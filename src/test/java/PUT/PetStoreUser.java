package PUT;

import POJO.PetStorePojo;
import POJO.PetStoreUserPOJO;
import Utils.PayloadUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;

public class PetStoreUser {

    @Test
    public void updateUser(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="/v2/user/createWithList";

        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getUserPayload(1, "mkokal", "melek", "kokal",
                        "mkokal@gma.com", "123456", "1112223344", 2))
                .when().post()
                .then().statusCode(200)
                .log().body();

    }

    @Test
    public void getUserTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("https://petstore.swagger.io/v2/user/mkokal")
                .then().statusCode(200)
                .log().body()
                .extract().response();

        PetStoreUserPOJO parsedResponse = response.as(PetStoreUserPOJO.class);
        String actualName = parsedResponse.getFirstName();
        String expectedName= "melek";
        Assert.assertEquals(expectedName, actualName);



    }
}
