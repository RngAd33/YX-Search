package com.rngad33.yxsearch.sync;

import cn.hutool.core.collection.CollUtil;
import com.rngad33.yxsearch.model.dto.post.PostEsDTO;
import com.rngad33.yxsearch.model.entity.Post;
import com.rngad33.yxsearch.service.PostService;
import com.rngad33.yxsearch.utils.esdao.PostEsDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据同步测试类
 */
@SpringBootTest
@Slf4j
public class SyncTest {

    @Resource
    private PostService postService;

    @Resource
    private PostEsDao postEsDao;

    /**
     * 全量同步帖子到 ES
     */
    @Test
    void doSyncFromSqlToEs() {
        List<Post> postList = postService.list();
        if (CollUtil.isEmpty(postList)) {
            return;
        }
        List<PostEsDTO> postEsDTOList = postList.stream().map(PostEsDTO::objToDto).collect(Collectors.toList());
        final int pageSize = 500;
        int total = postEsDTOList.size();
        log.info("FullSyncPostToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            postEsDao.saveAll(postEsDTOList.subList(i, end));
        }
        log.info("FullSyncPostToEs end, total {}", total);
    }

}