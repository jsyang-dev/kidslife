package info.kidslife.common.application;

public class EntityNotFoundException extends RuntimeException {

    private static final String MESSAGE = "엔티티를 찾을 수 없습니다.";

    public EntityNotFoundException() {
        super(MESSAGE);
    }
}
