package com.rngad33.yxsearch.controller;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rngad33.yxsearch.common.BaseResponse;
import com.rngad33.yxsearch.common.ErrorCode;
import com.rngad33.yxsearch.common.ResultUtils;
import com.rngad33.yxsearch.exception.ThrowUtils;
import com.rngad33.yxsearch.model.dto.post.PostQueryRequest;
import com.rngad33.yxsearch.model.dto.search.SearchRequest;
import com.rngad33.yxsearch.model.dto.user.UserQueryRequest;
import com.rngad33.yxsearch.model.entity.Picture;
import com.rngad33.yxsearch.model.enums.SearchTypeEnum;
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
     * @return searchVO
     */
    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        String searchText = searchRequest.getSearchText();
        String type = searchRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
        ThrowUtils.throwIf(!StrUtil.isAllNotBlank(searchText, type), ErrorCode.PARAMS_ERROR);
        SearchVO searchVO = new SearchVO();
        if (ObjUtil.isNull(searchTypeEnum)) {
            // - 未规定类型，搜索所有
            Page<UserVO> userVOPage = this.searchUsers(searchRequest);
            Page<Picture> picturePage = this.searchPictures(searchRequest);
            Page<PostVO> postVOPage = this.searchPosts(searchRequest, request);
            // 聚合
            searchVO.setUserList(userVOPage.getRecords());
            searchVO.setPictureList(picturePage.getRecords());
            searchVO.setPostList(postVOPage.getRecords());
        } else {
            // - 匹配类型搜索
            switch (searchTypeEnum) {
                case USER:
                    Page<UserVO> userVOPage = this.searchUsers(searchRequest);
                    searchVO.setUserList(userVOPage.getRecords());
                    break;
                case PICTURE:
                    Page<Picture> picturePage = searchPictures(searchRequest);
                    searchVO.setPictureList(picturePage.getRecords());
                    break;
                case POST:
                    Page<PostVO> postVOPage = this.searchPosts(searchRequest, request);
                    searchVO.setPostList(postVOPage.getRecords());
                    break;
            }
        }
        return ResultUtils.success(searchVO);
    }

    /**
     * 单独搜索用户
     *
     * @param searchRequest
     * @return userVOPage
     */
    private Page<UserVO> searchUsers(@RequestBody SearchRequest searchRequest) {
        String searchText = searchRequest.getSearchText();
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        return userService.listUserVOByPage(userQueryRequest);
    }

    /**
     * 单独搜索图片
     *
     * @param searchRequest
     * @return picturePage
     */
    private Page<Picture> searchPictures(@RequestBody SearchRequest searchRequest) {
        String searchText = searchRequest.getSearchText();
        return pictureService.searchPicture(searchText, 1, 10);
    }

    /**
     * 单独搜索帖子
     *
     * @param searchRequest
     * @param request
     * @return postVOPage
     */
    private Page<PostVO> searchPosts(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        String searchText = searchRequest.getSearchText();
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        return postService.listPostVOByPage(postQueryRequest, request);
    }

}