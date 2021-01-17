package com.userauth.userauth.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

import static com.userauth.userauth.user.User.user;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatusResourceTest {

    @InjectMocks
    private StatusResource resource;

    @Before
    public void setup() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getPrincipal()).thenReturn(
                user()
                        .withUsername("johnw")
                        .withFirstName("Admin")
                        .withName("Wick")
                        .withAuthorities(Set.of(new SimpleGrantedAuthority("USER")))
                        .withAge(35)
                        .build()
        );
    }

    @Test
    public void status() {
        String status = resource.status();

        assertThat(status).isEqualTo("Hello, johnw");
    }
}
