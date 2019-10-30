package com.silianbo.springcloud.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bo
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello Word!";
    }
}
