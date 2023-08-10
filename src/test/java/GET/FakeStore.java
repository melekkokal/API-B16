package GET;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class FakeStore {

    @Test
    public void FakeStore(){
        RestAssured.baseURI="https://fakestoreapi.com/products/1";
        Response response = RestAssured.given().accept( "application/json")
                .when().get()
                .then().statusCode(200).log().body().extract()
                .response();

        Map<String,Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(deserializedResponse);
        double price = (double) deserializedResponse.get("price");
        Assert.assertEquals(109.95,price,0);

        Double price2 = (Double) deserializedResponse.get("price");
        Assert.assertEquals(109.95, price2, 0); //here assert is confuse because it thinks there might be 2 objects or
        //two doubles. that's why you add delta.

        Assert.assertEquals(109.95, deserializedResponse.get("price")); //here it will compare two objects.
        //not always safe. because we might compare two numbers instead of objects.

        Map<String, Object> ratingMap = (Map<String, Object>) deserializedResponse.get("rating");
        Double rating = (Double) ratingMap.get("rate");
        Integer count = (Integer) ratingMap.get("count");

        Assert.assertTrue(rating==3.9);
        Assert.assertTrue(count==120);
    }

    @Test
    public void ValidateTheSumOfAllOfTheProducts(){
    RestAssured.baseURI="https://fakestoreapi.com";
    RestAssured.basePath="products";
    Response response=RestAssured.given().accept("application/json")
            .when().get()
            .then().statusCode(200).log().body().extract()
            .response();

        List<Map<String,Object>> deserializedResponse1 = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        double sumOfNums= 0.0;
        for(int i=0; i<deserializedResponse1.size();i++){
           Double total= Double.parseDouble(deserializedResponse1.get(i).get("price").toString());
            sumOfNums+=total;
        }
        System.out.println(sumOfNums);
        Assert.assertTrue(sumOfNums>200);
    }




















}
