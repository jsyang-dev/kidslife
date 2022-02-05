package info.kidslife;

import info.kidslife.utils.DatabaseCleanup;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@EnableJpaAuditing
public abstract class AcceptanceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
//        databaseCleanup.execute();
    }

    public static ExtractableResponse<Response> get(String uri, Object... params) {
        return givenSpec()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get(uri, params)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> post(String uri, T body, Object... params) {
        return givenSpec()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .when().post(uri, params)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> put(String uri, T body, Object... params) {
        return givenSpec()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .when().put(uri, params)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> delete(String uri, Object... params) {
        return givenSpec()
                .when().delete(uri, params)
                .then().log().all()
                .extract();
    }

    private static RequestSpecification givenSpec() {
        return RestAssured
                .given().log().all();
    }
}
