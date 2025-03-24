package pages.nasa;

//Imports
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

//Declare static constants to be used, including paths for testData

public class ApiNasaTest {

    //TODO: create and move to ENUM
    public static final String URL_GOOGLE = "https://www.google.com";
    public static final String URL_NASA = "https://api.nasa.gov";
    //Endpoints
    public static final String APOD_BASE = "https://api.nasa.gov/planetary/apod";
    public static final String APOD_PARAM_API_KEY = "api_key";
    public static final String APOD_PARAM_API_KEY_VALUE = "DEMO_KEY";

    @Test
    public void restfulTest() {
        //Using GET response
        RequestSpecification request = RestAssured.given()
                .baseUri(APOD_BASE)
                .queryParam(APOD_PARAM_API_KEY, APOD_PARAM_API_KEY_VALUE);
        Response response = request.get();
        System.out.println("\n" + response.getBody().asString());
    }
}