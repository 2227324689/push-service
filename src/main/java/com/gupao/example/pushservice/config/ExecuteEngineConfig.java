package com.gupao.example.pushservice.config;

import com.gupao.example.pushservice.engine.WechatExecuteTaskExecuteEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
@Configuration
public class ExecuteEngineConfig {

    @Value("${wechat.engine.name}")
    private String name;
    @Value("${wechat.engine.dispatcherCount}")
    private int dispatcherCount;

    @Bean
    public WechatExecuteTaskExecuteEngine wechatExecuteTaskExecuteEngine(){
        WechatExecuteTaskExecuteEngine wechatExecuteTaskExecuteEngine
                =new WechatExecuteTaskExecuteEngine(name,dispatcherCount);
        return wechatExecuteTaskExecuteEngine;
    }
}
