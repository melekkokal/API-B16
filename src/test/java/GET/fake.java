package GET;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class fake {

    @Test
    public void faketest(){

        RestAssured.baseURI="https://fakestoreapi.com/";
        RestAssured.basePath="products/1";

        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get()
                .then().statusCode(200).log().body().extract()
                .response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        double price = (Double) deserializedResponse.get("price");
        Assert.assertEquals(109.95, price,0.0); //delta is minor difference

    }
}
