package com.gupao.example.pushservice.engine;

import com.gupao.example.pushservice.task.WechatTask;
import com.gupao.example.pushservice.task.WechatTaskProcessor;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
public class DefaultWechatTaskProcessor implements WechatTaskProcessor {
    @Override
    public boolean process(WechatTask task) {
        System.out.println("默认任务处理器");
        DefaultTask defaultTask=(DefaultTask)task;
        defaultTask.run();
        return true;
    }
}
