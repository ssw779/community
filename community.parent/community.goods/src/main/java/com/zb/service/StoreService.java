package com.zb.service;

import com.zb.pojo.Store;
import com.zb.vo.StoreVo;

import java.util.List;

public interface StoreService {
    /**
     * 来个定时任务，在零点修改商家评分
     */
    /**
     * 多条件查询店铺，包括店铺图片
     * @param goodsVo
     * @return
     * @throws Exception
     */
    public List<Store> getStoreListByVo(StoreVo goodsVo) throws Exception;
}
