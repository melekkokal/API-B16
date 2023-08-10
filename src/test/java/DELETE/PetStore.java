package DELETE;

import POJO.PetStorePojo;
import Utils.PayloadUtils;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class PetStore {
    /*
    1. Create a pet
    2. Delete a pet and get status code 200
    3. Delete a pet and get status code 404
     */



    @Test
    public void deletePetTest(){


        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        //create a pet
        int petId=11223;
        String petName="duman";
        String petStatus="sleeping";

        PetStorePojo petPayload=new PetStorePojo();
        //creating new object is for serialization

        petPayload.setId(petId);
        petPayload.setName(petName);
        petPayload.setStatus(petStatus);
        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petPayload)
                .when().post()
                .then().statusCode(200).extract().response();

        //deserialization with POJO
        PetStorePojo parsedResponse = response.as(PetStorePojo.class);
        //now we can validate the fields that we are supposed to.
        Assert.assertEquals(petName, parsedResponse.getName());
        Assert.assertEquals(petId, parsedResponse.getId());
        Assert.assertEquals(petStatus, parsedResponse.getStatus());

        //delete the pet
        response = RestAssured.given().accept(ContentType.JSON)
                .when().delete(String.valueOf(petId)) //convert it to String because delete method doesn't take int.
                .then().statusCode(200).extract().response();

        //you can re-assign the value.

        Map<String, Object> deseriliazedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        String actualMessage= (String) deseriliazedResponse.get("message");
        int actualCode= (int) deseriliazedResponse.get("code");
        Assert.assertEquals(200, actualCode);
        Assert.assertEquals(String.valueOf(petId), actualMessage);


        //3 delete the pet and get a status code 404
        response = RestAssured.given().accept(ContentType.JSON)
                .when().delete(String.valueOf(petId)) //convert it to String because delete method doesn't take int.
                .then().statusCode(404).extract().response();

    }

}
