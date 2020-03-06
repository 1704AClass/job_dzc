package com.ningmeng.manage_media.controller;

import com.ningmeng.api.config.mediaapi.MediaFileControllerApi;
import com.ningmeng.framework.domain.media.request.QueryMediaFileRequest;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.manage_media.service.MediaFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ASUS on 2020/3/5.
 */
@RestController
@RequestMapping("/media/file")
public class MediaFileController implements MediaFileControllerApi{
    @Autowired
    MediaFileService mediaFileService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, QueryMediaFileRequest queryMediaFileRequest) {
       //媒资文件查询
        return mediaFileService.findList(page,size,queryMediaFileRequest);
    }
}
