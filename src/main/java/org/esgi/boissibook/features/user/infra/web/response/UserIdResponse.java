package org.esgi.boissibook.features.user.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserIdResponse(@JsonProperty String userId) {
}
