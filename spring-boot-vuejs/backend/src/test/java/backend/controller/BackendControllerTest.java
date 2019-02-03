package backend.controller;

import backend.SpringBootVuejsApplication;
import backend.domain.User;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = SpringBootVuejsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class BackendControllerTest {

    @LocalServerPort
    private int port;

    @Before
    public void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void sayHello() {
        when()
                .get("/api/hello")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(is(equalTo(BackendController.HELLO_TEXT)));
    }

    @Test
    public void addNewUserAndRetrieveItBack() {

        User testUser = new User("Peter", "Parker");

        Long userId =
                given()
                        .queryParam("firstName", "Peter")
                        .queryParam("lastName", "Parker")
                .when()
                    .post("/api/user")
                .then()
                    .statusCode(is(HttpStatus.SC_CREATED))
                    .extract()
                        .body().as(Long.class);

        User repsonseUser =
                given()
                    .pathParam("id", userId)
                .when()
                    .get("/api/user/{id}")
                .then()
                    .statusCode(is(HttpStatus.SC_OK))
                    .assertThat()
                        .extract().as(User.class);

        // Did SpiderMan came back?
        assertThat(repsonseUser.getFirstName(), is("Peter"));
        assertThat(repsonseUser.getLastName(), is("Parker"));
    }
}