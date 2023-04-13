package com.gupao.example.pushservice.engine;

import com.gupao.example.pushservice.task.AbstractExecuteTask;
import com.gupao.example.pushservice.task.WechatTask;
import com.gupao.example.pushservice.task.WechatTaskProcessor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: Mic
 * @公众号： 跟着Mic学架构
 * @官网： https://ke.gupaoedu.cn
 * @Org: 咕泡教育
 **/
@Slf4j
public final class TaskExecuteWorker implements WechatTaskProcessor, Closeable {

    //任务队列大小
    private static final int QUEUE_CAPACITY = 1 << 15;

    private final String name;
    private ThreadPoolExecutor threadPoolExecutor;
    private final AtomicBoolean closed;
    public TaskExecuteWorker(final String name, final int mod, final int total) {
        this(name, mod, total, null);
    }

    public TaskExecuteWorker(final String name, final int mod, final int total, final Logger logger) {
        this.name = name + "_" + mod + "%" + total;
        this.threadPoolExecutor=new ThreadPoolExecutor(4,8,60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(QUEUE_CAPACITY));
        this.closed = new AtomicBoolean(false);
    }

    @Override
    public boolean process(WechatTask task) {
        if (task instanceof AbstractExecuteTask) {
            putTask((Runnable) task);
        }
        return true;
    }
    private void putTask(Runnable task) {
        threadPoolExecutor.execute(task);
    }
    public long pendingTaskCount() {
        return threadPoolExecutor.getTaskCount();
    }

    public String status() {
        return name + ", pending tasks: " + pendingTaskCount();
    }

    @Override
    public void close() throws IOException {
        threadPoolExecutor.shutdown();
        closed.compareAndSet(false, true);
    }
}
