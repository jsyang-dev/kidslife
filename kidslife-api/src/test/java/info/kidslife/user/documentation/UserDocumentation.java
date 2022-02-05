package info.kidslife.user.documentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.kidslife.Documentation;
import info.kidslife.user.application.UserService;
import info.kidslife.user.application.dto.UserRequest;
import info.kidslife.user.application.dto.UserResponse;
import info.kidslife.user.domain.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@DisplayName("사용자 문서화")
public class UserDocumentation extends Documentation {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("사용자를 저장한다.")
    void create() {
        // given
        final UserRequest userRequest = UserRequest.builder()
                .userType(UserType.PARENT)
                .email("parent@email.com")
                .password("1234")
                .name("parent")
                .phone("01012345678")
                .birthday(LocalDate.of(1983, 1, 1))
                .build();
        final UserResponse userResponse = objectMapper.convertValue(userRequest, UserResponse.class);
        given(userService.create(any())).willReturn(userResponse);

        FieldDescriptor[] requestFieldDescriptors = {
                fieldWithPath("email").description("이메일(로그인 ID)"),
                fieldWithPath("password").description("비밀번호"),
                fieldWithPath("name").description("이름"),
                fieldWithPath("phone").description("휴대전화번호"),
                fieldWithPath("birthday").description("생일"),
                fieldWithPath("userType").description("사용자 유형(부모:PARENT, 자녀:CHILD)"),
        };
        FieldDescriptor[] responseFieldDescriptors = {
                fieldWithPath("id").description("ID"),
                fieldWithPath("email").description("이메일(로그인 ID)"),
                fieldWithPath("password").description("비밀번호"),
                fieldWithPath("name").description("이름"),
                fieldWithPath("phone").description("휴대전화번호"),
                fieldWithPath("birthday").description("생일"),
                fieldWithPath("userType").description("사용자 유형(부모:PARENT, 자녀:CHILD)"),
        };

        // when
        givenSpec("user-create", requestFields(requestFieldDescriptors), responseFields(responseFieldDescriptors))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userRequest)
                .when().post("/user")
                .then().log().all()
                .extract();

        // then
    }
}
