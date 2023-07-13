package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GOT {

    @Test
    public void GOTURLValidation(){
        RestAssured.baseURI="https://thronesapi.com/api/v2/Characters";
        RestAssured.given().header("Accept", "application/json") //there are some API that we must to provide HEADERS
                .when().get()
                .then().statusCode(200).log().body();
    }


   @Test
    public void getSpecificCharacter(){
     RestAssured.baseURI="https://thronesapi.com/api/v2/Characters/10";
     Response response = RestAssured.given().header("Accept", "application/json")
                .when().get()
                .then().statusCode(200).log().body().extract()
                .response();

       Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
       });
       System.out.println(deserializedResponse);
       String firstName = (String) deserializedResponse.get("firstName"); //if you live it as it returns an object.
       // since out value is Object on the Map. We have to convert.
       //you can also cast it.
       //you can also do toString.
       String lastName = deserializedResponse.get("lastName").toString();
       Assert.assertEquals("Cateyln", firstName);
       Assert.assertEquals("Stark", lastName);
   }

   @Test
    public void GetTheContents(){
        RestAssured.baseURI="https://thronesapi.com";
        RestAssured.basePath="api/v2/continents";
        Response response=RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200).log().body().extract()
                .response();
       List<Map<String, Object>> deserializedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
       });

       int count=0;
       for(int i =0; i<=deserializedResponse.size(); i++){
        count=i;
       }
    Assert.assertEquals(count, 4);

       Integer actualContinentCount = deserializedResponse.size();
       Integer expectedContinentCount = 4;
       Assert.assertEquals(actualContinentCount, expectedContinentCount);
       //store all of the names in a list.
       List<String> continentList = new ArrayList<>();
       for (int i =0; i<deserializedResponse.size() ; i++){
           Map<String, Object> tempMap = deserializedResponse.get(i);
           continentList.add((String) tempMap.get("name"));

       }
       System.out.println(continentList);
   }

   @Test
    public void StoringNameAndID(){
        RestAssured.baseURI="https://thronesapi.com";
        RestAssured.basePath="api/v2/continents";
        Response response = RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200).log().body().extract()
                .response();

       List<Map<String, Object>> deserializedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
       });
       Map<String, Integer> namesAndIDs = new HashMap<>();
       for(int i = 0; i< deserializedResponse.size() ; i++){
           Integer ids = (Integer) deserializedResponse.get(i).get("id");
           String names= (String) deserializedResponse.get(i).get("name");

           namesAndIDs.put(names, ids);
       }
       System.out.println(namesAndIDs);

   }
}