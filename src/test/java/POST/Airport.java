package POST;

import POJO.AirportPojo;
import Utils.PayloadUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class Airport {

    @Test
    public void validateTheMiles(){
        RestAssured.baseURI="https://airportgap.dev-tester.com";
        RestAssured.basePath="api/airports/distance";

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer JrUQ9qQ9hbPM13r57KciPWxG")
                .body(PayloadUtils.getAirportPayload("ORD", "MIA"))
                .when().post()
                .then().statusCode(200)
                .extract().response();

        AirportPojo parsedResponse = response.as(AirportPojo.class);
        double actualDistanceInMiles= parsedResponse.getData().getAttributes().getMiles();
        double expectedDistanceInMiles=1198.4365657701198;
        Assert.assertEquals("Failed to validate the distance" ,
                expectedDistanceInMiles, actualDistanceInMiles,0);


    }
}
