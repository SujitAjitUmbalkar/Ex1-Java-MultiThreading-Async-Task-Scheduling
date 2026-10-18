package com.codingshuttle.threads.learnMultiThreading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class LearnMultiThreadingApplication
{

    public static void main(String[] args) throws InterruptedException {

        // 1. Start the Spring Boot application
        SpringApplication.run(LearnMultiThreadingApplication.class, args);

        // 2. Main thread logs its name and state
        log.info("Before Thread, Name: {}, State: {}",
                Thread.currentThread().getName(),
                Thread.currentThread().getState());

        // 3. Create a worker thread (task is not running yet)
        Thread workerThread = new Thread(() -> {

            // 5. Worker thread starts executing this code
            log.info("Inside Thread, Name: {}, State: {}",
                    Thread.currentThread().getName(),
                    Thread.currentThread().getState());

            try {
                // 6. Worker thread sleeps for 5 seconds , other threads won't wait for it
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            // 7. Worker thread finishes execution
        });

        // 4. Start the worker thread
        workerThread.start();

        // 8. Main thread waits until the worker thread finishes
        workerThread.join();

        // 9. Main thread resumes and logs its name and state
        log.info("After Thread, Name: {}, State: {}",
                Thread.currentThread().getName(),
                Thread.currentThread().getState());

        // 10. Check the worker thread's final state (TERMINATED)
        log.info("After workerThread, Name: {}, State: {}",
                workerThread.getName(),
                workerThread.getState());
    }
}
