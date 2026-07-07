package com.codeit.misstionanswer.controller;

import com.codeit.misstionanswer.controller.api.*;
import com.codeit.misstionanswer.dto.data.*;
import com.codeit.misstionanswer.dto.request.*;
import com.codeit.misstionanswer.entity.*;
import com.codeit.misstionanswer.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;


@RequiredArgsConstructor
@Controller
@ResponseBody
@RequestMapping("/api/users")
public class UserController implements UserApi {
    private final UserService userService;
    private final UserStatusService userStatusService;

    @RequestMapping(
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<User> create(
            @RequestPart("userCreateRequest") UserCreateRequest userCreateRequest,
            @RequestPart(value = "profile", required = false) MultipartFile profile
    ) {
        Optional<BinaryContentCreateRequest> profileRequest = Optional.ofNullable(profile).flatMap(this::resolveProfileRequest);
        User createdUser = userService.create(userCreateRequest, profileRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    @RequestMapping(
            path = "update",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<User> update(
            @RequestParam("userId") UUID userId,
            @RequestPart("userUpdateRequest") UserUpdateRequest userUpdateRequest,
            @RequestPart(value = "profile", required = false) MultipartFile profile
    ) {
        Optional<BinaryContentCreateRequest> profileRequest = Optional.ofNullable(profile).flatMap(this::resolveProfileRequest);
        User updatedUser = userService.update(userId, userUpdateRequest, profileRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedUser);
    }

    @RequestMapping(path = "delete")
    public ResponseEntity<Void> delete(@RequestParam("userId") UUID userId) {
        userService.delete(userId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @RequestMapping(path = "findAll")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> users = userService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @RequestMapping(path = "updateUserStatusByUserId")
    public ResponseEntity<UserStatus> updateUserStatusByUserId(@RequestParam("userId") UUID userId, @RequestBody UserStatusUpdateRequest request) {
        UserStatus updatedUserStatus = userStatusService.updateByUserId(userId, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedUserStatus);
    }

    private Optional<BinaryContentCreateRequest> resolveProfileRequest(MultipartFile profileFile) {
        if (profileFile.isEmpty()) {
            return Optional.empty();
        } else {
            try {
                BinaryContentCreateRequest binaryContentCreateRequest = new BinaryContentCreateRequest(
                        profileFile.getOriginalFilename(),
                        profileFile.getContentType(),
                        profileFile.getBytes()
                );
                return Optional.of(binaryContentCreateRequest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

