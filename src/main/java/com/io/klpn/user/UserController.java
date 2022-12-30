package com.io.klpn.user;

import com.io.klpn.user.dtos.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public String registerUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.registerUser(userCreateDto);
    }

}
