package demoproject;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Basic6 {

    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\Kostya\\IdeaProjects\\RestAPITest\\env.properties");
        prop.load(fis);
        // prop.get("HOST");
    }

    @Test
    public static String JiraAPI(){

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
                return id;
    }


}
