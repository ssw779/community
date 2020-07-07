package com.zb.mapper;

import com.zb.pojo.CommunityTaskHis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityTaskHisMapper {

	public CommunityTaskHis getCommunityTaskHisById(@Param(value = "id") Long id)throws Exception;

	public List<CommunityTaskHis>	getCommunityTaskHisListByMap(Map<String,Object> param)throws Exception;

	public Integer getCommunityTaskHisCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertCommunityTaskHis(CommunityTaskHis communityTaskHis)throws Exception;

	public Integer updateCommunityTaskHis(CommunityTaskHis communityTaskHis)throws Exception;

	public Integer deleteCommunityTaskHisById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteCommunityTaskHis(Map<String,List<String>> params);

}
