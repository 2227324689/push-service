package com.gupao.example.pushservice;

import com.gupao.example.pushservice.task.AbstractExecuteTask;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
public class SynchTask extends AbstractExecuteTask {
    @Override
    public void run() {
        System.out.println("同步任务处理器");
    }
}
