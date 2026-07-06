package com.codeit.misstionanswer.dto.request;

public record UserUpdateRequest(
        String newUsername,
        String newEmail,
        String newPassword
) {
}
