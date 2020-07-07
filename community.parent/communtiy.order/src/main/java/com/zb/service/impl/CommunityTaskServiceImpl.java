package com.zb.service.impl;

import com.zb.mapper.CommunityTaskMapper;
import com.zb.pojo.CommunityTask;
import com.zb.service.CommunityTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityTaskServiceImpl implements CommunityTaskService {


    @Autowired
    public CommunityTaskMapper communityTaskMapper;

    @Override
    public int updateTaskversion(CommunityTask communityTask) {
        return communityTaskMapper.updateTaskversion(communityTask);
    }

    @Override
    public int delTask(Integer id) {
        return communityTaskMapper.delTask(id);
    }

    @Override
    public int updateTaskTime(Integer id) {
        return communityTaskMapper.updateTaskTime(id);
    }

    @Override
    public List<CommunityTask> getAminuteAgoTask() {
        return communityTaskMapper.getAMinuteAgoTask();
    }

    @Override
    public CommunityTask getCommunityTaskById(Integer id) {
        return communityTaskMapper.getCommunityTaskById(Long.parseLong(id.toString()));
    }

    @Override
    public int updateTask(CommunityTask communityTask) {
        return communityTaskMapper.updateCommunityTask(communityTask);
    }

    @Override
    public int insertTask(CommunityTask communityTask) {
        return communityTaskMapper.insertCommunityTask(communityTask);
    }
}
