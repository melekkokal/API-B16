package POST;

import POJO.ReqeesPOJO;
import Utils.PayloadUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

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
}
