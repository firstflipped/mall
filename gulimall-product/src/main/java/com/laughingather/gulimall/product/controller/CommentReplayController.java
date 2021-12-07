package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.product.service.CommentReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品评价回复关系
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/commentreplay")
public class CommentReplayController {
    @Resource
    private CommentReplayService commentReplayService;


}
