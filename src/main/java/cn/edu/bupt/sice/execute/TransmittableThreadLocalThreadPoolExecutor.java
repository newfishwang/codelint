package cn.edu.bupt.sice.execute;

import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.*;

public class TransmittableThreadLocalThreadPoolExecutor extends ThreadPoolExecutor {
    private static final RejectedExecutionHandler defaultHandler = new AbortPolicy();

    public TransmittableThreadLocalThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public TransmittableThreadLocalThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, defaultHandler);
    }

    public void execute(Runnable command) {
        super.execute(TtlRunnable.get(command));
    }
}