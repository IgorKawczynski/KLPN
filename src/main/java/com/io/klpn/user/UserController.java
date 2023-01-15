package com.io.klpn.user;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.user.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public UserResponseDTO getUserById(@PathVariable Long userId) {
        return userService.getUserResponseDto(userId);
    }


    @GetMapping("/me")
    public UserEditDTO getUserEditDTOById(@RequestParam Long id) {
        return userService.getUserEditDTOById(id);
    }

    @PutMapping("/my-profile")
    @ResponseStatus(HttpStatus.OK)
    public ErrorsListDTO changeUserById(@RequestParam Long id, @RequestBody UserEditDTO updatedUser){
        return userService.updateUser(id, updatedUser);
    }

    @PostMapping("/register")
    public ErrorsListDTO registerUser(@RequestBody UserRegisterDTO userRegisterDto) {
        return userService.registerUser(userRegisterDto);
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @GetMapping("")
    public List<UserToAcceptDTO> getUsersToAccept() {
        return userService.getUsersToAccept();
    }

    @PostMapping("/update-to-student")
    public ErrorsListDTO updateToStudent(@RequestBody UserUpdateToStudentDTO userUpdateToStudentDto) {
        return userService.updateToStudent(userUpdateToStudentDto);
    }

    @PatchMapping("")
    public ErrorsListDTO updateUserField(@RequestBody UpdateDto updateDto) {
        return userService.updateUserField(updateDto);
    }

    @DeleteMapping("/{userId}")
    public ErrorsListDTO deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

}
