package com.ningmeng.api.filesystem;

import com.ningmeng.framework.domain.filesystem.response.UploadFileResult;
import com.ningmeng.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ASUS on 2020/2/23.
 */
public interface FileSystemControllerApi {
    /**
     * 上传文件
     * @param multipartFile 文件
     * @param filetag 文件标签
     * @param businesskey 业务key
     * @param metedata 元信息,json格式
     * @return
     */
    @ApiOperation("上传文件")
    public UploadFileResult upload(MultipartFile multipartFile,String filetag, String businesskey, String metadata);

}
