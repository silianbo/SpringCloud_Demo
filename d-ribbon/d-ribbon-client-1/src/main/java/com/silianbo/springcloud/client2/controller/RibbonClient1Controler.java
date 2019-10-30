package com.silianbo.springcloud.client2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonClient1Controler {
    @Autowired
    Environment environment;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "s")
    public String helloServer() {
        String port = environment.getProperty("local.server.port");
        return "Hello port= " + port;
    }

    @GetMapping(value = "/hi")
    public String hi() {
        return this.restTemplate.getForObject("http://service-ribbon-client/s/", String.class);
    }
}
