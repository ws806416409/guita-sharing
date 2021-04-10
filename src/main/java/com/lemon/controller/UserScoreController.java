package com.lemon.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.pojo.Music;
import com.lemon.pojo.UserScore;
import com.lemon.service.IMusicService;
import com.lemon.service.IUserScoreService;
import com.lemon.utils.SystemContext;
import com.lemon.utils.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @Autowired
    private IMusicService iMusicService;

    @RequestMapping("/upload")
    @ApiOperation(value = "上传曲谱")
    public Object upload(HttpServletRequest request,
                         @RequestParam("type") String type,
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
            //上传到个人曲谱
            UserScore userScore = new UserScore();
            userScore.setAuthor(author);
            userScore.setUid(uid);
            userScore.setImg(visitUrl);
            userScore.setName(name);
            iUserScoreService.save(userScore);
            //分享到曲谱列表中
            Music music = new Music();
            music.setScoreimg(visitUrl);
            music.setMname(name);
            music.setSinger(author);
            music.setType(type);
            iMusicService.save(music);
            jsonObject.put("msg","success");
        }else{
            jsonObject.put("msg","false");
        }
        return jsonObject;
    }

    @GetMapping("/getMySheet")
    @ApiOperation(value = "获取用户上传的曲谱", notes = "传入id, 返回MySheet")
    public Object getUserScore(@RequestParam Integer id) {
        QueryWrapper<UserScore> qu = new QueryWrapper<>();
        qu.eq("id",id);
        List<UserScore> list = iUserScoreService.list(qu);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MySheet",list);
        return jsonObject;
    }
}

