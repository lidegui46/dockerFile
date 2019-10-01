package com.docker.file;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
        System.out.println("localIP: " + getLocalIP() + "    url: " + request.getRequestURL().toString());
        return "url:" + request.getRequestURL().toString() + " hello " + arg;
    }


    public static String getLocalIP() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // TODO自动生成的catch块
            e.printStackTrace();
        }
        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        // System.out.println(ipAddrStr）;
        return ipAddrStr;

    }