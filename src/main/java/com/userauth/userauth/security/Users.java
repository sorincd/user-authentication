package com.userauth.userauth.security;

import com.userauth.userauth.user.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class Users {
    private final List<User> users = List.of(
            User.user()
                    .withUsername("user")
                    .withPassword("password")
                    .withFirstName("John")
                    .withName("Wick")
                    .withAge(35)
                    .withAuthorities(Set.of(new SimpleGrantedAuthority("USER")))
                    .build(),
            User.user()
                    .withUsername("admin")
                    .withPassword("password")
                    .withFirstName("Admin")
                    .withName("Nick")
                    .withAuthorities(Set.of(new SimpleGrantedAuthority("ADMIN")))
                    .withAge(40)
                    .build());

    public Optional<User> getUser(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }
}
