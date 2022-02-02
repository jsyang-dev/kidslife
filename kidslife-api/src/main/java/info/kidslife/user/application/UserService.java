package info.kidslife.user.application;

import info.kidslife.user.application.dto.UserRequest;
import info.kidslife.user.application.dto.UserResponse;
import info.kidslife.user.domain.User;
import info.kidslife.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(UserRequest userRequest) {
        final User savedUser = userRepository.save(userRequest.toUser());
        return UserResponse.from(savedUser);
    }
}
