package org.esgi.boissibook.features.user.infra.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.esgi.boissibook.features.user.infra.validation.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UpdateUserRequest(
    @Schema(description = "The updated user's name", example = "Gregory", required = true)
    @JsonProperty
    @NotBlank
    @Size(min = 1, max = 255)
    String name,
    @Schema(description = "The updated user's email", example = "gregory@mail.com", required = true)
    @JsonProperty
    @NotBlank
    @Email
    String email,
    @Schema(description = "The updated user's password", example = "Password.123")
    @JsonProperty
    @Password
    @Size(min = 8, max = 255)
    String password
) {
}

