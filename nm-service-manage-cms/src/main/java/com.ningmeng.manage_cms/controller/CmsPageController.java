package com.ningmeng.manage_cms.controller;

import com.ningmeng.api.config.cmsapi.CmsPageControllerApi;
import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.request.QueryPageRequest;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.ResponseResult;
import com.ningmeng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ASUS on 2020/2/14.
 */
@RestController
public class CmsPageController implements CmsPageControllerApi{
    @Autowired
    private CmsPageService cmsPageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
       return cmsPageService.findList(page,size,queryPageRequest);
    }

    //添加页面
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage){
        return cmsPageService.add(cmsPage);
    }

    //根据id查询页面
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String id) {
        return cmsPageService.getById(id);
    }

    //修改页面
    @PutMapping("/edit/{id}")//这里使用put方法，http方法中out表示更新
    public CmsPageResult edit(@PathVariable("id") String id,@RequestBody CmsPage cmsPage){
        return cmsPageService.update(id,cmsPage);
    }

    @DeleteMapping("/del/{id}") //使用http的delete方法完成岗位操作
    public ResponseResult delete(@PathVariable("id") String id) {
        return cmsPageService.delete(id);
    }

    @PostMapping("/postPage/{pageId}")
    public ResponseResult post(@PathVariable("pageId") String pageId) {
        return cmsPageService.postPage(pageId);
    }
}
