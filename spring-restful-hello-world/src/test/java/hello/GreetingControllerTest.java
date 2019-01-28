package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class GreetingControllerTest {

    @LocalServerPort
    private int port;

    private String name = "Zaphod Beeblebrox";

    @Test
    public void greeting() {

        when()
                .get(String.format("http://localhost:%s/greeting", port))
                .then()
                .statusCode(is(200))
                .body(containsString("Hello, World!"));

        given()
                .param("name", name)
                .when()
                .get(String.format("http://localhost:%s/greeting", port))
                .then()
                .statusCode(is(200))
                .body(containsString(String.format("Hello, %s!", name)));
    }
}