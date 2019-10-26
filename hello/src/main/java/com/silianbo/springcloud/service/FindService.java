package com.silianbo.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "hello")
public interface FindService {
    @RequestMapping(value = "/s", method = RequestMethod.GET)
    String helloService(@RequestParam("username") String username);
}
