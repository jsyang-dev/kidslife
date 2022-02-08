package info.kidslife.user.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ChildRequest {

    @NotNull
    private Long childId;

    @Builder
    private ChildRequest(Long childId) {
        this.childId = childId;
    }
}
