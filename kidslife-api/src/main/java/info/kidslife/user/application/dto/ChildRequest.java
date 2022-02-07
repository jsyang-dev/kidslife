package info.kidslife.user.application.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ChildRequest {

    @NotNull
    private Long childId;

    @Builder
    private ChildRequest(Long childId) {
        this.childId = childId;
    }
}
