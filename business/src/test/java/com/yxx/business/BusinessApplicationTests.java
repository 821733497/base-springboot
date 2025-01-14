package com.yxx.business;

import com.yxx.business.model.entity.Menu;
import com.yxx.business.model.entity.User;
import com.yxx.business.service.MenuService;
import com.yxx.common.utils.redis.RedissonCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BusinessApplicationTests {
    @Autowired
    private RedissonCache redissonCache;
    @Autowired
    private MenuService menuService;

    String key = "test";
    String userKey = "user";

    @Test
    void redisPutTest() {
        redissonCache.put(key, "测试呀");

        User user = new User();
        user.setLoginCode("120");
        user.setLoginName("护士");
        user.setPassword("120");
        redissonCache.put(userKey, user);
    }

    @Test
    void redisGetTest(){
        String string = redissonCache.getString(key);
        System.out.println("string = " + string);

        User value = redissonCache.get(userKey);
        System.out.println("user = " + value);
    }

    @Test
    void redisRemoveTest(){
        redissonCache.remove(key);
        redissonCache.remove(userKey);
    }

    @Test
    void menuTree(){
        List<Menu> menus = menuService.menuTree();
        System.out.println("menus = " + menus);
    }

}
