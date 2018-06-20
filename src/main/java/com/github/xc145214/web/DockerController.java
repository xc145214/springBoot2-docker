package com.github.xc145214.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiac
 * @date: 2018/6/20
 * @desc: 一句话描述
 */
@RestController
public class DockerController {

    @RequestMapping("/")
    public String index() {
        return "hello Docker!";
    }
}
