package info.kidslife.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("PARENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parent extends User {

    @OneToMany(mappedBy = "parent")
    private List<Child> children;

    @Builder
    private Parent(String email, String password, String name, String phone, LocalDate birthday) {
        super(email, password, name, phone, birthday, UserType.PARENT);
    }
}
