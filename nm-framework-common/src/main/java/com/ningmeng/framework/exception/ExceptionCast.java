package com.ningmeng.framework.exception;

import com.ningmeng.framework.model.response.ResultCode;

/**
 * 异常抛出类
 * Created by ASUS on 2020/2/18.
 */
public class ExceptionCast {
    //使用此静态方法抛出自定义异常
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
