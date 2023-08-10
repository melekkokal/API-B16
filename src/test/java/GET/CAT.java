package GET;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CAT {

    @Test
    public void FactsValidation(){
        RestAssured.baseURI="https://catfact.ninja";
        RestAssured.basePath="facts";
        Response response = RestAssured.given().accept("application/json").queryParam("limit", 100)
                .when().get()
                .then().statusCode(200).log().body().extract()
                .response();
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        List<Map<String, Object>> data = (List<Map<String, Object>>) deserializedResponse.get("data");

        Integer actualFactAmount=data.size();
        Integer expectedFactAmount=100;
        Assert.assertEquals(actualFactAmount,expectedFactAmount);


        //find the fact that has shorter than 50 characters and the fact that has more than 200 characters.
        //get the ones which doesnt have any cat or kitty word. //remove the limit!! 332 facts.

        List<String> factsLessThan50Chars= new ArrayList<>();
        List<String> factsMoreThan200Chars=new ArrayList<>();
        List<String> nonCatFacts=new ArrayList<>();

        for(int i=0; i<data.size(); i++){
            String allFacts= (String) data.get(i).get("fact").toString().toLowerCase();
            if(allFacts.length()<=50){
                factsLessThan50Chars.add(allFacts);
            }if(allFacts.length()>=200){
                factsMoreThan200Chars.add(allFacts);
            } else if (!(allFacts.contains("cats")) && !(allFacts.contains("cat")) && !(allFacts.contains("kittens"))) {
                nonCatFacts.add(allFacts);
            }
        }
        System.out.print("Cat facts less than 50 characters are " + factsLessThan50Chars
                    + " Facts more than 200 characters are " + factsMoreThan200Chars
                    + " Facts that don't contain cat or cats are " + nonCatFacts );

    }
}

