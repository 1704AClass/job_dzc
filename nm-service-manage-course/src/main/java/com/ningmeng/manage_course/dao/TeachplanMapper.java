package com.ningmeng.manage_course.dao;

import com.ningmeng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by ASUS on 2020/2/23.
 */
@Mapper
public interface TeachplanMapper {
    public TeachplanNode selectList(String courseId);
}
