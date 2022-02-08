package info.kidslife.user.acceptance;

import info.kidslife.AcceptanceTest;
import info.kidslife.user.application.dto.ChildRequest;
import info.kidslife.user.application.dto.UserRequest;
import info.kidslife.user.application.dto.UserResponse;
import info.kidslife.user.domain.UserType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static info.kidslife.user.UserSteps.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("사용자 인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {

    private UserResponse 부모;
    private UserResponse 자녀;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        부모 = 사용자_저장되어_있음(
                UserRequest.builder()
                        .userType(UserType.PARENT)
                        .email("parent@email.com")
                        .password("1234")
                        .name("parent")
                        .phone("01012345678")
                        .birthday(LocalDate.of(1983, 1, 1))
                        .build()
        );
        자녀 = 사용자_저장되어_있음(
                UserRequest.builder()
                        .userType(UserType.CHILD)
                        .email("child@email.com")
                        .password("1234")
                        .name("child")
                        .phone("01012345678")
                        .birthday(LocalDate.of(2009, 1, 1))
                        .build()
        );
    }

    @Test
    @DisplayName("부모 사용자를 저장한다.")
    void createParent() {
        // given
        final UserRequest userRequest = UserRequest.builder()
                .userType(UserType.PARENT)
                .email("parent@email.com")
                .password("1234")
                .name("parent")
                .phone("01012345678")
                .birthday(LocalDate.of(1983, 1, 1))
                .build();

        // when
        ExtractableResponse<Response> response = 사용자_저장_요청(givenSpec(), userRequest);

        // then
        사용자_저장됨(response);
    }

    @Test
    @DisplayName("자녀 사용자를 저장한다.")
    void createChild() {
        // given
        final UserRequest userRequest = UserRequest.builder()
                .userType(UserType.CHILD)
                .email("child@email.com")
                .password("1234")
                .name("child")
                .phone("01012345678")
                .birthday(LocalDate.of(2009, 1, 1))
                .build();

        // when
        ExtractableResponse<Response> response = 사용자_저장_요청(givenSpec(), userRequest);

        // then
        사용자_저장됨(response);
    }

    @Test
    @DisplayName("사용자를 조회한다.")
    void find() {
        // when
        ExtractableResponse<Response> response = 사용자_조회_요청(givenSpec(), 부모.getId());

        // then
        사용자_조회됨(response);
    }

    @Test
    @DisplayName("자녀를 추가한다.")
    void addChild() {
        // given
        final ChildRequest childRequest = ChildRequest.builder()
                .childId(자녀.getId())
                .build();

        // when
        ExtractableResponse<Response> response = 자녀_추가_요청(givenSpec(), 부모.getId(), childRequest);

        // then
        자녀_추가됨(response);
    }

    private void 사용자_저장됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    private void 사용자_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.as(UserResponse.class)).isNotNull();
    }

    private void 자녀_추가됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
