package com.laughingather.gulimall.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author WangJie
 */
@Controller
public class IndexController {

//    @GetMapping({"/", "/login.html"})
//    public String loginPage() {
//        return "login";
//    }

    @GetMapping("/register.html")
    public String registerPage() {
        return "register";
    }

}
