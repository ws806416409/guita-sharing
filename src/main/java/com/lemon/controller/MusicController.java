package com.lemon.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.pojo.Music;
import com.lemon.service.IMusicService;
import com.lemon.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/music")
@Api(value = "曲谱管理", tags = "Swagger曲谱管理API")
public class MusicController {

    @Autowired
    private IMusicService iMusicService;

    @RequestMapping("/filterByType")
    @ApiOperation(value = "通过类别筛选", notes = "传入一个字符串类型的type")
    public Object filterByType(@RequestParam String type) {
        QueryWrapper<Music> qu = new QueryWrapper<>();
        qu.eq("type",type);
        List<Music> musicList = iMusicService.list(qu);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("musicList",musicList);
        return jsonObject;
    }

    @RequestMapping("getSingerList")
    @ApiOperation(value = "获取歌手列表")
    public Object getSingerList(){
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<Music> qu = new QueryWrapper<>();
        qu.select("singer");
        qu.groupBy("singer");
        List<Music> list = iMusicService.list(qu);
        List<String> singerList = new ArrayList<>();
        for (Music music : list) {
            singerList.add(music.getSinger());
        }
        jsonObject.put("singerList",singerList);
        return jsonObject;
    }


    @RequestMapping("/filterBySinger")
    @ApiOperation(value = "通过歌手筛选曲谱", notes = "传入一个字符串类型的singer")
    public Object filterBySinger(@RequestParam("singer") String singer){
        QueryWrapper<Music> qu = new QueryWrapper<>();
        qu.eq("singer",singer);
        List<Music> list = iMusicService.list(qu);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("SheetList",list);
        return jsonObject;
    }



    @RequestMapping("/getSheetInfo")
    @ApiOperation(value = "查看曲谱详情", notes = "传入曲谱主键id")
    public Object getInfo(@RequestParam Integer sheetId){
        //查看详情
        Music sheetInfo = iMusicService.getById(sheetId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sheetInfo",sheetInfo);
        return jsonObject;
    }

    @GetMapping("/findAll")
    public Object find(){
        List<Music> list = iMusicService.list();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("all",list);
        return jsonObject;
    }

}

