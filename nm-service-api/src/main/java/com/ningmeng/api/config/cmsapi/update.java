package com.ningmeng.api.config.cmsapi;

import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import io.swagger.annotations.ApiOperation;

/**
 * Created by ASUS on 2020/2/18.
 */
public interface update {
    @ApiOperation("通过ID查询页面")
    public CmsPage findById(String id);

    @ApiOperation("修改页面")
    public CmsPageResult edit(String id, CmsPage cmsPage);

}
