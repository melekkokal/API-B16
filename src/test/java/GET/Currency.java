package GET;

import io.restassured.RestAssured;
import org.junit.Test;

public class Currency {
   @Test //from JUnit
   public void usdCurrencyTest(){
       //1. Define the URL.
       RestAssured.baseURI = "https://fakestoreapi.com/products/1";
       //3. Add headers if needed.
       RestAssured.given().header("Accept", "application/json")
               .when().get() //Do it when I send the get request.
               .then().statusCode(200).log().body(); //statusCode method will do Assertion for us.
   }


}
