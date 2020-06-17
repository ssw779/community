package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.feign.GoodsFeignClient;
import com.zb.pojo.Goods;
import com.zb.service.GoodsSalesVolumeService;
import com.zb.service.impl.GoodsSalesVolumeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class OrdersController {

    @Autowired
    private GoodsSalesVolumeServiceImpl goodsSalesVolumeService;

}
