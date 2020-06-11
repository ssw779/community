package com.zb.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.pojo.Content;
import com.zb.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * 轮询广告
 *
 * @author 孙硕威
 */
@RestController
@CrossOrigin
public class StoreBanner {
    @Autowired
    private RestTemplate restTemplate;


    /**
     * 查询轮询广告，通过lua多级缓存
     */
    @GetMapping(value = "/findStoreBanner/{id}")
    public Dto findStoreBanner(@PathVariable("id") Integer id) {
        //从d:myLua/bannerReader.lua文件里读取信息,里面有限相应的操作
        String url = "http://localhost:9000/bannerReader?id=" + id;
        List<Content> contentList = restTemplate.getForObject(url, List.class);
        return DtoUtil.returnSuccess("success", contentList);
    }

    @GetMapping(value = "findStore/{storeType}/{shopParenType}")
    public Dto findStore(@PathVariable("storeType") Integer storeType, @PathVariable("shopParenType") Integer shopParenType) {
        String url = "http://localhost:9000/recReader?storeType=" + storeType+"&shopParenType="+shopParenType;
        List<Store> storeList = restTemplate.getForObject(url, List.class);
        return DtoUtil.returnSuccess("success", storeList);
    }


}
