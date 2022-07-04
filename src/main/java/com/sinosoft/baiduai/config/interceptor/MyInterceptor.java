package com.sinosoft.baiduai.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.baiduai.util.GlobalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2022/5/16 15:35
 */
public class MyInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(MyInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String openUrI = "/baiduai/";
        if (request.getRequestURI().startsWith(openUrI) && isWhiteIp(request.getRemoteHost())) {
            // 仅判断是否含有指定RequestHeader，不做具体验证
            return StrUtil.isNotEmpty(request.getHeader(GlobalUtil.AUTH_HEADER));
        }
        LOG.warn("拒绝了" + request.getRemoteHost() +"访问：" + request.getRequestURI());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    /**
     * 未来可配置白名单IP，防止攻击
     *
     * @param remoteIp 访问IP
     * @return 是否白名单IP
     */
    private boolean isWhiteIp(String remoteIp) {
        // TODO..配置白名单IP
        return true;
    }
}
