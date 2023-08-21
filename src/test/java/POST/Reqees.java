package POST;

import POJO.ReqeesPOJO;
import Utils.PayloadUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Reqees {

    @Test
    public void createUserTest(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users?";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON).body(PayloadUtils.getReqeesPayload("melek", "student"))
                .when().post()
                .then().statusCode(201).log().body()
                .extract().response();


        ReqeesPOJO reqeesPOJO = response.as(ReqeesPOJO.class);
        String actualName=reqeesPOJO.getName();
        String actualJob=reqeesPOJO.getJob();
        Assert.assertEquals("melek", actualName);
        Assert.assertEquals("student", actualJob);

    }

    @Test
    public void createCustomerTest1(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users?";
        File jsonFile= new File("src/test/resources/newcustomermarlow.json");

        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonFile)
                .when().post()
                .then().statusCode(201)
                .log().body();

        ReqeesPOJO customer = new ReqeesPOJO();
        customer.setName("havva");
        customer.setJob("student");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(customer)
                .when().post()
                .then().statusCode(201)
                .log().body().extract().response();

        JsonPath parsedResponse =response.jsonPath();
        String actualName= parsedResponse.getString("name");
        Assert.assertEquals("havva", actualName);

    }

    @Test
    public void createCustomerTest2(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users?";
        File jsonFile= new File("src/test/resources/newuserjale.json");

         RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonFile)
                .when().post()
                .then().statusCode(201)
                .log().body() //sout
                .extract().response();



        ReqeesPOJO customer = new ReqeesPOJO();
        customer.setName("melek");
        customer.setJob("job");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(customer)
                .when().post()
                .then().statusCode(201)
                .log().body().extract().response();

        JsonPath parsedResponse =response.jsonPath();
        String actualName= parsedResponse.getString("name");
        Assert.assertEquals("melek", actualName);





    }
}
