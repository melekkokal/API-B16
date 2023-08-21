package Homeworks;

import Utils.PayloadUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

public class UpdateReqeesCustomer {

       /*
    STEPS:
        1. Create a customer
        2. List a customer
        3. Update a customer
        4. List a customer
         */

   /* @Test
    public void createCustomer(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users";

        String originalName="morpheus";
        String originalJob="leader";

        //create
        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getReqeesPayload(originalName,originalJob))
                .when().post()
                .then().statusCode(201)
                .log().body();

        //list
        RestAssured.given().accept(ContentType.JSON)
                .when().get(originalName+originalJob)
                .then().statusCode(404)
                .body("job", Matchers.is(originalJob))
                .body("name", Matchers.is(originalName))
                .log().body();


        //update

        String updatedName="Buket";
        String updatedJob="Manager";

        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getReqeesPayload(updatedName, updatedJob))
                .when().put()
                .then().statusCode(200)
                .log().body();






    }*/





}
