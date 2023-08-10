package PUT;

import Utils.PayloadUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

public class PetStore {
    @Test
    public void updatePetTest(){

        /*
        STEPS:
        1. Create a pet
        2. List a pet
        3. Update a pet
        4. List a pet
         */

        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        int originalPetID=01000;
        String originalPetName="bella";

        //Create a pet
        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload(originalPetID, originalPetName))
                .when().post()
                .then().statusCode(200)
                .body("id", Matchers.equalTo(originalPetID))
                .body("name", Matchers.equalTo(originalPetName));



        //Get pet
        RestAssured.given().accept(ContentType.JSON)
                .when().get(originalPetID+"")
                .then().statusCode(200)
                .body("id", Matchers.is(originalPetID))
                .body("name", Matchers.equalTo(originalPetName));


        //Put(Update) a pet
        String updatedPetName="pino";

        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload(originalPetID, updatedPetName))
                .when().put()
                .then().statusCode(200)
                .body("id", Matchers.is(originalPetID))
                .body("name", Matchers.is(updatedPetName));

        //Get pet
        RestAssured.given().accept(ContentType.JSON)
                .when().get(""+originalPetID)
                .then().statusCode(200)
                .body("id", Matchers.equalTo(originalPetID))
                .body("name", Matchers.equalTo(updatedPetName));





    }
}
