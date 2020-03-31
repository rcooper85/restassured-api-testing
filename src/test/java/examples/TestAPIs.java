package examples;

import api.AuthApi;
import api.BookingApi;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import payloads.AuthPayload;
import payloads.AuthResponsePayload;
import payloads.BookingDatesPayload;
import payloads.BookingPayload;

import java.io.*;
import java.util.Date;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class TestAPIs {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    Properties properties;
    FileInputStream fs;

    //TODO - Move to a helper class and remove from test set.

    public void testProperty() throws IOException {
        fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\configuration\\api.properties");
        properties = new Properties();
        properties.load(fs);
        String test = properties.getProperty("URI");
        System.out.println(test);
    }

    @BeforeClass
    public static void createRequestAndResponseSpecs() {
        requestSpec = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }


    /**
     * DataProvider object to be used to parameterise API tests for zippopotam tests.
     * @return Object containing test data.
     */
    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
        };
    }

    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void myFirstAPITest(String countryCode, String zipCode, String expectedPlaceName) {

        given().spec(requestSpec).pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
        when().
            get("{countryCode}/{zipCode}").
        then().spec(responseSpec).
            assertThat().body("places[0].'place name'", equalTo(expectedPlaceName));
    }

    @Test
    public void mySecondAPITest() {
        given().when().get("http://zippopotam.us/us/90210").then().assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void myThirdAPITest() {
        given().
                log().all().when().get("http://zippopotam.us/us/90210").
                then().log().
                body();
    }

    @Test
    public void myFourthTest() {
        given().
        when().get("http://zippopotam.us/us/90210").
                then().assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void extractFromResponseBody() {
        String placeName = given().spec(requestSpec).when().get("us/90210").then().extract().path("places[0].'place name'");
        Assert.assertEquals("Beverly Hills", placeName);
        System.out.println("The extracted place name is: " + placeName);
    }

}