package com.hmdp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import com.hmdp.utils.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hmdp.service.impl.ShopServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HmDianPingApplicationTests {

    @Resource
    private ShopServiceImpl shopService;
    @Resource
    private RedisIdWorker redisIdWorker;
    private ExecutorService es = Executors.newFixedThreadPool(500);

    // @Test
    // public void testIdWorker() throws InterruptedException {
    // CountDownLatch latch = new CountDownLatch(300);

    // Runnable task = () -> {
    // for (int i = 0; i < 100; i++) {
    // long id = redisIdWorker.nextId("order");
    // System.out.println("id = " + id);
    // }
    // latch.countDown();
    // };
    // long begin = System.currentTimeMillis();
    // for (int i = 0; i < 300; i++) {
    // es.submit(task);
    // }
    // latch.await();
    // long end = System.currentTimeMillis();
    // System.out.println("time = " + (end - begin));
    // }
}