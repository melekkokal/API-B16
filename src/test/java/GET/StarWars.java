package GET;

import POJO.PlanetsPojo;
import POJO.StarWarsCharacterPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class StarWars {

 @Test
    public void characterTest(){
     Response response = RestAssured.given().accept("application/json")
             .when().get("https://swapi.dev/api/people/1")
             .then().statusCode(200)
             .log().body()
             .extract().response();

     StarWarsCharacterPojo parsedResponse = response.as(StarWarsCharacterPojo.class);
     String expecedName = "Luke Skywalker";
     String actualName = parsedResponse.getName();
     Assert.assertEquals("Failed to validate SW character name", expecedName, actualName);

 }

 @Test
 public void planetsTest(){
  Response response = RestAssured.given().accept(ContentType.JSON)
          .when().get("https://swapi.dev/api/planets/1")
          .then().statusCode(200)
          .log().body()
          .extract().response();

  PlanetsPojo planets = response.as(PlanetsPojo.class);

  String actualName= planets.getName();
  String expectedName="Tatooine";
  Assert.assertEquals("Failed to validate the name of the planet.", expectedName, actualName);


  String actualPopulation=planets.getPopulation();
  String expectedPopulation="200000";
  Assert.assertEquals("Failed to validate the population.", expectedPopulation, actualPopulation);

  String actualTerrain=planets.getTerrain();
  String expectedTerrain="desert";
  Assert.assertEquals("Failed to validate the terrain.", expectedTerrain, actualTerrain);
 }
}
