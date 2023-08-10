package GET;

import POJO.PetStorePojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class PetStore {

    @Test
    public void getPetTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("https://petstore.swagger.io/v2/pet/10567")
                .then().statusCode(200)
                .log().body()
                .extract().response();

    PetStorePojo parsedResponse = response.as(PetStorePojo.class);
    String actualPetName = parsedResponse.getName();
    String expectedPetName= "hatiko";
        Assert.assertEquals(expectedPetName, actualPetName);

        parsedResponse.getCategory().getId(); //referring the id from getCategory = method channing.


    }
}
