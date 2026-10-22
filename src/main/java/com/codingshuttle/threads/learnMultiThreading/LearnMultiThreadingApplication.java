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
         Future<String> myName =    executorService.submit(new Callable<String>()
            {
                @Override
                public String call() throws Exception
                {
                    log.info("Inside name  Future : {}", Thread.currentThread().getState());

                    Thread.sleep(5000);

                    return "Sujit";
                }
            });

         myName.get();
        }

        log.info("after nameFuture {} ",Thread.currentThread().getState());
    }

}