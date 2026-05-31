package com.mseada.babysitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {

    @RequestMapping(value = {"/", "/home", "/babysitters", "/admin", "/profile/**"})
    public String index() {
        return "forward:/index.html";
    }
}
