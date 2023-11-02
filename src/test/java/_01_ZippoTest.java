import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _01_ZippoTest {

    @Test
    public void test1 () {

        given()
                // Hazırlık işlemleri kodları
                .when()
                // endpoint (url), metod u verip istek gönderiliyor

                .then()
        // assertion, test, data işlemleri
        ;
    }

    @Test
    public void statusCodeTest () {

        given()
                // hazırlık kısmı boş
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body() // dönen body json data , log().all() : gidip gelen her şey
                .statusCode(200) // test kısmı olduğundan assertion status code 200 mü
        ;
    }

    @Test
    public void contentTypeTest () {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body() // dönen body json data , log().all() : gidip gelen her şey
                .statusCode(200) // test kısmı olduğundan assertion status code 200 mü
                .contentType(ContentType.JSON)  // dönen datanın tipi JSON mı
        ;
    }

    @Test
    public void test3 () {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)  // assertion
                .body("country", equalTo("United States")) //assertion
        ;
    }

    @Test
    public void CheckStateResponseBody () {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)  // assertion
                .body("places[0].state", equalTo("California")) //assertion
        ;
    }

    // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
    // place dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
    // olduğunu doğrulayınız
    @Test
    public void CheckHasItem () {

        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                .log().body()
                .statusCode(200)  // assertion
                .body("places.'place name'", hasItem("Dörtağaç Köyü")) //assertion
        ;
    }
// Soru : "http://api.zippopotam.us/us/90210"  endpoint in dönen
    // place dizisinin dizi uzunluğunun 1 olduğunu doğrulayınız.

    @Test
    public void dataLength () {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)  // assertion
                .body("places", hasSize(1)) //assertion
        ;
    }

    @Test
    public void combiningTest () {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .statusCode(200)  // assertion
                .body("places", hasSize(1)) //assertion
                .body("places[0].state", equalTo("California"))
        ;
    }

    @Test
    public void pathParamTest () {

        given()
                .pathParam("ulke", "us")
                .pathParam("postaKod", 90210)
                .log().uri() // tequest link calismadan onceki hali

                .when()
                .get("http://api.zippopotam.us/{ulke}/{postaKod}")
                .then()

                .statusCode(200)  // assertion

        ;
    }

    @Test
    public void queryParamTest () {
        given()
                .param("page", 3)
                .log().uri()
                .when()
                .get("https://gorest.co.in/public/v1/users")
                .then()
                .statusCode(200)
                .log().body()
        ;
        // .setVisibility(android.view.View.GONE);
    }

    @Test
    public void queryTest () {
        for (int page = 1; page <= 10; page++) {
            given()
                    .param("page", page)
                    .log().uri()
                    .when()
                    .get("https://gorest.co.in/public/v1/users")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("meta.pagination.page", equalTo(page));
            ;
        }
    }


    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    @BeforeClass
    public void setup () {
        baseURI = "https://gorest.co.in/public/v1";

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)  // log().uri()
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)  // statusCode(200)
                .log(LogDetail.BODY)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void requestResponseSpecificationn () {
        given()
                .param("page", 1)
                .spec(requestSpec)

                .when()
                .get("/users") // http hok ise baseUri baş tarafına gelir.

                .then()
                .spec(responseSpec)
        ;
    }

    @Test
    public void extractingJsonPath () {
        String countryName =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210") // http hok ise baseUri baş tarafına gelir.
                        .then()
                        .extract().path("country")
                ;
        System.out.println("countryName = " + countryName);
        Assert.assertEquals(countryName,"United States");
    }

}




