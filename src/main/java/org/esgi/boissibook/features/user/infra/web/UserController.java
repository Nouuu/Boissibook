package org.esgi.boissibook.features.user.infra.web;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.domain.UserCommandHandler;
import org.esgi.boissibook.features.user.domain.UserQueryHandler;
import org.esgi.boissibook.features.user.infra.web.request.CreateUserRequest;
import org.esgi.boissibook.features.user.infra.web.request.UpdateUserRequest;
import org.esgi.boissibook.features.user.infra.web.response.UserIdResponse;
import org.esgi.boissibook.features.user.infra.web.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserCommandHandler userCommandHandler;
    private final UserQueryHandler userQueryHandler;

    public UserController(UserCommandHandler userCommandHandler, UserQueryHandler userQueryHandler) {
        this.userCommandHandler = userCommandHandler;
        this.userQueryHandler = userQueryHandler;
    }

    /**
     * It gets a list of users from the user service, and returns a response entity with a list of user responses
     *
     * @return A list of UserResponse objects
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        var users = userQueryHandler.getUsers();
        return ResponseEntity.ok(
                users.stream().map(UserResponse::fromUser).toList()
        );
    }

    /**
     * Get the user with the given id and return it as a UserResponse object.
     *
     * @param id The id of the user we want to retrieve.
     * @return ResponseEntity<UserResponse>
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") String id) {
        var user = userQueryHandler.getUser(id);
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }

    /**
     * "Create a user with the given email, name, and password, and return the user's ID."
     *
     * @param createUserRequest The request body that is sent to the endpoint.
     * @return ResponseEntity<UserIdResponse>
     */
    @PostMapping
    public ResponseEntity<UserIdResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        var createUser = new User()
                .setEmail(createUserRequest.email())
                .setName(createUserRequest.name())
                .setPassword(createUserRequest.password());
        var userId = userCommandHandler.createUser(createUser);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new UserIdResponse(userId));
    }

    /**
     * Update a user with the given id with the given updateUserRequest.
     *
     * @param id                The id of the user to update
     * @param updateUserRequest The request body that contains the user's email, name, and password.
     * @return ResponseEntity<Void>
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody UpdateUserRequest updateUserRequest) {
        var updateUser = new User()
                .setId(id)
                .setEmail(updateUserRequest.email())
                .setName(updateUserRequest.name())
                .setPassword(updateUserRequest.password());
        userCommandHandler.updateUser(updateUser);
        return ResponseEntity.ok().build();
    }
}
