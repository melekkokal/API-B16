package POST;

import POJO.SlackPojo;
import Utils.PayloadUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class Slack {
    @Test
    public void sendMessageTest(){
        RestAssured.baseURI="https://slack.com";
        RestAssured.basePath="api/chat.postMessage";

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON) //use it for POST
                .header("Authorization", "Bearer xoxb-4764264203175-5572283351303-ChTt1SkoH3zJPsSqvNZ3K8uq")
                .body(PayloadUtils.getSlackPayload("Merhaba!"))
                .when().post()
                .then().statusCode(200)
                .extract().response();

        SlackPojo parsedResponse = response.as(SlackPojo.class);
        String actualMessage= parsedResponse.getMessage().getText(); //we are getting the text from the class
        Assert.assertTrue(actualMessage.contains("Melek"));


    }

    @Test
    public void sendMessageTest2(){
       RestAssured.baseURI="https://slack.com";
       RestAssured.basePath="api/chat.postMessage";

       RestAssured.given().accept(ContentType.JSON)
               .contentType(ContentType.JSON)
               .header("Authorization","Bearer xoxb-4764264203175-5572283351303-ChTt1SkoH3zJPsSqvNZ3K8uq" )
               .body(PayloadUtils.getSlackPayload("Melek says Hey guys!"))//since it is post we attach request body.
               .when().post()
               .then().statusCode(200)
               .body("ok", Matchers.is(true))
               .body("message.text", Matchers.equalTo("Melek says Hey guys!"));




    }
}
