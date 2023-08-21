package Homeworks;

import Utils.PayloadUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.json.Json;

public class PetStorePost {

    @Test
    public void postPetID(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload(12346, "momo"))
                .when().post()
                .then().statusCode(200)
                .log().body()
                .extract().response();

        JsonPath parsedResponse = response.jsonPath();
        String actualName = parsedResponse.getString("name");
        Assert.assertEquals("momo", actualName);

    }

    @Test
    public void putPet(){

        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        String updatedName="caki";
        int originalpetID=11111;

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload(originalpetID, updatedName))
                .when().put()
                .then().statusCode(200)
                .body("id", Matchers.is(originalpetID))
                .body("name", Matchers.is(updatedName))
                .extract().response();


    }
}
