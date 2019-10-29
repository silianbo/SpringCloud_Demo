package com.silianbo.springcloud.dzuul.util;

/**
 * @author bo
 */
public class ZuulUtil {
    public String isSuccess = "isSuccess";

    /**
     * pre:请求执行之前filter
     * route:处理请求,进行路由
     * post:请求处理完成后执行的filter
     * error:出现错误时执行的filter
     */
    public interface FilterType {
        String PRE = "pre";
        String ROUTE = "route";
        String POST = "post";
        String ERROR = "error";
    }

    public interface FilterStatus {
        String ISSUCCESS = "isSuccess";
    }
}
