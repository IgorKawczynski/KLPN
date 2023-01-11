package com.io.klpn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.io.klpn.user.UserRepository;
import com.io.klpn.user.User;

@Service
public class CurrentUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override //Nazwa metody `LoadByUsername` ze wzgledu na to, ze to metoda pobierana z interfejsu UserDetailsService
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            final CurrentUser currentUser = new CurrentUser();
            currentUser.setId(user.getId());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(user.getPassword());
            return currentUser;
        }
        else
            throw new UsernameNotFoundException("Failed to find user with email: " + email);
    }
}
