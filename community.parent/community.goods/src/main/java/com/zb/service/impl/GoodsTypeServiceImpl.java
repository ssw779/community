package com.zb.service.impl;

import com.zb.mapper.GoodsMapper;
import com.zb.mapper.GoodsTypeMapper;
import com.zb.pojo.Goodstype;
import com.zb.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;


    @Override
    public List<Goodstype> getShoptypeListByPojo(Goodstype goodstype) {
        try {
            return goodsTypeMapper.getShoptypeListByPojo(goodstype);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
