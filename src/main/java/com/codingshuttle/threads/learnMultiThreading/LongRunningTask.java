package com.codingshuttle.threads.learnMultiThreading;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LongRunningTask implements Runnable
{
    private String cmd;
    public LongRunningTask(String cmd)
    {
        this.cmd = cmd;
    }

    public void run()
    {
        log.info("{} Starting Task at {} ",cmd ,Thread.currentThread().getName());
        try
        {
            Thread.sleep(4000);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        log.info("{} ending task at {}",cmd ,Thread.currentThread().getName());
    }
}
