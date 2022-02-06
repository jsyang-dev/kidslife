package info.kidslife.user;

import info.kidslife.user.application.dto.UserRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.MediaType;

public class UserSteps {

    public static ExtractableResponse<Response> 사용자_저장_요청(RequestSpecification givenSpec, UserRequest userRequest) {
        return givenSpec
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userRequest)
                .when().post("/user")
                .then().log().all()
                .extract();
    }
}
