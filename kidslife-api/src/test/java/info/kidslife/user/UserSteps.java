package info.kidslife.user;

import info.kidslife.user.application.dto.UserRequest;
import info.kidslife.user.application.dto.UserResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.MediaType;

import static info.kidslife.AcceptanceTest.*;

public class UserSteps {

    public static final String USER_URI = "/user";

    public static UserResponse 사용자_저장되어_있음(UserRequest userRequest) {
        return 사용자_저장_요청(givenSpec(), userRequest).as(UserResponse.class);
    }

    public static ExtractableResponse<Response> 사용자_저장_요청(RequestSpecification givenSpec, UserRequest userRequest) {
        return givenSpec
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userRequest)
                .when().post(USER_URI)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 사용자_조회_요청(RequestSpecification givenSpec, Long id) {
        return givenSpec
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get(USER_URI + "/{id}", id)
                .then().log().all()
                .extract();
    }
}
