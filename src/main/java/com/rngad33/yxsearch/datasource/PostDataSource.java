package com.rngad33.yxsearch.datasource;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rngad33.yxsearch.common.ErrorCode;
import com.rngad33.yxsearch.exception.ThrowUtils;
import com.rngad33.yxsearch.model.dto.post.PostQueryRequest;
import com.rngad33.yxsearch.model.entity.Post;
import com.rngad33.yxsearch.model.vo.PostVO;
import com.rngad33.yxsearch.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(pageNum);
        postQueryRequest.setPageSize(pageSize);
        // 取request
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        ThrowUtils.throwIf(ObjUtil.isNull(servletRequestAttributes), ErrorCode.OPERATION_ERROR);
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return postService.listPostVOByPage(postQueryRequest, request);
    }

}