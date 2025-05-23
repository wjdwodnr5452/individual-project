package com.individual.individual_project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        log.info("hello");
        return "Hello World";
    }

}
