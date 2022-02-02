package info.kidslife.user.application.dto;

import info.kidslife.user.domain.User;
import info.kidslife.user.domain.UserType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResponse {

    private final Long id;
    private final String email;
    private final String password;
    private final String name;
    private final String phone;
    private final LocalDate birthday;
    private final UserType userType;

    @Builder
    private UserResponse(Long id, String email, String password, String name, String phone, LocalDate birthday, UserType userType) {
        this.id = id;
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
    }

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .birthday(user.getBirthday())
                .build();
    }
}
