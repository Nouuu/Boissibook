package org.esgi.boissibook.features.user.infra.web;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.domain.UserService;
import org.esgi.boissibook.features.user.infra.web.request.CreateUserRequest;
import org.esgi.boissibook.features.user.infra.web.request.UpdateUserRequest;
import org.esgi.boissibook.features.user.infra.web.response.UserResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        var users = userService.getUsers();
        return ResponseEntity.ok(
                users.stream().map(UserResponse::fromUser).toList()
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") String id) {
        var user = userService.getUser(id);
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest) {
        var createUser = new User()
                .setEmail(createUserRequest.email())
                .setName(createUserRequest.name())
                .setPassword(createUserRequest.password());
        var userId = userService.createUser(createUser);
        return ResponseEntity.ok(userId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody UpdateUserRequest updateUserRequest) {
        var updateUser = new User()
                .setId(id)
                .setEmail(updateUserRequest.email())
                .setName(updateUserRequest.name())
                .setPassword(updateUserRequest.password());
        userService.updateUser(updateUser);
        return ResponseEntity.ok().build();
    }
}
