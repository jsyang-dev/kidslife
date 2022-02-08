package info.kidslife.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@DiscriminatorValue("CHILD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Child extends User {

    @ManyToOne
    private Parent parent;

    @Builder
    private Child(String email, String password, String name, String phone, LocalDate birthday) {
        super(email, password, name, phone, birthday, UserType.CHILD);
    }

    public void changeParent(Parent parent) {
        if (Objects.nonNull(this.parent)) {
            this.parent.getChildren().remove(this);
        }
        this.parent = parent;
        parent.getChildren().add(this);
    }
}
