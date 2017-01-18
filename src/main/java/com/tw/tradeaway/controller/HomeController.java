package com.tw.tradeaway.controller;

import com.tw.tradeaway.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by prateeks on 1/13/17.
 */

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService service;

    @RequestMapping("/home")
    public String home(){
        Logger logger = LoggerFactory.getLogger(HomeController.class);
        logger.error("we will explain");
        return "bootcamp";
    }

//    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
//    public User getUser(@PathVariable("id") Long id){
//        return service.get(id);
//    }
}
