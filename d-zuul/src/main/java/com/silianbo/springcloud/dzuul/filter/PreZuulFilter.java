package com.silianbo.springcloud.dzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.silianbo.springcloud.dzuul.util.ZuulUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bo
 */
public class PreZuulFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(PreZuulFilter.class);

    /**
     * filterType 返回过滤器类型
     * 他决定了过滤器在请求的哪个生命周期中执行。这里定义为pre，代表会在请求被路由前执行。
     * <p>
     * pre:   请求执行之前filter
     * route: 处理请求,进行路由
     * post:  请求处理完成后执行的filter
     * error: 出现错误时执行的filter
     */
    @Override
    public String filterType() {
        return ZuulUtil.FilterType.PRE;
    }

    /**
     * filterOrder 返回过滤器的执行顺序
     * 优先级为0，数字越大，优先级越低
     * <p>
     * 当请求在一个阶段有多个过滤器是，需要根据该方法的返回值来依次执行
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter 判断该过滤器是否需要被执行
     * <p>
     * true：需要过滤；
     * false：不要过滤；
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * <p>
     * 对特定用户进行过滤
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("进入pre 过滤器");
        logger.info("{} PreZuulFilter request to {}", request.getMethod(), request.getRequestURL().toString());
        // 获取请求的参数
        String username = request.getParameter("username");
        if (StringUtils.isNotBlank(username)) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set(ZuulUtil.FilterStatus.ISSUCCESS, true);
            return null;
        } else {
            ctx.setSendZuulResponse(false);
            // 返回错误码
            ctx.setResponseStatusCode(401);
            // 返回错误内容
            ctx.setResponseBody("{\"result\":\"请求pre 过滤器 拦截生效,username不能为空!\"}");
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            ctx.set(ZuulUtil.FilterStatus.ISSUCCESS, false);
            return null;
        }
    }
}
