package com.rngad33.yxsearch.controller;

import com.rngad33.yxsearch.service.PostService;
import com.rngad33.yxsearch.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 搜索接口
 */
@RestController("/search")
public class SearchController {

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

}