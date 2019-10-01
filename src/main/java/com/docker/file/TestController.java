package com.docker.file;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:  ldg
 * Date:    2019/9/17 22:13
 * Desc:    this is file description
 */
@RequestMapping("/test")
@RestController
public class TestController {
    /**
     * http://localhost:8090/test/get?arg=11
     *
     * @param arg
     * @return
     */
    @GetMapping("get")
    public String get(HttpServletRequest request, @RequestParam(value = "arg", defaultValue = "") String arg) {
        System.out.println();
        return "url:" +  request.getRequestURL().toString() + " hello " + arg;
    }
}