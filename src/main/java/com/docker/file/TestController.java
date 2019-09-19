package com.docker.file;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        fullGC();
        return "hello " + arg;
    }

    private void fullGC() {
        byte[] newErdo = new byte[1024 * 1024 * 30];

        while (true) {
            // 触发 full gc
            System.gc();
            byte[] bytes = new byte[1024 * 1024 * 120];
        }
    }
}