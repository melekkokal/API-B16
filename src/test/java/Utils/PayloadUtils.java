package Utils;

public class PayloadUtils {
    public static String getPetPayload(Integer petID, String petName){
        String petPayload = "{\n" +
                "  \"id\": "+petID+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"anything\"\n" +
                "  },\n" +
                "  \"name\": \""+petName+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://s3.amazon.com\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    \n" +
                "  ],\n" +
                "  \"status\": \"snuggling\"\n" +
                "}";

        return petPayload;
    }

    public static String getSlackPayload(String message){
        String slackPayload="{\n" +
                "  \"channel\": \"C05H5S8A50V\",\n" +
                "  \"text\": \""+message+"\"\n" +
                "}";

        return slackPayload;
    }


    public static String getAirportPayload(String from, String to){
       String airportPayload="{\n" +
               "    \"from\": \""+from+"\",\n" +
               "    \"to\": \""+to+"\"\n" +
               "}";

       return airportPayload;

    }

    public static String getReqeesPayload(String name, String job){
        String reqeesPayload="{\n" +
                "   \"name\": \""+name+"\",\n" +
                "   \"job\": \""+job+"\"\n" +
                "}";
        return reqeesPayload;
    }
}
