package com.io.klpn.security;

import com.io.klpn.student.Student;
import com.io.klpn.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.io.klpn.user.UserRepository;
import com.io.klpn.user.User;

import java.util.Optional;

@Service
public class CurrentUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private StudentRepository studentRepository;

    @Autowired
    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override //Nazwa metody `LoadByUsername` ze wzgledu na to, ze to metoda pobierana z interfejsu UserDetailsService
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email);
        final Optional<Student> student = studentRepository.findById(user.getId());
        if (user != null) {
            final CurrentUser currentUser = new CurrentUser();
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(user.getPassword());
            currentUser.setId(user.getId());
            if (student.isPresent()) {
                currentUser.setIsStudent(true);
            }
            return currentUser;
        }
        else
            throw new UsernameNotFoundException("Failed to find user with email: " + email);
    }
}
