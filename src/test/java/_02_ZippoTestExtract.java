import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class _02_ZippoTestExtract {
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

    @Test
    public void extractingJsonPath4 () {
     int limit =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .extract().path("meta.pagination.limit")
                ;

        System.out.println("limit  = " + limit);
      Assert.assertTrue(limit==10);

    }
    @Test
    public void extractingJsonPath5() {
        int id
                =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .extract().path("data.id");
        System.out.println("i = " + id);

    }

        @Test
    public void extractingJsonPath15() {
            List<Integer> idler =
                    given()
                            .when()
                            .get("https://gorest.co.in/public/v1/users")
                            .then()
                            .extract().path("data.id");
            System.out.println("i = " + idler);

        }



    @Test
    public void extractingJsonPath6(){
        List    <String> isimler
                =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .extract().path("data.name");
        System.out.println("i = " + isimler);





    }    @Test
    public void extractingJsonPathResponceAll(){
        Response donenData=
                 given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .extract().response();

        List    <Integer> idler=donenData.path("data.id");
        List    <String> isimler=donenData.path("data.name");
        int limit=donenData.path("meta.pagination.limit");
        System.out.println("isimler = " + isimler);
        System.out.println("idler = " + idler);
        System.out.println("limit = " + limit);
        Assert.assertTrue(isimler.contains("Mahesh Menon"));
        Assert.assertTrue(idler.contains(5599126));
        Assert.assertTrue(limit==10);





    }
}



