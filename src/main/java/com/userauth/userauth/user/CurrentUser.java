package com.userauth.userauth.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurrentUser {

    public static UserDetails currentUser() {
        return (UserDetails) authentication().getPrincipal();
    }

    public static Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}