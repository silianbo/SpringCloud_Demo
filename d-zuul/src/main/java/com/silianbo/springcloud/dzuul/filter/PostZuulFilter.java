package com.silianbo.springcloud.dzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.silianbo.springcloud.dzuul.util.ZuulUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bo
 */
@Component
public class PostZuulFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(PostZuulFilter.class);

    @Override
    public String filterType() {
        return ZuulUtil.FilterType.POST;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        logger.info("进入post过滤器");
        HttpServletRequest request = ctx.getRequest();
        boolean issuccess = false;
        try {
            issuccess = (boolean) ctx.get(ZuulUtil.FilterStatus.ISSUCCESS);
        } catch (Exception e) {
        }
        if (issuccess) {
            logger.info("{} PostZuulFilter request to {}", request.getMethod(), request.getRequestURL().toString());
            logger.info(ctx.getResponseBody());
            ctx.set(ZuulUtil.FilterStatus.ISSUCCESS, true);
        } else {
            ctx.setResponseBody("{\"result\":\"请求post 过滤器 拦截生效!\"}");
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            ctx.set(ZuulUtil.FilterStatus.ISSUCCESS, false);
        }
        return null;
    }
}
