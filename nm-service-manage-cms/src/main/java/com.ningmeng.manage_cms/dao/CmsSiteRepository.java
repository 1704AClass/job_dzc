package com.ningmeng.manage_cms.dao;

import com.ningmeng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ASUS on 2020/3/1.
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String>{
}
