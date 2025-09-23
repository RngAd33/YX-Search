package com.rngad33.yxsearch.once;

import cn.hutool.core.date.StopWatch;
import com.rngad33.yxsearch.model.entity.User;
import com.rngad33.yxsearch.service.UserService;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 该测试类用于向数据库批量插入数据
 */
@SpringBootTest
class InsertUsersTest {

    @Resource
    private UserService userService;

    // 自定义线程池
    private final ExecutorService executorService = new ThreadPoolExecutor(60, 1000, 10000,
            TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));

    /**
     * 并发式
     */
    @Test
    void doInsert() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 10000;
        int j = 0;
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        // 分10个线程
        for (int i = 0; i < 10; i++) {
            List<User> users = new ArrayList<>();
            do {
                j++;
                User user = new User();
                user.setUserAccount("user");
                user.setUserPassword("12345678");
                user.setUnionId("4444");
                user.setMpOpenId("4444");
                user.setUserName("祈-我ら神祖と共に歩む者なり");
                user.setUserAvatar("https://636f-codenav-8grj8px727565176-1256524210.tcb.qcloud.la/img/logo.png");
                user.setUserProfile("");
                user.setUserRole("user");
                user.setCreateTime(new Date());
                users.add(user);
            } while (j % INSERT_NUM != 0);
            // 异步执行
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                userService.saveBatch(users, 100);
            }, executorService);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[]{})).join();   // 阻塞
        stopWatch.stop();   // 任务完成后才执行此句
        System.out.println(stopWatch.getTotalTimeMillis());
    }

}