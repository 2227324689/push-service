package com.gupao.example.pushservice.engine;

import com.gupao.example.pushservice.task.AbstractExecuteTask;
import com.gupao.example.pushservice.task.WechatTaskProcessor;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
public class WechatExecuteTaskExecuteEngine extends AbstractWechatTaskExecuteEngine<AbstractExecuteTask> {
    /**
     * 定义线程池组，相当于定义一个线程池数组
     */
    private final TaskExecuteWorker[] executeWorkers;

    public WechatExecuteTaskExecuteEngine(String name) {
        this(name,Runtime.getRuntime().availableProcessors());
    }

    public WechatExecuteTaskExecuteEngine(String name,int dispatchWorkerCount) {
        //线程池组数量等于CPU核心数｛这里可以作为配置项来设置｝
        executeWorkers = new TaskExecuteWorker[dispatchWorkerCount];
        for (int mod = 0; mod < dispatchWorkerCount; ++mod) {
            executeWorkers[mod] = new TaskExecuteWorker(name, mod, dispatchWorkerCount);
        }
    }

    @Override
    public int size() { //用来获取正在执行的任务数量，打印日志做监控
        int result = 0;
        for (TaskExecuteWorker each : executeWorkers) {
            result += each.pendingTaskCount();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size();
    }

    @Override
    public void addTask(Object tag, AbstractExecuteTask task) {
        WechatTaskProcessor processor = getProcessor(tag);
        if(null!=processor){  //是否是同步处理任务
            processor.process(task);
            return;
        }
        //下面是采用异步线程池组来处理任务
        TaskExecuteWorker worker = getWorker(tag);
        worker.process(task);
    }

    private TaskExecuteWorker getWorker(Object tag) {
        int idx = (tag.hashCode() & Integer.MAX_VALUE) % workersCount();
        return executeWorkers[idx];
    }

    private int workersCount() {
        return executeWorkers.length;
    }

    @Override
    public void close() throws IOException {
        for (TaskExecuteWorker each : executeWorkers) {
            each.close();
        }
    }

    /**
     * 获取工作线程池的状态
     *
     * @return workers status string
     */
    public String workersStatus() {
        StringBuilder sb = new StringBuilder();
        for (TaskExecuteWorker worker : executeWorkers) {
            sb.append(worker.status()).append('\n');
        }
        return sb.toString();
    }
}
