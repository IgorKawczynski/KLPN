package com.io.klpn.user;

import com.io.klpn.basic.ErrorsListDto;
import com.io.klpn.user.dtos.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ErrorsListDto registerUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.registerUser(userCreateDto);
    }

    @PostMapping("/update-to-student/{userId}")
    public String updateToStudent(@PathVariable Long userId) {
        return null;
    }

}
