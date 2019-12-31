import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class excelDriven {

    @Test
    public void Test() {

        RestAssured.baseURI="http://216.10.245.166";

        Response res = given().
                header("Content-Type","application/json").
                body("{\n" +
                        "\n" +
                        "\"name\":\"Learn Appium Automation with Java\",\n" +
                        "\"isbn\":\"bcwe12eezd\",\n" +
                        "\"aisle\":\"227\",\n" +
                        "\"author\":\"John foe\"\n" +
                        "}").
                when().
                post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response();
                JsonPath js = ReusableMethods.rawToJSON(res);
                String id = js.get("ID");
                String resp = res.asString();
                System.out.println(id);
                System.out.println(resp);

        //REST API simple assertions
        //Status code of the response
        //Content-Type
        //Body
    }

}
