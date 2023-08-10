package POST;

import POJO.PetStorePojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class Serialization {

    @Test
    public void serializationTest() throws IOException {

        PetStorePojo pet=new PetStorePojo();
        pet.setId(12345);
        pet.setName("marlow");
        pet.setStatus("playing");

        ObjectMapper objectMapper = new ObjectMapper();

        File file=new File("src/test/resources/pet.json");
        objectMapper.writeValue(file, pet);
    }
}
