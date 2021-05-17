package com.laughingather.gulimall.search.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author WangJie
 */
@Controller
public class SearchController {

    @GetMapping("/list.html")
    public String list() {
        return "list";
    }

}
