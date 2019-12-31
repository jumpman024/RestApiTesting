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

public class Basic8 {

    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\Kostya\\IdeaProjects\\RestAPITest\\env.properties");
        prop.load(fis);
        // prop.get("HOST");
    }

    @Test
    public void JiraAPI(){

        // Creating Issue/Defect
        RestAssured.baseURI = "http://localhost:8080";
        String issueId = ReusableMethods.createIssue();
        String commentId = ReusableMethods.createComment(issueId);
        Response res = given().header("Content-Type","application/json").
                header("Cookie","JSESSIONID="+ReusableMethods.getSessionKEY()).
                body("{ \"body\": \"Updating 19189839389389 comment from the automation code\","+
                        "\"visibility\": {"+
                        "\"type\": \"role\","+
                        "\"value\": \"Administrators\" }"+
                        "}").when().
                put("/rest/api/2/issue/"+issueId+"/comment/"+ commentId).then().statusCode(200).extract().response();




    }
}


