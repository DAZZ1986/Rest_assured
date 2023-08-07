package Pet_api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractTest {

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;
    protected static RequestSpecification requestSpecification;
    protected static ResponseSpecification responseSpecification;

    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        baseUrl = prop.getProperty("base_pet_url");


        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .setContentType(ContentType.JSON)
                .addHeader("Content-Type", "application/json")
                .addHeader("accept", "application/json")
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(Matchers.lessThan(15000L))
                .expectContentType(ContentType.JSON)
                .build();
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }


    public static String getApiKey() {
        return apiKey;
    }
    public static String getBaseUrl() {
        return baseUrl;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
    public ResponseSpecification getResponseSpecification() {
        return responseSpecification;
    }

}
