package org.esgi.boissibook.features.user.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.user.domain.UserCommandHandler;
import org.esgi.boissibook.features.user.infra.web.request.CreateUserRequest;
import org.esgi.boissibook.features.user.infra.web.request.UpdateUserRequest;
import org.esgi.boissibook.features.user.infra.web.response.UserIdResponse;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "User controller", description = "Users features")
@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserCommandController {

    private final UserCommandHandler userCommandHandler;

    public UserCommandController(UserCommandHandler userCommandHandler) {
        this.userCommandHandler = userCommandHandler;
    }


    @Operation(summary = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = UserIdResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user form",
                    content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<UserIdResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        var createUser = UserWebMapper.toUser(createUserRequest);
        var userId = userCommandHandler.createUser(createUser);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new UserIdResponse(userId));
    }

    @Operation(summary = "Update existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user form",
                    content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
            )
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id,
                                           @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        var updateUser = UserWebMapper.toUser(id, updateUserRequest);
        userCommandHandler.updateUser(updateUser);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
            )
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userCommandHandler.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Delete all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = UserIdResponse.class))
            )
    })
    @DeleteMapping(value = "/")
    public ResponseEntity<Void> deleteAllUser() {
        userCommandHandler.deleteAllUsers();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
