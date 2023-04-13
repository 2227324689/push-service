package com.gupao.example.pushservice;

import com.gupao.example.pushservice.task.WechatTask;
import com.gupao.example.pushservice.task.WechatTaskProcessor;
import org.springframework.stereotype.Service;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
@Service
public class SyncWechatTaskProcessor implements WechatTaskProcessor {
    @Override
    public boolean process(WechatTask task) {
        System.out.println("同步处理任务");
        SynchTask synchTask=(SynchTask)task;
        synchTask.run();
        return true;
    }
}
