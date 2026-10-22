package com.codingshuttle.threads.learnMultiThreading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.*;

@SpringBootApplication
@Slf4j
public class LearnMultiThreadingApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(LearnMultiThreadingApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (
                        4,
                        6,
                        2,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(10),
                        new RejectedExecutionHandler()
                        {
                            @Override
                            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
                            {
                                log.info("Thread Rejected ... Retrying");
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e)
                                {
                                    throw new RuntimeException(e);
                                }
                                executor.submit(r);
                            }
                        }
                );


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(
                6,
                new ThreadFactory()
                {
                    @Override
                    public Thread newThread(Runnable r)
                    {
                        return new Thread(r);
                    }
                }
        );

        log.info("Starting main Thread : " + Thread.currentThread().getName());

        for (int i = 0; i < 10; i++)
        {
            scheduledExecutorService.schedule(new LongRunningTask("schedule Task"), 5 ,  TimeUnit.SECONDS);

//            threadPoolExecutor.submit(new LongRunningTask(i+""));
        }


        log.info("ending main Thread : " + Thread.currentThread().getName());

    }

}