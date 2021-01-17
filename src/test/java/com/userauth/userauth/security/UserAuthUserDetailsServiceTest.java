package com.userauth.userauth.security;

import com.userauth.userauth.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAuthUserDetailsServiceTest {

    @InjectMocks
    private UserAuthUserDetailsService userAuthUserDetailsService;
    
    @Mock
    private Users users;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void loadUserByUsername() {
        String encodedPassword = new BCryptPasswordEncoder().encode("password");
        when(passwordEncoder.encode("password")).thenReturn(encodedPassword);
        when(users.getUser("user")).thenReturn(Optional.of(User.user()
                .withUsername("user")
                .withPassword("password")
                .withFirstName("John")
                .withName("Wick")
                .withAge(35)
                .withAuthorities(Set.of(new SimpleGrantedAuthority("USER")))
                .build()));
        UserDetails user = userAuthUserDetailsService.loadUserByUsername("user");

        assertThat(user.getUsername()).isEqualTo("user");
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void givenNotExistingUser_WhenLoadUserByUsername_ThenThrowUsernameNotFoundException() {
        String unknownUser = "unknown-user";
        when(users.getUser(unknownUser)).thenReturn(Optional.empty());
        userAuthUserDetailsService.loadUserByUsername(unknownUser);
    }
}
