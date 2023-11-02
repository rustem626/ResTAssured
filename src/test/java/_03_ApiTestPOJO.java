import Model.Location;
import Model.Place;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _03_ApiTestPOJO {
    //POJO: JSON nesnesi: LocationNesnesi
    @Test
    public void extractJson_POJO(){

Location locationNesnesi=
    given()
            .when()
            .get("http://api.zippopotam.us/us/90210")
            .then()
            .extract().body().as(Location.class)
        ;
        System.out.println("locationNesnesi.getCountry() = " + locationNesnesi.getCountry());
        System.out.println("locationNesnesi.getPlaces() = "+"\n" + locationNesnesi.getPlaces());

}
    @Test
    public void extractJson_POJO_soru(){

        Location locationNesnesi=
                given()
                        .when()
                        .get("http://api.zippopotam.us/tr/01000")
                        .then()
                        .extract().body().as(Location.class)
                ;
        for (Place p: locationNesnesi.getPlaces()) {
            if (p.getPlacename().equalsIgnoreCase("Dörtağaç Köyü"))
                System.out.println("p = " + p);

        }

    }}
