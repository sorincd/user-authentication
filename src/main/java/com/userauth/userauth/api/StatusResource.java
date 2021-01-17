package com.userauth.userauth.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/status")
public class StatusResource {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public String status() {
        return "UP";
    }
}
