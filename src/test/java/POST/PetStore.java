package POST;

import POJO.PetStorePojo;
import Utils.PayloadUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class PetStore {
    @Test
    public void createPetTest(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload(30796, "luna")) //copy and paste your request body
                .when().post()
                .then().statusCode(200)
                .log().body()
                .extract().response();

        PetStorePojo petStorePojo= response.as(PetStorePojo.class);
        //since we need to validate it we can use the pojo cass
        String actualName=petStorePojo.getName();
        String actualStatus=petStorePojo.getStatus();
        Assert.assertEquals("luna", actualName);
        Assert.assertEquals("snuggling", actualStatus);




    }

    @Test
    public void createPetWithJsonFileTest(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
        File jsonfile = new File("src/test/resources/pet.json"); //where we created the json file.

        //here we pass the json file and validate that it passes.
        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonfile)
                .when().post()
                .then().statusCode(200)
                .log().body();

        PetStorePojo pet = new PetStorePojo();
        pet.setId(12345);
        pet.setName("luna");
        pet.setStatus("relaxing");

        //using the java object. request body is now java object.
        Response response = RestAssured.given() .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(pet) //object as a parameter.
                .when().post()
                .then().statusCode(200)
                .log().body().extract().response();
                //log.body prints out

        //Deserialization - JSONPath
        JsonPath parsedResponse = response.jsonPath(); //this will help use deserialize.
        String actualName = parsedResponse.getString("name");
        Assert.assertEquals("luna", actualName);
        parsedResponse.getString("category.name"); //this will bring you the child key under category.

    }
}
