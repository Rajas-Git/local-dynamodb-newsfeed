package com.feed.news;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring Boot!";
    }

    @GetMapping("/api/greeting")
    public Greeting getGreeting() {
        return new Greeting("Hello", "world", 2025);
    }

    public record Greeting(String message, String name, int year) {
    }
}
