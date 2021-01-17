package com.userauth.userauth.security;

import com.userauth.userauth.user.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .logout()
                .deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .logoutSuccessUrl("/login");
    }

    @Bean
    List<User> users() {
        return List.of(
                User
                        .user()
                        .withUsername("user")
                        .withPassword(passwordEncoder().encode("password"))
                        .withFirstName("John")
                        .withName("Wick")
                        .withAge(35)
                        .withAuthorities(Set.of(new SimpleGrantedAuthority("USER")))
                        .build(),
                User
                        .user()
                        .withUsername("admin")
                        .withPassword(passwordEncoder().encode("password"))
                        .withFirstName("Admin")
                        .withName("Nick")
                        .withAuthorities(Set.of(new SimpleGrantedAuthority("ADMIN")))
                        .withAge(40)
                        .build());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
