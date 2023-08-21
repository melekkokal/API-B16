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

    public static String getFootballPayload(int id,String name){
        String footballPayload="{\n" +
                "            \"id\": "+id+",\n" +
                "            \"area\": {\n" +
                "                \"id\": 2001,\n" +
                "                \"name\": \"Africa\",\n" +
                "                \"countryCode\": \"AFR\",\n" +
                "                \"ensignUrl\": null\n" +
                "            },\n" +
                "            \"name\": \""+name+"\",\n" +
                "            \"code\": \"QCAF\",\n" +
                "            \"emblemUrl\": null,\n" +
                "            \"plan\": \"TIER_FOUR\",\n" +
                "            \"currentSeason\": {\n" +
                "                \"id\": 555,\n" +
                "                \"startDate\": \"2019-09-04\",\n" +
                "                \"endDate\": \"2021-11-16\",\n" +
                "                \"currentMatchday\": 6,\n" +
                "                \"winner\": null\n" +
                "            },\n" +
                "            \"numberOfAvailableSeasons\": 3,\n" +
                "            \"lastUpdated\": \"2022-03-13T18:51:44Z\"\n" +
                "        }";
        return footballPayload;
    }

    public static String getUserPayload(int id, String username, String firstName, String lastName, String email,
    String password, String phone, int userStatus){
        String userPayload="[\n" +
                "  {\n" +
                "    \"id\": "+id+",\n" +
                "    \"username\": \""+username+"\",\n" +
                "    \"firstName\": \""+firstName+"\",\n" +
                "    \"lastName\": \""+lastName+"\",\n" +
                "    \"email\": \""+email+"\",\n" +
                "    \"password\": \""+password+"\",\n" +
                "    \"phone\": \""+phone+"\",\n" +
                "    \"userStatus\": "+userStatus+"\n" +
                "  }\n" +
                "]";
        return userPayload;
    }

}
