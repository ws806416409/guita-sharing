package com.lemon.controller;


import com.alibaba.fastjson.JSONObject;
import com.lemon.pojo.UserScore;
import com.lemon.service.IUserScoreService;
import com.lemon.utils.SystemContext;
import com.lemon.utils.UUIDUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/userScore")
@Api(value = "用户曲谱管理", tags = "用户曲谱管理API")
public class UserScoreController {

    @Autowired
    private IUserScoreService iUserScoreService;

    @RequestMapping("/upload")
    public Object upload(HttpServletRequest request,
                         @RequestPart("musicImg") MultipartFile file,
                         @RequestParam("uid") Integer uid,
                         @RequestParam("name") String name,
                         @RequestParam("author") String author)throws IOException {
        JSONObject jsonObject = new JSONObject();
        if(!file.isEmpty()){
            String n = UUIDUtils.create();
            //            System.out.println(System.getProperty("user.dir"));D:\guita-sharing
            String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\upload\\" + n + file.getOriginalFilename();
            File newFile = new File(path);
            file.transferTo(newFile);
            String visitUrl = "/upload/"+ n +file.getOriginalFilename();
            UserScore userScore = new UserScore();
            userScore.setAuthor(author);
            userScore.setUid(uid);
            userScore.setImg(visitUrl);
            userScore.setName(name);
            iUserScoreService.save(userScore);
            jsonObject.put("msg","success");
        }else{
            jsonObject.put("msg","false");
        }
        return jsonObject;
    }
}

