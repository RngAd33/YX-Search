package com.rngad33.yxsearch.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rngad33.yxsearch.model.dto.post.PostQueryRequest;
import com.rngad33.yxsearch.model.entity.Post;
import com.rngad33.yxsearch.model.vo.PostVO;
import com.rngad33.yxsearch.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 帖子数据源
 */
@Service
public class PostDataSource implements DataSource<PostVO> {

    @Resource
    private PostService postService;

    @Override
    public Page<PostVO> doSearch(String searchText, long pageNum, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        Page<Post> postPage = postService.page(new Page<>(pageNum, pageSize),
                postService.getQueryWrapper(postQueryRequest));
        return postService.getPostVOPage(postPage, null);
    }

}