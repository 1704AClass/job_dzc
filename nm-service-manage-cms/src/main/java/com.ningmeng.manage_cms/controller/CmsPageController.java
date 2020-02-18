package com.ningmeng.manage_cms.controller;

import com.ningmeng.api.config.cmsapi.CmsPageControllerApi;
import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.request.QueryPageRequest;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import com.ningmeng.framework.model.response.QueryResponseResult;
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
        //暂时采用测试数据，测试接口是否可以正常运行
       /* QueryResult queryResult = new QueryResult();
        queryResult.setTotal(2);
        //静态数据列表
        List list = new ArrayList();
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试页面");
        list.add(cmsPage);
        queryResult.setList(list);
        //CommonCode.SUCCESS,queryResult
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;*/
       return cmsPageService.findList(page,size,queryPageRequest);
    }

    //添加页面
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage){
        return cmsPageService.add(cmsPage);
    }
}
