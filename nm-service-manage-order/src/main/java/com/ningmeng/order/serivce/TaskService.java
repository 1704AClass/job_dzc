package com.ningmeng.order.serivce;

import com.ningmeng.framework.domain.task.NmTask;
import com.ningmeng.framework.domain.task.NmTaskHis;
import com.ningmeng.order.dao.NmTaskHisRepository;
import com.ningmeng.order.dao.NmTaskRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by ASUS on 2020/3/16.
 */
@Service
public class TaskService {
    @Autowired
    private NmTaskRepository nmTaskRepository;

    @Autowired
    private NmTaskHisRepository nmTaskHisRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //取出n条任务，取出指定时间之前处理的任务
    public List<NmTask> findTaskList(Date updateTime,int n){
        //设置分页参数，取出前n条记录
        Pageable pageable = new PageRequest(0,n);
        Page<NmTask> nmTasks = nmTaskRepository.findByUpdateTimeBefore(pageable,updateTime);
        return nmTasks.getContent();
    }

    @Transactional
    public void publish(String taskId,String ex,String routingKey){
        Optional<NmTask> optional = nmTaskRepository.findById(taskId);
        if (optional.isPresent()){
            NmTask nmTask = optional.get();
            //String exchange, String routingKey, Object object
            rabbitTemplate.convertAndSend(ex,routingKey,nmTask);
            //更新任务时间为当前时间
            nmTask.setUpdateTime(new Date());
            nmTaskRepository.save(nmTask);

        }
    }

    @Transactional
    public int getTask(String taskId,int version){
        int i = nmTaskRepository.updateTaskVersion(taskId, version);
        return i;
    }

    //删除任务
    @Transactional
    public void finishTask(String taskId){
        Optional<NmTask> taskOptional = nmTaskRepository.findById(taskId);
        if(taskOptional.isPresent()){
            NmTask nmTask = taskOptional.get();
            nmTask.setDeleteTime(new Date());
            NmTaskHis nmTaskHis = new NmTaskHis();
            BeanUtils.copyProperties(nmTask, nmTaskHis);
            nmTaskHisRepository.save(nmTaskHis);
            nmTaskRepository.delete(nmTask);
        }
    }
}
