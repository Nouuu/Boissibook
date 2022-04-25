package org.esgi.boissibook.features.user.infra.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateUserRequest(
        @JsonProperty String name,
        @JsonProperty String email,
        @JsonProperty String password) {
}

