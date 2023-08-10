package Homeworks.Homework1.stepdef;

import Homeworks.Homework1.pages.APIchatPage;
import Homeworks.Homework1.pages.LoginPage;
import Homeworks.Homework1.pages.MainPage;
import Utils.ConfigReader;
import Utils.DriverHelper;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SlackStepDef {
    WebDriver driver= DriverHelper.getDriver();
    LoginPage loginPage=new LoginPage(driver);
    MainPage mainPage=new MainPage(driver);
    APIchatPage apiChatPage=new APIchatPage(driver);


    @Given("User navigates to the website")
    public void userNavigatesToTheWebsite() {
    driver.get(ConfigReader.readProperty("QA_slack_url"));
    loginPage.launchSlack();
    }

    /*
    Classroom solution
    @Given("User navigates to the website")
    public void userNavigatesToTheWebsite() {
    RestAssured.baseURI="https://slack.com/";
    RestAssured.basePath="api/chat.postMessage";
    }

     */

    @When("User clicks on API channel and sends {string}")
    public void userClicksOnAPIChannelAndSendsHelloPeopleThisIsMel(String message) {
    mainPage.clickAPI();
    apiChatPage.sendMessage(message, driver);
    }
    /*
    Classroom solution
    @When("User clicks on API channel and sends {string}")
    public void userClicksOnAPIChannelAndSendsHelloPeopleThisIsMel(String message) {
    Response response = RestAssured.given().accept(ContentType.JSON)
               .contentType(ContenType.JSON)
               .header("Authorization",
               .body(PayloadUtils.getSlackPayload("Message from Cucumber"))
               .when().post();
                   }
     */

    @Then("User validates {string} is in Slack through API request")
    public void userValidatesIsInSlackThroughAPIRequest(String expectedMessage) {
        RestAssured.baseURI="https://slack.com";
        RestAssured.basePath="api/chat.postMessage";

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON) //use it for POST
                .header("Authorization", "Bearer xoxb-4764264203175-5572283351303-ChTt1SkoH3zJPsSqvNZ3K8uq")
                .when().get("https://slack.com/api/conversations.history?channel=C05H5S8A50V")
                .then().statusCode(200).log().body()
                .extract().response();

       Map<String, Object> parsedResponse=response.as(new TypeRef<Map<String, Object>>() {
       });
        List<Map<String, Object>> allMessages= (List<Map<String, Object>>) parsedResponse.get("messages");

        List<String> allText= new ArrayList<>();
        for(int i =0; i<allMessages.size(); i++){
            String actualMessage=allMessages.get(i).get("text").toString();
            allText.add(actualMessage);
            if(actualMessage.equals(expectedMessage)){
                Assert.assertEquals(expectedMessage,actualMessage);

            }        }

    }




}
