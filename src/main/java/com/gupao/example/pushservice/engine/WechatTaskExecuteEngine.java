package com.gupao.example.pushservice.engine;

import com.gupao.example.pushservice.task.WechatTask;
import com.gupao.example.pushservice.task.WechatTaskProcessor;

import java.io.Closeable;
import java.util.Collection;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
public interface WechatTaskExecuteEngine<T extends WechatTask> extends Closeable {
    /**
     * 获取当前的任务数量
     * @return
     */
    int size();

    /**
     * 判断执行引擎是否为空
     *
     * @return true 如果没有要执行的任务，则返回true, 否则返回false
     */
    boolean isEmpty();



    /**
     * 获取指定的WechatTaskProcessor
     * 如果不存在，就返回默认的WechatTaskProcessor
     * @param key
     * @return
     */
    WechatTaskProcessor getProcessor(Object key);

    /**
     * 获取所有的processor key
     * @return
     */
    Collection<Object> getAllProcessorKey();

    /**
     * 设置默认的处理器，如果没有找到对应的处理器，则采用默认的处理器执行
     * @param defaultTaskProcessor
     */
    void setDefaultTaskProcessor(WechatTaskProcessor defaultTaskProcessor);

    /**
     * 添加任务到执行引擎
     * @param key
     * @param task
     */
    void addTask(Object key, T task);

}
