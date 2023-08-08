package Pet_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import main.API.Category;
import main.API.Pet;
import main.API.Tag;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Story("CRUD манипуляции с сущностью класса Pet")
public class PetApiTest extends AbstractTest {

    private static Long id = null;
    private String debug;

    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();   //глобальное логирование - логировать если чтото пойдет не так
    }
    //перенос из постмана в автотесты
    //рефактор нашдобавить RequestSpecification и ResponseSpecification спецификации - ok
    //отправка и получение json объекта с использованием jackson (сериализации/десериализации) - ok


    //Базовая сериализация и десериализация JSON с Jackson - в данном файле пример базовой сериализации и десериализации.
    //https://habr.com/ru/companies/otus/articles/687004/

    @Test
    @DisplayName("Validate doc by json Schema")
    void checkJsonSchema() {
        given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("id", id)
                    .when()
                    .get(getBaseUrl() + "v2/pet/{id}")
                        .then()
                        .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PetSchema.json"));
    }






    /*  переност автотестов из postman
    pm.test("Status code is 200", function () {
        pm.response.to.have.status(200);
    });
    pm.test("Your test name", function () {
        var jsonData = pm.response.json();
        pm.expect(jsonData.name).to.eql("Musy");
    });
     */
    /*  без сериализации
    @Test   //Отправка POST запроса - json файлом в body запроса.
    @DisplayName("POST - Check pet create")
    void checkPetPOST() {
        File jsonFile = new File("src/test/java/l3/DZ/files/PetPOSTJsonFile.json");

        Response respPost = given()
                .spec(getRequestSpecification())
                .body(jsonFile)
                    .when()
                    .request(Method.POST, getBaseUrl() + "v2/pet");

        assertThat(respPost.getStatusCode(), equalTo(200));
        assertThat(respPost.getBody().jsonPath().get("name").toString(), equalTo("Musy"));
        id = respPost.getBody().jsonPath().get("id");
    }
     */
    @Test  //Отправка POST запроса - СЕРИАЛИЗАЦИЯ pojo объект в body запроса.
    @DisplayName("POST - Check pet create")
    @Feature("Карточка товара")
    @Step("Создание сущности класса Pet")
    void createEntitycheckPOST() throws JsonProcessingException {

        Category category1 = new Category();
        category1.setId(7);
        category1.setName("home");

        String photoUrl = "photoUrl";
        List<String> listPhoto = new ArrayList<>();
        listPhoto.add(photoUrl);

        Tag tag1 = new Tag();
        tag1.setId(0);
        tag1.setName("Stringgggg");
        List<Tag> listTag = new ArrayList<>();
        listTag.add(tag1);


        Pet pet1 = new Pet();
        pet1.setId(0l);
        pet1.setCategory(category1);
        pet1.setName("MusyPOST");
        pet1.setPhotoUrls(listPhoto);
        pet1.setTags(listTag);
        pet1.setStatus("available");

        //СЕРИАЛИЗАЦИЯ  (pojo -> json)
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPet1 = objectMapper.writeValueAsString(pet1);    //POJO to json

        Response respPost = given()
                .spec(getRequestSpecification())
                .body(jsonPet1)     //и наш pojo отправляем как json строку
                .when()
                .request(Method.POST, getBaseUrl() + "v2/pet");

        assertThat(respPost.getStatusCode(), equalTo(200));
        assertThat(respPost.getBody().jsonPath().get("name").toString(), equalTo("MusyPOST"));

        Allure.step("Сущность Pet создана");

        id = respPost.getBody().jsonPath().get("id");
    }






    /*  переност автотестов из postman
    var jsonData = pm.response.json();

    pm.test("Status code is 200", function () {
        pm.response.to.have.status(200);
    });
    pm.test("Check Header Content-Type", function () {
        pm.response.to.have.header("Content-Type");
        pm.response.to.be.header("Content-Type", "application/json");
    });
    pm.test("Check JSON category.name", function () {
        pm.expect(jsonData.category.name).to.eql("home");
    });
    pm.test("Check JSON tags.id", function () {
        pm.expect(jsonData.tags[0].id).to.eql(0);
    });
    pm.test("Check JSON status", function () {
        pm.expect(jsonData.status).to.eql("available");
    });
     */
    /*  без десериализации
    Response respGet = given()
                            .header("Content-Type", "application/json")
                            .header("accept", "application/json")
                            .pathParam("id", id)
                                .when()
                                .request(Method.GET, getBaseUrl() + "v2/pet/{id}");

    assertThat(respGet.getStatusCode(), equalTo(200));
    assertThat(respGet.getContentType(), equalTo("application/json"));

    JsonPath respJsonBody = respGet.getBody().jsonPath();
    assertThat(respJsonBody.get("id"), equalTo(id));
    assertThat(respJsonBody.get("category.name"), equalTo("home"));
    assertThat(respJsonBody.get("tags[0].id"), equalTo(0));
    assertThat(respJsonBody.get("status"), equalTo("available"));
    */
    @Test  //Отправка GET запроса - ДЕСЕРИАЛИЗАЦИЯ, json из ответа в pojo объект.
    @DisplayName("GET - Check pet exist")
    @Feature("Личный кабинет")
    @Step("Проверка наличия сущности класса Pet")
    void checkPetGET() throws IOException {
        if(id == null) {
            createEntitycheckPOST();
        }

        Response respGet = given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("id", id)
                .when()
                .request(Method.GET, getBaseUrl() + "v2/pet/{id}");

        assertThat(respGet.getStatusCode(), equalTo(200));
        assertThat(respGet.getContentType(), equalTo("application/json"));

        JsonPath respJsonBody = respGet.getBody().jsonPath();
        assertThat(respJsonBody.get("id"), equalTo(id));
        assertThat(respJsonBody.get("name"), equalTo("Musy_PUT_UPDATE"));
        assertThat(respJsonBody.get("category.name"), equalTo("home"));
        assertThat(respJsonBody.get("tags[0].id"), equalTo(0));
        assertThat(respJsonBody.get("status"), equalTo("sold"));

        Allure.step("Сущность Pet получена");


        //ДЕСЕРИАЛИЗАЦИЯ
        //вариант 1. Fluent style
/*
        Pet newPet0 = given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("id", id)
                    .when()
                    .request(Method.GET, getBaseUrl() + "v2/pet/{id}")
                    .prettyPeek()
                        .then()                 //вызываем такую последовательность: ДЕСЕРИАЛИЗАЦИЯ (принимаем json -> pojo класс)
                        .extract()              //1. извлекаем
                        .response()             //2. обращаемся к ответу
                        .body()                 //3. берем у него body
                        .as(Pet.class);    //4. и сохраняем его как нами написанный Response.class

        System.out.println(newPet0);
*/

        //вариант 2. ObjectMapper
        //а) - D:\CODE\Курс\AQA\moi gb.ru\6 Тестирование backend на Java - Максим Кравченко\Урок 4. Расширенные возможности rest-assured\ДЗ сериализация, десериализация
/*
        String employeeJson = "{\n" +
                                " \"firstName\" : \"Jalil\",\n" +
                                " \"lastName\" : \"Jarjanazy\",\n" +
                                " \"age\" : 30\n" +
                                "}";
*/
        ObjectMapper objectMapper = new ObjectMapper();
        String json = respJsonBody.prettify();                    //json сохраняем как строку
        Pet newPet1 = objectMapper.readValue(json, Pet.class);    //и данную строку json передаем в метод readValue и сохраняем как объект Pet
        System.out.println(newPet1);                              //3. ДЕСЕРИАЛИЗАЦИЯ, мы можем разложить json файл по переменным POJO класса - как достать json переменную?

        //б) - https://stackoverflow.com/questions/45110031/convert-jsonobject-to-json-string-with-jackson
        //ObjectMapper mapper = new ObjectMapper();
        //JSONObject object = ...;
        //String jsonString = mapper.writeValueAsString(object);
    }







    /*  переност автотестов из postman
    pm.test("Status code is 200", function () {
        pm.response.to.have.status(200);
    });

    var jsonData = pm.response.json();

    pm.test("Your test name", function () {
        var jsonData = pm.response.json();
        pm.expect(jsonData.id).to.eql(id);
    });
    pm.test("Check category name", function () {
        pm.expect(jsonData.name).to.eql("Musy_PUT");
    });
     */
    @Test
    @DisplayName("UPDATE - Check pet name update")
    @Feature("Личный кабинет")
    @Step("Проверка модификации сущности класса Pet")
    void checkPetUPDATE() throws JsonProcessingException, ParseException {
        if(id == null) {
            createEntitycheckPOST();
        }

        //By File
        /*
        File jsonFile0 = new File("src/test/java/Pet_api/files/PetPUTJsonFile.json");

        //update from Json file
        try (Reader jsonFile1 = new FileReader("src/test/java/Pet_api/files/PetPUTJsonFile.json")) {
            //Read JSON file
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(jsonFile1);
            data.put("id", id);
            data.put("name", "Musy_PUT_UPDATE");
            data.put("status", "sold");

            //Write JSON file
            @SuppressWarnings("resource")
            FileWriter file = new FileWriter("src/test/java/Pet_api/files/PetPUTJsonFile.json");
            file.write(data.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        */

        JsonPath getPet = given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("id",id)
                .when()
                .request(Method.GET, getBaseUrl() + "v2/pet/{id}")
                .then()
                .extract()
                .jsonPath();

        //Read JSON data
        JSONParser parser = new JSONParser();
        String jsonStr = getPet.prettify();    //json сохраняем как строку
        //Write JSON data
        JSONObject data = (JSONObject) parser.parse(jsonStr);
        data.put("name", "Musy_PUT_UPDATE");
        data.put("status", "sold");


        Response respUpdate = given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(data)
                    .when()
                    .request(Method.PUT, getBaseUrl() + "v2/pet");

        assertThat(respUpdate.getStatusCode(), equalTo(200));
        JsonPath respJsonBody = respUpdate.getBody().jsonPath();
        assertThat(respJsonBody.get("id"), equalTo(id));
        assertThat(respJsonBody.get("name"), equalTo("Musy_PUT_UPDATE"));
        assertThat(respJsonBody.get("status"), equalTo("sold"));

        Allure.step("Сущность Pet корректно модифицирована");
    }






    /*  переност автотестов из postman
    var jsonData = pm.response.json();

    pm.test("Status code is 200", function () {
        pm.response.to.have.status(200);
    });
    pm.test("Your test name", function () {
        pm.expect(jsonData.code).to.eql(200);
    });
    pm.test("Your test name", function () {
        pm.expect(jsonData.message).to.eql("1313");
    });
     */
    @Test
    @DisplayName("DELETE - Check pet is deleted")
    @Feature("Корзина")
    @Step("Проверка удаления сущности класса Pet")
    void checkPetDELETE() throws JsonProcessingException {
        if(id == null) {
            createEntitycheckPOST();
        }

        //удаляем объект
        Response respDelete = given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("id", id)
                .when()
                .request(Method.DELETE, getBaseUrl() + "v2/pet/{id}");

        assertThat(respDelete.getStatusCode(), equalTo(200));   //проверка 1
        assertThat(respDelete.getContentType(), equalTo("application/json"));

        JsonPath respJsonBody = respDelete.getBody().jsonPath();
        assertThat(respJsonBody.get("code"), equalTo(200));     //проверка 2
        //take out message value
        String mes = respJsonBody.get("message");
        //convert message value from string to long
        long hills = Long.parseLong(mes);
        assertThat(hills, equalTo(id));                                 //проверка 3


        //перепроверяем удаление GET запросом
        given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("id",id)
                .response()
                .contentType(ContentType.JSON)
                .statusCode(404)
                .body("code", equalTo(1))
                .body("message", equalTo("Pet not found"))
                    .when()
                    .request(Method.GET, getBaseUrl() + "v2/pet/{id}");
                        //.then()
                        //.spec(getResponseSpecification());

        Allure.step("Сущность Pet корректно удалена");

        id = null;
    }

}
