package org.rezatron.springboot_war_test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

@Value("${rez.test}")
private String testValueString;

    @GetMapping("")
    public String hello() {
        log.info("YOU HIT THE END POINT {value}", testValueString);
        return "Hello, World! from " + testValueString;
    }
}
