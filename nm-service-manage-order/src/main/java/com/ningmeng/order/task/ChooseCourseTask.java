package com.ningmeng.order.task;

import java.util.Date;

/**
 * Created by ASUS on 2020/3/15.
 */
//@Component
public class ChooseCourseTask {
    // @Scheduled(fixedRate = 5000) //上次执行开始时间后5秒执行
    // @Scheduled(fixedDelay = 5000) //上次执行完毕后5秒执行
    // @Scheduled(initialDelay=3000, fixedRate=5000) //第一次延迟3秒，以后每隔5秒执行一次
    //@Scheduled(cron = "0/3 * * * * *")//每隔3秒执行一次
    public void task1(){
        System.out.println("task1"+new Date());
    }

    //@Scheduled(fixedRate = 3000)
    public void task2(){
        System.out.println("task2"+new Date());
    }
}
