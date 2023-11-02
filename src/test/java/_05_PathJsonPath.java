import Model.Location;
import Model.Place;
import Model.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.List;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _05_PathJsonPath {
    @Test
    public void extractingPath() // int stringden donusum
    {
        int postCode=

    given()
            .when()
            .get("http://api.zippopotam.us/us/90210")

            .then()
            .extract().jsonPath().getInt("'post code'")
    ;
        System.out.println("postCode = " + postCode);

}
    @Test
    public void getZipCode() // int stringden donusum
    {
        Response response=

                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().response()
                ;
       // Daha önceki örneklerde (as) Clas dönüşümleri için tüm yapıya karşılık gelen
        // gereken tüm classları yazarak dönüştürüp istediğimiz elemanlara ulaşıyorduk.

        // Burada ise(JsonPath) aradaki bir veriyi clasa dönüştürerek bir list olarak almamıza
        // imkan veren JSONPATH i kullandık.Böylece tek class ile veri alınmış oldu
        // diğer class lara gerek kalmadan

        // path : class veya tip dönüşümüne imkan veremeyen direk veriyi verir. List<String> gibi
        // jsonPath : class dönüşümüne ve tip dönüşümüne izin vererek , veriyi istediğimiz formatta verir.

        Location locationPathAs=response.as(Location.class);
        System.out.println("locationPathAs.getPlaces() = " + locationPathAs.getPlaces());

        List <Place>placeList=response.jsonPath().getList("places", Place.class);
        System.out.println("placeList = " + placeList);
}
    @Test
    public void getuserV1() // int stringden donusum
    {
       List<User> dataUsers=

                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().jsonPath().getList("data", User.class)
                ;
        System.out.println("postCode = " + dataUsers.get(0).getEmail());
// https://gorest.co.in/public/v1/users  endpointte dönen Sadece Data Kısmını POJO
        // dönüşümü ile alarak yazdırınız.
        for (User u:dataUsers )
            System.out.println("u = " + u);
    }}