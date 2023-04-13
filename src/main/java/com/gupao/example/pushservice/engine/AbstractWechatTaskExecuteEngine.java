package com.gupao.example.pushservice.engine;

import com.gupao.example.pushservice.SyncWechatTaskProcessor;
import com.gupao.example.pushservice.task.WechatTask;
import com.gupao.example.pushservice.task.WechatTaskProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 * 这里可以实现对processor的扩展
 **/
@Slf4j
public abstract class AbstractWechatTaskExecuteEngine <T extends WechatTask> implements WechatTaskExecuteEngine<T>, ApplicationContextAware {

    private final static ConcurrentHashMap<Object, WechatTaskProcessor> taskProcessors = new ConcurrentHashMap<>();

    private WechatTaskProcessor defaultTaskProcessor=new DefaultWechatTaskProcessor();


    @Override
    public WechatTaskProcessor getProcessor(Object key) {
        return taskProcessors.containsKey(key) ? taskProcessors.get(key) : defaultTaskProcessor;
    }

    @Override
    public Collection<Object> getAllProcessorKey() {
        return taskProcessors.keySet();
    }

    @Override
    public void setDefaultTaskProcessor(WechatTaskProcessor defaultTaskProcessor) {
        this.defaultTaskProcessor = defaultTaskProcessor;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 这里可以设置一些默认的同步处理器，不需要使用异步执行引擎来做
        taskProcessors.putIfAbsent("sync", applicationContext.getBean(SyncWechatTaskProcessor.class));
    }
}
