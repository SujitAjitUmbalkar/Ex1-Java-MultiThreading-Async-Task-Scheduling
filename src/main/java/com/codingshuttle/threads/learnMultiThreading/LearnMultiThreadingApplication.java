package com.codingshuttle.threads.learnMultiThreading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class LearnMultiThreadingApplication implements CommandLineRunner
{

    public static void main(String[] args) throws Exception
    {

        SpringApplication.run(LearnMultiThreadingApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception
    {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (
                    4,
                    6,
                    2,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10)
            );

        log.info("Starting main Thread : "+ Thread.currentThread().getName());

        threadPoolExecutor.submit(new Runnable()
        {
            @Override
            public void run()
            {
                log.info("Starting Task at "+Thread.currentThread().getName());
                try
                {
                    Thread.sleep(4000);
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                log.info("Task Completed at "+Thread.currentThread().getName());
            }
        });


        log.info("Ending main Thread : "+ Thread.currentThread().getName());

    }
}
