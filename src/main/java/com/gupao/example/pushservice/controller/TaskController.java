package com.gupao.example.pushservice.controller;

import com.gupao.example.pushservice.SynchTask;
import com.gupao.example.pushservice.TemplateMsgPushTask;
import com.gupao.example.pushservice.engine.WechatExecuteTaskExecuteEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
@RestController
public class TaskController {

    @Autowired
    WechatExecuteTaskExecuteEngine executeEngine;

    /**
     * demo -> 通过线程池组来执行指定任务
     */
    @GetMapping("/test")
    public void test(){
        TemplateMsgPushTask templateMsgPushTask=new TemplateMsgPushTask();
        //tag ，是用来实现路由的key，可以路由到不同的工作线程组中
        executeEngine.addTask("TemplateMsg_Batch_01",templateMsgPushTask);
    }

    @GetMapping("/processor")
    public void processor(){
        //这个处理方式，是基于策略模式来实现，也就是说提前内置一定的任务处理机制
        //执行引擎会根据你对应的tag，找到合适的processor进行处理
        executeEngine.addTask("sync",new SynchTask());
    }
}
