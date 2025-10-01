package com.rngad33.yxsearch.once;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.RandomUtil;
import com.rngad33.yxsearch.model.entity.Post;
import com.rngad33.yxsearch.model.entity.User;
import com.rngad33.yxsearch.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 该测试类用于向数据库批量插入数据
 */
@SpringBootTest
class InsertPostsTest {

    @Resource
    private PostService postService;

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
            List<Post> posts = new ArrayList<>();
            do {
                j++;
                Post post = new Post();
                post.setTitle("帖子" + RandomUtil.randomString(6));
                post.setContent("这是一个帖子");
                post.setTags("[]");
                post.setThumbNum(0);
                post.setFavourNum(0);
                post.setUserId(1L);
                post.setCreateTime(new Date());
                posts.add(post);
            } while (j % INSERT_NUM != 0);
            // 异步执行
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                postService.saveBatch(posts, 100);
            }, executorService);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[]{})).join();   // 阻塞
        stopWatch.stop();   // 任务完成后才执行此句
        System.out.println(stopWatch.getTotalTimeMillis());
    }

}