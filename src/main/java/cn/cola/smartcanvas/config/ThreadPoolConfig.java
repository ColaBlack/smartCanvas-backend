package cn.cola.smartcanvas.config;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池配置
 *
 * @author ColaBlack
 */
@Configuration
@ConfigurationProperties(prefix = "smartcanvas.threadpool")
@Data
public class ThreadPoolConfig {

    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 最大线程数
     */
    private int maxPoolSize;

    /**
     * 线程存活时间
     */
    private int keepAliveSeconds;

    /**
     * 队列容量
     */
    private int queueCapacity;

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadFactory threadFactory = new ThreadFactory() {
            /**
             * 线程编号
             */
            private int threadNumber = 1;

            /**
             * 生成线程的方法
             * @param r 线程执行的任务
             * @return 线程对象
             */
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r, "smartcanvas-thread-" + threadNumber);
                threadNumber++;
                return thread;
            }
        };
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueCapacity), threadFactory);
    }
}
