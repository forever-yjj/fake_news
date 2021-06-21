package com.zm.news.utils;

import java.util.HashMap;
import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.utils
 * @className: ResponseCode
 * @author: ZM
 * @description: layui响应体
 * @date: 2021/1/19 9:59
 * @version: 1.0
 */
public class ResponseCode extends HashMap<String, Object> {

    public static ResponseCode success(List<?> data, Long count) {
        ResponseCode responseCode = new ResponseCode();
        responseCode.put("code", 0);
        responseCode.put("msg", "操作成功");
        responseCode.put("count", count);
        responseCode.put("data", data);
        return responseCode;

    }

    public static ResponseCode fail() {
        ResponseCode responseCode = new ResponseCode();
        responseCode.put("code", 1);
        responseCode.put("msg", "操作失败");
        responseCode.put("count", 0);
        responseCode.put("data", null);
        return responseCode;

    }
}
