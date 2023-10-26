import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ZippoTestExtract {
/*
    REST Assured
    Build Status Maven Central

    Testing and validation of REST services in Java is harder than in dynamic languages such as Ruby and Groovy.
    REST Assured brings the simplicity of using these languages into the Java domain.


            News
2023-09-08: REST Assured 5.3.2 is released with several bug fixes, better Spring MockMvc Support,
 better support for newer versions of Spring Security etc. See change log for more details.

            2023-06-16: REST Assured 5.3.1 is released with several bug fixes and dependency upgrades. See change log for more details.
            2022-11-18: REST Assured 5.3.0 is released. It adds CSRF header support and a much improved CSRF support in general.
    It also brings the Spring MockMvc Module up-to-date with changes in Spring Framework 6 and Spring Boot 3,
    as well as various other bug fixes and improvements. See changelog for more details.

*/

    @Test //negative Test
    public void extractingJsonPath () {
        String countryName =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210") // http hok ise baseUri baş tarafına gelir.
                        .then()
                        .extract().path("country")
                ;
        System.out.println("countryName = " + countryName);
        Assert.assertEquals(countryName,"Canada");
    }

    @Test
    public void extractingJsonPath2 () {
        String stateName =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210") // http hok ise baseUri baş tarafına gelir.
                        .then()
                        .extract().path("places[0].state")
                ;
        System.out.println("countryName = " + stateName);
        Assert.assertEquals(stateName,"California");
    }

    @Test
    public void extractingJsonPath3 () {
        String placeName =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210") // http hok ise baseUri baş tarafına gelir.
                        .then()
                        .extract().path("places[0].'place name'")
                ;
        Assert.assertEquals(placeName,"Beverly Hills");
        System.out.println("placeName = " + placeName);
        Assert.assertEquals(placeName,"California");

    }

}




