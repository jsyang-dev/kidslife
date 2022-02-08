package info.kidslife.user.application;

import info.kidslife.common.application.EntityNotFoundException;
import info.kidslife.user.application.dto.ChildRequest;
import info.kidslife.user.application.dto.UserRequest;
import info.kidslife.user.application.dto.UserResponse;
import info.kidslife.user.domain.Child;
import info.kidslife.user.domain.Parent;
import info.kidslife.user.domain.User;
import info.kidslife.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private static final String PARENT_ID_NOT_MATCHED_MESSAGE = "parentId는 Parent의 ID여야 합니다.";
    private static final String CHILD_ID_NOT_MATCHED_MESSAGE = "childId는 Child의 ID여야 합니다.";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(UserRequest userRequest) {
        final User user = userRequest.toUser();
        final User savedUser = userRepository.save(user);
        return UserResponse.from(savedUser);
    }

    public UserResponse find(Long id) {
        final User user = findUserById(id);
        return UserResponse.from(user);
    }

    public void addChild(Long parentId, ChildRequest childRequest) {
        Parent parent = findParentById(parentId);
        Child child = findChildById(childRequest.getChildId());
        child.changeParent(parent);
    }

    private Parent findParentById(Long parentId) {
        final User user = findUserById(parentId);
        if (!user.isParent()) {
            throw new IllegalArgumentException(PARENT_ID_NOT_MATCHED_MESSAGE);
        }
        return (Parent) user;
    }

    private Child findChildById(Long childId) {
        final User user = findUserById(childId);
        if (!user.isChild()) {
            throw new IllegalArgumentException(CHILD_ID_NOT_MATCHED_MESSAGE);
        }
        return (Child) user;
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
