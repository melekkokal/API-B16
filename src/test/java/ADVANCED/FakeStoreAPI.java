package ADVANCED;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

public class FakeStoreAPI {

   /* @Test
    public void getAllPrice(){
        RestAssured.baseURI="https://fakestoreapi.com/";
        RestAssured.basePath="products/";

        RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .body(".find {it.id == 1 }.title", Matchers.is("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"))
                .and()
                .body("")
    }*/
}
