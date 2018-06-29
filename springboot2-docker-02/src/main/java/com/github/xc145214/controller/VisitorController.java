package com.github.xc145214.controller;

import com.github.xc145214.entity.Visitor;
import com.github.xc145214.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiac
 * @date: 2018/6/28
 * @desc: 一句话描述
 */
@Controller
public class VisitorController {

    @Autowired
    private VisitorRepository repository;

    @RequestMapping("/")
    @ResponseBody
    public String index(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Visitor visitor = repository.findByIp(ip);
        if (visitor == null) {
            visitor = new Visitor();
            visitor.setIp(ip);
            visitor.setTimes(1);
        } else {
            visitor.setTimes(visitor.getTimes() + 1);
        }
        repository.save(visitor);
        return "I have been seen ip " + visitor.getIp() + " " + visitor.getTimes() + " times.";
    }

}
