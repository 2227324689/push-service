package com.gupao.example.pushservice;

import com.gupao.example.pushservice.task.AbstractExecuteTask;
import org.springframework.stereotype.Service;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
public class TemplateMsgPushTask extends AbstractExecuteTask {

    @Override
    public void run() {
        System.out.println("开始执行推送任务");
    }
}
