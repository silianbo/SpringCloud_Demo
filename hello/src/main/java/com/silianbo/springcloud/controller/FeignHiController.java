package com.silianbo.springcloud.controller;

import com.silianbo.springcloud.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8080/s?username=silianob
 */
@RestController
public class FeignHiController {
    @Autowired
    private FindService findService;

    @GetMapping(value = "s")
    public String helloServer(String username) {
        return "Hello username=" + username + "!";
    }

    @GetMapping("/find/s")
    String findService(String username) {
        try {
            return "find" + findService.helloService(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }
}
