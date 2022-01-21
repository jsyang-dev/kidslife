package info.kidslife.user.acceptance;

import info.kidslife.AcceptanceTest;
import info.kidslife.user.application.dto.UserRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.*;

@DisplayName("사용자 인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("부모 사용자를 저장한다.")
    void createParent() {
        // given
        UserRequest userRequest = new UserRequest();

        // when
        ExtractableResponse<Response> response = post("/user", userRequest);

        // then
        사용자_저장됨(response);
    }

    @Test
    @DisplayName("자녀 사용자를 저장한다.")
    void createChild() {
        // given
        UserRequest userRequest = new UserRequest();

        // when
        ExtractableResponse<Response> response = post("/user", userRequest);

        // then
        사용자_저장됨(response);
    }

    private void 사용자_저장됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }
}
