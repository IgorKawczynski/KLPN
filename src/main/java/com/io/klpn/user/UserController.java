package com.io.klpn.user;

import com.io.klpn.basic.ErrorsListDto;
import com.io.klpn.user.dtos.UserCreateDto;
import com.io.klpn.user.dtos.UserResponseDto;
import com.io.klpn.user.dtos.UserUpdateDto;
import com.io.klpn.user.dtos.UserUpdateToStudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return userService.getUserResponseDto(userId);
    }

    @PostMapping("")
    public ErrorsListDto registerUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.registerUser(userCreateDto);
    }

    @PostMapping("/update-to-student")
    public ErrorsListDto updateToStudent(@RequestBody UserUpdateToStudentDto userUpdateToStudentDto) {
        return userService.updateToStudent(userUpdateToStudentDto);
    }

    @PatchMapping("")
    public ErrorsListDto updateUserField(@RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUserField(userUpdateDto);
    }

    @DeleteMapping("/{userId}")
    public ErrorsListDto deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

}
