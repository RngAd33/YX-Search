package com.rngad33.yxsearch.model.dto.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 文件上传请求
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadFileRequest {

    /**
     * 业务
     */
    private String biz;

}