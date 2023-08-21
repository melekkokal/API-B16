package POST;

import POJO.PetStorePojo;
import POJO.ReqeesPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class Serialization {

    @Test
    public void serializationTest() throws IOException {

        PetStorePojo pet = new PetStorePojo();
        pet.setId(12345);
        pet.setName("marlow");
        pet.setStatus("playing");

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/test/resources/pet.json");
        objectMapper.writeValue(file, pet);
    }

    @Test
    public void createCustomer() throws IOException {
        ReqeesPOJO rp = new ReqeesPOJO();
        rp.setName("buket");
        rp.setJob("manager");

        ObjectMapper objectMapper=new ObjectMapper();

        File file = new File("src/test/resources/newcustomermarlow.json");
        objectMapper.writeValue(file, rp);

    }

    @Test
    public void createAnothercustomer() throws IOException {
        ReqeesPOJO reqeesPojo = new ReqeesPOJO();
        reqeesPojo.setName("jale");
        reqeesPojo.setJob("sdet");

        ObjectMapper om=new ObjectMapper();

        File file = new File("src/test/resources/newuserjale.json");
        om.writeValue(file, reqeesPojo);

        //ObjectMapper class=> Is the class that actually helps to perform Deserialization and Serialization.
        // as() method in restassured uses objectMapper under the hood.
        // (heart of deserialization very important)


    }
}
