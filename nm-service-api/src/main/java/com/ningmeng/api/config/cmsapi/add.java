package com.ningmeng.api.config.cmsapi;

import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import io.swagger.annotations.ApiOperation;

/**
 * Created by ASUS on 2020/2/17.
 */
public interface add {
    @ApiOperation("添加页面")
    public CmsPageResult add(CmsPage cmsPage);
}
