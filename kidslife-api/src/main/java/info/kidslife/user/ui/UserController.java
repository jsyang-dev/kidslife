package info.kidslife.user.ui;

import info.kidslife.user.application.UserService;
import info.kidslife.user.application.dto.ChildRequest;
import info.kidslife.user.application.dto.UserRequest;
import info.kidslife.user.application.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest) {
        final UserResponse userResponse = userService.create(userRequest);
        final URI uri = URI.create("/user/" + userResponse.getId());
        return ResponseEntity.created(uri).body(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> find(@PathVariable Long id) {
        final UserResponse userResponse = userService.find(id);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/{parentId}/child")
    public ResponseEntity<Void> addChild(@PathVariable Long parentId, @RequestBody @Valid ChildRequest childRequest) {
        userService.addChild(parentId, childRequest);
        return ResponseEntity.ok().build();
    }
}
