package com.gupao.example.pushservice.engine;

import com.gupao.example.pushservice.task.AbstractExecuteTask;
import com.gupao.example.pushservice.task.WechatTask;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
public class DefaultTask extends AbstractExecuteTask {

    @Override
    public void run() {
        System.out.println("这里是默认执行的任务");
    }
}
