//package org.example.crud_system.config;
//
//import jakarta.servlet.*;
//import org.springframework.core.Ordered;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.io.IOException;
//
//public class JwtTokenFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest,
//                         ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        servletRequest.setAttribute("username", "admin");
//        servletRequest.setAttribute("password", "admin");
//        filterChain.doFilter(servletRequest, servletResponse);
//        servletResponse.setContentType("application/json");
//        servletResponse.setCharacterEncoding("UTF-8");
//    }
//
//    public int getOrder() {
//        return SecurityFilterChain.REQUEST_WRAPPER_FILTER_MAX_ORDER - 1;
//    }
//
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
