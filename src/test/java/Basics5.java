import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class Basics5 {

    @Test
    public void Test() {

        RestAssured.baseURI="https://maps.googleapis.com";

        Response res = given().
                param("location","-33.8670522,151.1957362").
                param("radius","500").
                param("key","AIzaSyDmF5ygsr2uscjA88dE_9wmOqibXq3QVMU").log().all().
                when().
                get("maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                //body("results[0].name", equalTo("Sydney")).and().
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
                header("Server","scaffolding on HTTPServer2").log().all().
                extract().response();
                JsonPath js = ReusableMethods.rawToJSON(res);
                int count = js.get("results.size()");
                for(int i=0;i<count;i++){

                    System.out.println(js.get("results["+i+"].name"));
                }
                System.out.println(count);

        //REST API simple assertions
        //Status code of the response
        //Content-Type
        //Body
    }

}
