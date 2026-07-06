package com.codeit.misstionanswer.dto.request;

import java.util.*;

public record UserCreateRequest(
        String username,
        String email,
        String password
) {
}
