import groovy.transform.ASTTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.testng.annotations.Test;


public class Basics {

    @Test
    public void Test() {

        RestAssured.baseURI="https://maps.googleapis.com";

        given().
                param("location","-33.8670522,151.1957362").
                param("radius","500").
                param("key","AIzaSyDmF5ygsr2uscjA88dE_9wmOqibXq3QVMU").log().all().
                when().
                get("maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].name", equalTo("Sydney")).and().
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
                header("Server","scaffolding on HTTPServer2");

        //REST API simple assertions
        //Status code of the response
        //Content-Type
        //Body
    }

}
