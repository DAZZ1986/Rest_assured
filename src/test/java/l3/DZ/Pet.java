package l3.DZ;

    /*
{
      "id": 0,
      "category": {
        "id": 0,
        "name": "string"
      },
      "name": "doggie",
      "photoUrls": [
        "string"
      ],
      "tags": [
        {
          "id": 0,
          "name": "string"
        }
      ],
      "status": "available"
}
     */

    /* туториал Что такое JSON и как с ним работать, написание pojo - https://www.youtube.com/watch?v=ZDk4UnOHXXA
    json структура и правила: (это ключ значение)
    - в кавычки берем только текстовые значения, а числа и boolean в кавычки брать не нужно. Используем запятые для отделения записей друг от друга.
    - массивы - значениях должны быть одного типа и закраваются в квадратные скобки. (пример "photoUrls")
    - объект объектов (объект состоящий из других объектов) -                        (пример "category").
{
	"id": 0,
	"category": {   //это объект объектов
		"id": 0,
		"name": "string",
		"dress": [1,2,3]
	},
	"name": "Masy",
	"photoUrls": [  //это массив именнованный список однотипных значений (в pojo - )
		"string",
		"string",
		"string",
		"string",
		"string"
	],
	"tags": [       //это массив объектов (в pojo - вложенный класс)
	    {
			"id": 0,
			"name": "string"
		},
		{
			"id": 0,
			"name": "string",
			"price": 0
		}
	],
	"status": "available"
}
     */

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "category",
        "name",
        "photoUrls",
        "tags",
        "status"
})
public class Pet {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private List<String> photoUrls;
    @JsonProperty("tags")
    private List<Tag> tags;
    @JsonProperty("status")
    private String status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();





    @JsonProperty("id")
    public Long getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }
    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("photoUrls")
    public List<String> getPhotoUrls() {
        return photoUrls;
    }
    @JsonProperty("photoUrls")
    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    @JsonProperty("tags")
    public List<Tag> getTags() {
        return tags;
    }
    @JsonProperty("tags")
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }



    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}