package com.yd.web.service;

import com.yd.web.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by wubo on 2016/12/14.
 */
@Service
public class DemoService {

    @Autowired
    DemoMapper demoMapper;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static Random random = new Random();

    public String getNameById(String id) {
        return demoMapper.getNameById(id);
    }

    @Scheduled(fixedRate = 5000)
//    @Scheduled(initialDelay=1000, fixedRate=5000)
//    @Scheduled(cron="*/5 * * * * *")
    public void reportCurrentTime() {
      //  System.out.println("现在时间：" + dateFormat.format(new Date()));
    }


    @Async
    public Future<String> doTaskOne() throws Exception {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<String>("任务一完成");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<String>("任务二完成");
    }

}
