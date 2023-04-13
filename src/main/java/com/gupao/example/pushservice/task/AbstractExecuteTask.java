package com.gupao.example.pushservice.task;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
public abstract class AbstractExecuteTask implements WechatTask,Runnable {

    @Override
    public boolean shouldProcess() {
        return true;
    }
}
