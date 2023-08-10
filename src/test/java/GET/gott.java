package GET;

import POJO.ContinentPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gott {

    @Test
    public void gottTest(){

        RestAssured.baseURI="https://thronesapi.com/";
        RestAssured.basePath="api/v2/Characters/10";

        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get()
                .then().statusCode(200).log().body().extract()
                .response();

        Map<String, Object> deseralizedResponse= response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(deseralizedResponse);
        System.out.println(deseralizedResponse.get("lastName"));

    }


    @Test
    public void getcontinents(){

        RestAssured.baseURI="https://thronesapi.com";
        RestAssured.basePath="api/v2/continents";

        Response response = RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200).log().body().extract()
                .response();

        List<Map<String, Object>> deserializedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        int count=0;
        for(int i = 0; i<=deserializedResponse.size();i++){
          count=i;
        }
        Assert.assertEquals(count, 4);

        List<String> allContinents=new ArrayList<>();
        for(int i=0;i<deserializedResponse.size();i++){
            Map<String, Object> tempMap=deserializedResponse.get(i);
            allContinents.add((String) tempMap.get("name"));

        }
        System.out.println(allContinents);
        }


        @Test
    public void continentss(){
        RestAssured.baseURI="https://thronesapi.com/";
        RestAssured.basePath="api/v2/continents";

        Response response = RestAssured.given().accept(ContentType.JSON) //under the hood it has the JSON value
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .log().body().extract().response();

        //pojo deserialization
            List<ContinentPojo> continentPojoList = new ArrayList<>();


            //continentPojoList = response.as(continentPojoList.getClass());
            ContinentPojo [] parsedResponse = response.as(ContinentPojo[].class);
            Map<String, Integer> continentIDmap = new HashMap<>();

            for (int i = 0; i < parsedResponse.length; i++) {
                ContinentPojo continentPojo=parsedResponse[i];
                String name= continentPojo.getName();
                int id=continentPojo.getId();
                continentIDmap.put(name,id);
            }

        }

    }



