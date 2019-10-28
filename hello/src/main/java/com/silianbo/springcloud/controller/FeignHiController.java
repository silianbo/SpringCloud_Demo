package com.silianbo.springcloud.controller;

import com.silianbo.springcloud.provider.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8888/s?username=silianob
 * http://localhost:8888/find/s?username=silianob
 *
 * @author bo
 */
@RestController
public class FeignHiController {
    @Autowired
    private FindService findService;
    @Autowired
    Environment environment;

    @GetMapping(value = "s")
    public String helloServer(String username) {
        String port = environment.getProperty("local.server.port");
        return "Hello  username=" + username + "!" + "port= " + port;
    }

    @GetMapping("/find/s")
    String findService(String username) {
        try {
            return "Find " + findService.helloService(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }
}
