package com.codingshuttle.threads.learnMultiThreading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.*;

@SpringBootApplication
@Slf4j
public class LearnMultiThreadingApplication
{

    public static void main(String[] args) throws Exception {

        SpringApplication.run(LearnMultiThreadingApplication.class, args);

        learnFuture();

    }

    static void learnFuture() throws ExecutionException, InterruptedException
    {
        try (ExecutorService executorService = Executors.newFixedThreadPool(4))
        {
            Callable<String> task = new Callable<String>()
            {
                @Override
                public String call() throws Exception
                {
                    Thread.sleep(5000);
                    return getName();
                }
            };

            Future<String> myName = executorService.submit(task);       // calling worker thread from main

            while (!myName.isDone())
            {
                log.info("Task is still running...");
            }

            log.info(" waiting to get the value from future  {} ",myName.get()  ); // main thread is waiting for future
        }

        log.info("after nameFuture {} ", Thread.currentThread().getState());

    }

    static void learnCompletableFuture() throws ExecutionException, InterruptedException
    {

    }

    static String getName()
    {
        log.info("Inside name  Future : {}", Thread.currentThread().getState());
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        return "Sujit";
    }

}