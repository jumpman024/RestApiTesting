package demoproject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class DemoProject {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";

        Response res = given().
                header("Content-Type", "application/json").
                body(PayLoad.addBook(isbn,aisle)).
                when().
                post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);
        String id = js.get("ID");
        String resp = res.asString();
        System.out.println(id);
        System.out.println(resp);

        given().header("Content-Type","application/json").
                body("{\n" +
                        " \n" +
                        "\"ID\" : \"ID\"\n" +
                        " \n" +
                        "}").
                when().
                post("/Library/DeleteBook.php").
                then().assertThat().statusCode(200).
                extract().response();
    }

    @Test
    public void deleteBook(){
        Response res = given().
                header("Content-Type","application/json").
                body("{\n" +
                        " \n" +
                        "\"ID\" : \"ioiio9989\"\n" +
                        " \n" +
                        "}").
                when().
                post("/Library/DeleteBook.php").
                then().assertThat().statusCode(200).
                extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);
        String id = js.get("ID");
        String resp = res.asString();
        System.out.println(id);
        System.out.println(resp);
    }



    @DataProvider(name="BooksData")
    public Object[][] getData(){

        return new Object[][] {{"ioiio","9989"},{"Erwt","8978"},{"edjd","8384"}};
    }
}
