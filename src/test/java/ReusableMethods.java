import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {

    public static JsonPath rawToJSON(Response r){

        String respon = r.asString();
        JsonPath x = new JsonPath(respon);
        return x;
    }

}
