package com.lemon.controller;


import com.lemon.service.IUserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-04-08
 */
@Controller
@RequestMapping("/userScore")
public class UserScoreController {

    @Autowired
    private IUserScoreService iUserScoreService;

//    @RequestMapping("/upload")
//    public String upload(@RequestPart("musicImg") MultipartFile musicImg)throws IOException {
//
//    }
}

