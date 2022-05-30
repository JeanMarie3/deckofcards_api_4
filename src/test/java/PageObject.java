import io.restassured.path.json.JsonPath;
public class PageObject {

    public static JsonPath rowToJson(String response) {
        JsonPath js3 = new JsonPath(response);

        return js3;
    }

}
