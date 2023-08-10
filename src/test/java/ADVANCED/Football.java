package ADVANCED;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

public class Football {

    @Test
    public void getCompatitionsTest(){
        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .header("X-Auth-Token", "c55b7a64e8424d46a52051bce36d1c0a")
                .when().get()
                .then().statusCode(200)
                .body("competitions.find { it.id == 2166 }.name", Matchers.is("AFC Champions League"))
                .body("competitions.findAll { it.area.name == 'Africa' }.name",
                        Matchers.containsInRelativeOrder("AFC Champions League"))
                .and()
                .body("competitions.min { it.id }.name", Matchers.equalTo("FIFA World Cup"))
                .body("competitions.collect { it.id }.sum()", Matchers.lessThan(400000))
                .extract().response();

                                //with name you say give me the name of the comp.
        List<String> africaCompetitionsList = response.path("competitions.findAll { it.area.name == 'Africa' }.name");
        System.out.println(africaCompetitionsList);
    }
}
