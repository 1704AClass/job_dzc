package com.ningmeng.manage_cms_client.dao;

import com.ningmeng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ASUS on 2020/2/19.
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String>{
}
