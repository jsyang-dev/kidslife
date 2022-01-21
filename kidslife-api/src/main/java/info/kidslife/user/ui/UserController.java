package info.kidslife.user.ui;

import info.kidslife.user.application.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ResponseEntity<UserResponse> create() {
        return ResponseEntity.ok(new UserResponse());
    }
}
