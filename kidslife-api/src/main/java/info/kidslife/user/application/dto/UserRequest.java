package info.kidslife.user.application.dto;

import info.kidslife.user.domain.Child;
import info.kidslife.user.domain.Parent;
import info.kidslife.user.domain.User;
import info.kidslife.user.domain.UserType;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class UserRequest {

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;

    @NotBlank
    private final String name;

    @NotBlank
    @Length(min = 10, max = 11)
    private final String phone;

    @NotNull
    private final LocalDate birthday;

    @NotNull
    private final UserType userType;

    @Builder
    private UserRequest(String email, String password, String name, String phone, LocalDate birthday, UserType userType) {
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
    }

    public User toUser() {
        if (userType.isParent()) {
            return Parent.builder()
                    .email(email)
                    .password(password)
                    .name(name)
                    .phone(phone)
                    .birthday(birthday)
                    .build();
        }
        if (userType.isChild()) {
            return Child.builder()
                    .email(email)
                    .password(password)
                    .name(name)
                    .phone(phone)
                    .birthday(birthday)
                    .build();
        }
        return null;
    }
}
