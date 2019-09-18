package com.docker.file;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  ldg
 * Date:    2019/9/17 22:13
 * Desc:    this is file description
 */
@RequestMapping("/test")
@RestController
public class TestController {
    @GetMapping("get")
    public String get(@RequestParam(value = "arg", defaultValue = "") String arg) {
        List<User> list = new ArrayList<>();
        while (true) {
            list.add(new User("aa_", 1));
        }
        //return "hello " + arg;
    }
}

class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
