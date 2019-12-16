import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Basics3 {

    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\Kostya\\IdeaProjects\\RestAPITest\\env.properties");
        prop.load(fis);
       // prop.get("HOST");
    }

    @Test
    public void AddandDeletePlace(){



        // Task 1 Grab the response
        RestAssured.baseURI=prop.getProperty("HOST");

        Response res = given().
                queryParam("key", prop.getProperty("KEY")).
                body(Payload.getPost()).
                when().
                post(Resources.placesPostData()).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status",equalTo("OK")).
                extract().response();




                // Task 2 Grab the Place_id from the response
                JsonPath x = ReusableMethods.rawToJSON(res);
                String placeid = x.get("place_id");
                System.out.println(placeid);

                // Task 3 place this place_id in the Delete request
                given().
                queryParam("key", "qaclick123").
                body("{\n" +
                        "    \"place_id\":\""+placeid+"\"          \n" +
                        "}").
                when().
                post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status",equalTo("OK"));




    }

}
