package GET;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class Airports {
@Test
    public void getListOfAirportsTest(){
    RestAssured.baseURI="https://airportgap.dev-tester.com";
    RestAssured.basePath="api/airports";

    Response response = RestAssured.given().accept(ContentType.JSON)
            .header("Authorization", "Bearer JrUQ9qQ9hbPM13r57KciPWxG")
            .when().get()
            .then().statusCode(200)
            .extract().response();

    //deserialization
    JsonPath parsedResponse = response.jsonPath();
    String nextPageUrl = parsedResponse.getString("links.next");
    System.out.println("Next page url is : " + nextPageUrl);

    //retrieve id of first airport
    String firstID = parsedResponse.getString("data[0].id");
    Assert.assertEquals("GKA", firstID);

    //retrieve city of first airport
    String firstCity = parsedResponse.getString("data[0].attributes.city");
    Assert.assertEquals("Goroka",firstCity );

}
}
