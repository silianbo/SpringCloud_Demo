package com.silianbo.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("echo")
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return this.restTemplate.getForObject("http://service-nacos-provider/echo/" + str, String.class);
    }
}
