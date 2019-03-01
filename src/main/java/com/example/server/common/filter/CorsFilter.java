package com.example.server.common.filter;

        import org.springframework.core.annotation.Order;
        import org.springframework.stereotype.Component;

        import javax.servlet.*;
        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

/**
 * @ClassName CorsFilter
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.common.filter
 * @Date 2019/2/23 16:57
 */
//@WebFilter(urlPatterns = "/*")
//@Order(-1)
public class CorsFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("in filter");
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        res.setHeader("Access-Control-Allow-Origin", "*");
//        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, GET");
//        res.setHeader("Access-Control-Max-Age", "3600");
////        res.setHeader("Access-Control-Allow-Headers", "x-requested-with");
////        res.addHeader("Access-Control-Allow-Origin","*");
//        res.setHeader("Access-Control-Allow-Headers","Origin,X-Requespted-With,Content-Type,Accept,Authorization");
        httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers","Origin,X-Requespted-With,Content-Type,Accept,Authorization");
       httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(httpServletRequest, httpServletResponse);

    }

    @Override
    public void destroy() {

    }
}
