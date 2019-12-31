package demoproject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class ReusableMethods {

    public static JsonPath rawToJSON(Response r){

        String respon = r.asString();
        JsonPath x = new JsonPath(respon);
        return x;
    }

    public static String getSessionKEY(){
        // Creating a session
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("Content-Type","application/json").
                body("{ \"username\": \"konstantin.bezsmertnyy\", \"password\": \"Test12345!\" }").
                when().
                post("/rest/auth/1/session").then().statusCode(200).
                extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);
        String sessionId = js.get("session.value");
        return sessionId;
    }

    public static String createIssue(){

        // Creating Issue/Defect
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("Content-Type","application/json").
                header("Cookie","JSESSIONID="+ReusableMethods.getSessionKEY()).
                body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"TEST\"\n" +
                        "       },\n" +
                        "       \"summary\": \"REST ye merry gentlemen.\",\n" +
                        "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}").when().
                post("/rest/api/2/issue").then().statusCode(201).extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);
        String id = js.get("id");
        System.out.println(id);
        System.out.println(res.asString());
        return id;
    }


//    public static TempDemo createIssue2(){
//
//        // Creating Issue/Defect
//        RestAssured.baseURI = "http://localhost:8080";
//        Response res = given().header("Content-Type","application/json").
//                header("Cookie","JSESSIONID="+ReusableMethods.getSessionKEY()).
//                body("{\n" +
//                        "    \"fields\": {\n" +
//                        "       \"project\":\n" +
//                        "       {\n" +
//                        "          \"key\": \"TEST\"\n" +
//                        "       },\n" +
//                        "       \"summary\": \"REST ye merry gentlemen.\",\n" +
//                        "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
//                        "       \"issuetype\": {\n" +
//                        "          \"name\": \"Bug\"\n" +
//                        "       }\n" +
//                        "   }\n" +
//                        "}").when().
//                post("/rest/api/2/issue").then().statusCode(201).extract().response();
//        JsonPath js = ReusableMethods.rawToJSON(res);
//        String id = js.get("id");
//        System.out.println(id);
//        System.out.println(res.asString());
//        return new TempDemo(id, js);
//    }

//    static class TempDemo {
//        public String id;
//        public JsonPath jsonPath;
//
//        public TempDemo(String id, JsonPath jsonPath) {
//            this.id = id;
//            this.jsonPath = jsonPath;
//        }
//    }

    public static String createComment(String issueId) {

        // Creating Issue/Defect
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKEY()).
                body("{ \"body\": \"Inserting comment from the automation code\"," +
                        "\"visibility\": {" +
                        "\"type\": \"role\"," +
                        "\"value\": \"Administrators\" }" +
                        "}").when().
                post("/rest/api/2/issue/"+issueId+"/comment/").then().statusCode(201).extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);
        String id = js.get("id");
        System.out.println(id);
        System.out.println(res.asString());
        return id;
    }


}
