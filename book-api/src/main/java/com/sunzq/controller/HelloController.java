package com.sunzq.controller;

import com.sunzq.GraceJSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("hello")
    public GraceJSONResult hello(){
        return GraceJSONResult.ok("hello1");
    }
    @GetMapping("hello1")
    public GraceJSONResult hello2(){
        return GraceJSONResult.ok("hello1");
    }
}
