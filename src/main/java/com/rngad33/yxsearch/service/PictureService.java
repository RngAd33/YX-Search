package com.rngad33.yxsearch.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rngad33.yxsearch.model.entity.Picture;

/**
 * 图片服务
 */
public interface PictureService {

    Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);

}