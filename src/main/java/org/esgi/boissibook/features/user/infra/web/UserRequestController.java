package org.esgi.boissibook.features.user.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.user.domain.UserQueryHandler;
import org.esgi.boissibook.features.user.infra.web.response.UserResponse;
import org.esgi.boissibook.features.user.infra.web.response.UsersCountResponse;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User controller", description = "Users features")
@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRequestController {

    private final UserQueryHandler userQueryHandler;

    public UserRequestController(UserQueryHandler userQueryHandler) {
        this.userQueryHandler = userQueryHandler;
    }

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        var users = userQueryHandler.getUsers();
        return ResponseEntity.ok(
                users.stream().map(UserWebMapper::toUserResponse).toList()
        );
    }

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
            )
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") String id) {
        var user = userQueryHandler.getUser(id);
        return ResponseEntity.ok(UserWebMapper.toUserResponse(user));
    }

    @Operation(summary = "Count users")
    @ApiResponse(responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = UsersCountResponse.class)))
    @GetMapping("/count")
    public ResponseEntity<UsersCountResponse> countUsers() {
        var count = userQueryHandler.countUsers();
        return ResponseEntity.ok(new UsersCountResponse(count));
    }
}
