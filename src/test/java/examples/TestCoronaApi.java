package examples;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import configuration.Config;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class TestCoronaApi {

    Config config = new Config();

    public TestCoronaApi() throws IOException {
    }

    @DataProvider
    public static Object[][] corona() {
        return new Object[][] {
                {"uk", "deaths", 2352},
                {"spain", "deaths", 9387},
                {"usa", "deaths", 5110},
                {"australia", "deaths", 23},
                {"uae", "deaths", 8},
                {"italy", "deaths", 13155},
                {"netherlands", "deaths", 1173}
        };
    }

    @Test
    @UseDataProvider("corona")
    public void testCoronaDeaths(String country, String jpath, Integer expectedvalue) {
        given().pathParam("country", country).
                when().get("https://corona.lmao.ninja/countries/{country}").then().assertThat().body(jpath, equalTo(expectedvalue));
    }

    @Test
    public void testProperties() throws IOException {

        String prop = config.readProperty("username");
        System.out.println("This is a test for reading properties: " + prop);
            }
    }

