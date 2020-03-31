package examples;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class TestCoronaApi {

    @DataProvider
    public static Object[][] corona() {
        return new Object[][] {
                {"uk", "deaths", 1408},
                {"spain", "deaths", 7340},
                {"usa", "deaths", 2616},
                {"australia", "deaths", 18},
                {"uae", "deaths", 5},
                {"italy", "deaths", 11591},
                {"netherlands", "deaths", 864}
        };
    }

    @Test
    @UseDataProvider("corona")
    public void testCoronaDeaths(String country, String jpath, Integer expectedvalue) {
        given().pathParam("country", country).
                when().get("https://corona.lmao.ninja/countries/{country}").then().assertThat().body(jpath, equalTo(expectedvalue));
    }
}
