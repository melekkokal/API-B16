package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlackPojo {

    private boolean ok;
    private String ts;
    private SlackMessagePojo message; //create a variable with the class name
    //and the variable name on Postman.

}
