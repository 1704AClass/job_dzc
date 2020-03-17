package com.ningmeng.framework.domain.learning.response;

import com.ningmeng.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ASUS on 2020/3/16.
 */
public enum  LearningCode implements ResultCode{
    LEARNING_GETMEDIA_ERROR(false,50001,"课程ID为空"),
    CHOOSECOURSE_USERISNULL(false,50002,"用户ID为空"),
    CHOOSECOURSE_TASKISNULL(false,50003,"没有该订单");

    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;

    private LearningCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
