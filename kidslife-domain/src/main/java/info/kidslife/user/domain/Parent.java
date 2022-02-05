package info.kidslife.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("PARENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parent extends User {

    @Builder
    private Parent(String email, String password, String name, String phone, LocalDate birthday) {
        super(email, password, name, phone, birthday, UserType.PARENT);
    }
}
