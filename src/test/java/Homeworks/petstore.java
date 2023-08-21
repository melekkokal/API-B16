package Homeworks;

import POJO.PetStorePojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class petstore {


    @Test
    public void createPetTest(){

        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet/";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 11110,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"any string\"\n" +
                        "  },\n" +
                        "  \"name\": \"pepe\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"https://s3.amazon.com\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    \n" +
                        "  ],\n" +
                        "  \"status\": \"sleeping\"\n" +
                        "}")
                .when().post()
                .then().statusCode(200)
                .extract().response();

        PetStorePojo petStorePojo = response.as(PetStorePojo.class);
        String actualName=petStorePojo.getName();
        String actualStatus=petStorePojo.getStatus();
        Assert.assertEquals("pepe",actualName);
        Assert.assertEquals("sleeping",actualStatus);
    }
}
