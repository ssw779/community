package com.zb.service.impl;

import com.zb.mapper.CommunityTaskMapper;
import com.zb.pojo.CommunityTask;
import com.zb.service.CommunityTaskService;
import com.zb.service.TaskService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskServiceImpl implements TaskService {
    @Autowired
    private CommunityTaskMapper communityTaskMapper;
    @Autowired
    private CommunityTaskService communityTaskService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<CommunityTask> getAMinuteAgoTask() {
        return communityTaskService.getAminuteAgoTask();
    }

    @Override
    public void publishtask(CommunityTask communityTask) {
        CommunityTask task = communityTaskService.getCommunityTaskById(Integer.parseInt(communityTask.getId()));
        if (task != null) {
            //发送任务
            amqpTemplate.convertAndSend(task.getMqExchange(), task.getMqRoutingkey(), task);
            //修改任务表的时间，把时间修改为now（），为的是如果发送失败，不会立马再次发送，会等待一分钟
            communityTaskService.updateTaskTime(Integer.parseInt(task.getId()));
        }
    }

    @Override
    public int getTaskLock(String id, Integer version) {
        CommunityTask communityTask = new CommunityTask();
        communityTask.setId(id);
        communityTask.setVersion(version);
        if (communityTaskService.updateTaskversion(communityTask) > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public void finshTask(CommunityTask communityTask) {
        //添加信息到历史任务
        CommunityTask communityTaskById = communityTaskService.getCommunityTaskById(Integer.parseInt(communityTask.getId()));
        if (communityTaskById == null) {
            //不存在就添加
            communityTaskMapper.insertCommunityTaskHis(communityTask);
        } else {
        //存在就修改
            communityTaskMapper.updateCommunityTaskHis(communityTask);
        }
        //删除任务
        communityTaskService.delTask(Integer.parseInt(communityTask.getId()));

        //存在就修改，不存在就添加
        //添加成功后删除

    }
}
