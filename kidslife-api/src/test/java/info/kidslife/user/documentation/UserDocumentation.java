package info.kidslife.user.documentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.kidslife.Documentation;
import info.kidslife.user.application.UserService;
import info.kidslife.user.application.dto.ChildRequest;
import info.kidslife.user.application.dto.UserRequest;
import info.kidslife.user.application.dto.UserResponse;
import info.kidslife.user.domain.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.time.LocalDate;

import static info.kidslife.user.UserSteps.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

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
        final UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .userType(UserType.PARENT)
                .email("parent@email.com")
                .password("1234")
                .name("parent")
                .phone("01012345678")
                .birthday(LocalDate.of(1983, 1, 1))
                .build();
        final UserRequest userRequest = objectMapper.convertValue(userResponse, UserRequest.class);
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
        사용자_저장_요청(
                givenSpec(
                        "createUser",
                        requestFields(requestFieldDescriptors),
                        responseFields(responseFieldDescriptors)
                ),
                userRequest
        );
    }

    @Test
    @DisplayName("사용자를 조회한다.")
    void find() {
        // given
        final UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .userType(UserType.PARENT)
                .email("parent@email.com")
                .password("1234")
                .name("parent")
                .phone("01012345678")
                .birthday(LocalDate.of(1983, 1, 1))
                .build();
        given(userService.find(anyLong())).willReturn(userResponse);

        ParameterDescriptor[] pathParameterDescriptors = {
                parameterWithName("id").description("ID"),
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
        사용자_조회_요청(
                givenSpec(
                        "findUser",
                        pathParameters(pathParameterDescriptors),
                        responseFields(responseFieldDescriptors)
                ),
                userResponse.getId()
        );
    }

    @Test
    @DisplayName("자녀를 추가한다.")
    void addChild() {
        // given
        ParameterDescriptor[] pathParameterDescriptors = {
                parameterWithName("parentId").description("부모 사용자 ID"),
        };
        FieldDescriptor[] requestFieldDescriptors = {
                fieldWithPath("childId").description("자녀 사용자 ID"),
        };

        // when
        자녀_추가_요청(
                givenSpec(
                        "addChild",
                        pathParameters(pathParameterDescriptors),
                        requestFields(requestFieldDescriptors)
                ),
                1L,
                ChildRequest.builder()
                        .childId(2L)
                        .build()
        );
    }
}
