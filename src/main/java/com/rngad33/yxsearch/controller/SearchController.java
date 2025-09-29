package com.rngad33.yxsearch.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rngad33.yxsearch.common.BaseResponse;
import com.rngad33.yxsearch.common.ErrorCode;
import com.rngad33.yxsearch.common.ResultUtils;
import com.rngad33.yxsearch.exception.MyException;
import com.rngad33.yxsearch.model.dto.post.PostQueryRequest;
import com.rngad33.yxsearch.model.dto.search.SearchRequest;
import com.rngad33.yxsearch.model.dto.user.UserQueryRequest;
import com.rngad33.yxsearch.model.entity.Picture;
import com.rngad33.yxsearch.model.vo.PostVO;
import com.rngad33.yxsearch.model.vo.SearchVO;
import com.rngad33.yxsearch.model.vo.UserVO;
import com.rngad33.yxsearch.service.PictureService;
import com.rngad33.yxsearch.service.PostService;
import com.rngad33.yxsearch.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

/**
 * 搜索接口
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;

    @Resource
    private PostService postService;

    /**
     * 聚合搜索
     *
     * @param searchRequest
     * @param request
     * @return
     */
    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        String searchText = searchRequest.getSearchText();
        // 搜用户
        CompletableFuture<Page<UserVO>> userFuture = CompletableFuture.supplyAsync(() -> {
            UserQueryRequest userQueryRequest = new UserQueryRequest();
            userQueryRequest.setUserName(searchText);
            return userService.listUserVOByPage(userQueryRequest);
        });
        // 搜图片
        CompletableFuture<Page<Picture>> pictureFuture = CompletableFuture
                .supplyAsync(() -> pictureService.searchPicture(searchText, 1, 10)
        );
        // 搜帖子
        CompletableFuture<Page<PostVO>> postFuture = CompletableFuture.supplyAsync(() -> {
            PostQueryRequest postQueryRequest = new PostQueryRequest();
            postQueryRequest.setSearchText(searchText);
            return postService.listPostVOByPage(postQueryRequest, request);
        });
        CompletableFuture.allOf(userFuture, pictureFuture, postFuture).join();
        // 聚合
        try {
            Page<UserVO> userVOPage = userFuture.get();
            Page<Picture> picturePage = pictureFuture.get();
            Page<PostVO> postVOPage = postFuture.get();
            SearchVO searchVO = new SearchVO();
            searchVO.setUserList(userVOPage.getRecords());
            searchVO.setPictureList(picturePage.getRecords());
            searchVO.setPostList(postVOPage.getRecords());
            return ResultUtils.success(searchVO);
        } catch (Exception e) {
            throw new MyException(ErrorCode.SYSTEM_ERROR, "查询异常！");
        }
    }

    /**
     * 单独搜索用户
     *
     * @param searchRequest
     * @return
     */
    @PostMapping("/user")
    public BaseResponse<Page<UserVO>> searchUsers(@RequestBody SearchRequest searchRequest) {
        String searchText = searchRequest.getSearchText();
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        Page<UserVO> userVOPage = userService.listUserVOByPage(userQueryRequest);
        return ResultUtils.success(userVOPage);
    }

    /**
     * 单独搜索图片
     *
     * @param searchRequest
     * @return
     */
    @PostMapping("/pic")
    public BaseResponse<Page<Picture>> searchPictures(@RequestBody SearchRequest searchRequest) {
        String searchText = searchRequest.getSearchText();
        Page<Picture> picturePage = pictureService.searchPicture(searchText, 1, 10);
        return ResultUtils.success(picturePage);
    }

    /**
     * 单独搜索帖子
     *
     * @param searchRequest
     * @param request
     * @return
     */
    @PostMapping("/post")
    public BaseResponse<Page<PostVO>> searchPosts(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        String searchText = searchRequest.getSearchText();
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        Page<PostVO> postVOPage = postService.listPostVOByPage(postQueryRequest, request);
        return ResultUtils.success(postVOPage);
    }

}