package com.hmdp;

import javax.annotation.Resource;

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

    @Test
    public void testSaveShopToRedis() throws InterruptedException {
        // 测试保存商铺到 Redis
        shopService.saveShoptoRedis(1L, 20L);
    }

}
