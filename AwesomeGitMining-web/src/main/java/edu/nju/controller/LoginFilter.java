package edu.nju.controller;

import edu.nju.model.User;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by tj on 2016/5/6.
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
//            throw new ServletException("OncePerRequestFilter just supports HTTP requests");
//        }
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        HttpSession session = httpRequest.getSession(true);
//
//        StringBuffer url = httpRequest.getRequestURL();
//        String[] strs = ProsReader.getString("INDICATION_APP_NAME").split("\\|");
//        if (strs != null && strs.length > 0) {
//            for (String str : strs) {
//                if (url.indexOf(str) >= 0) {
//                    filterChain.doFilter(servletRequest, servletResponse);
//                    return;
//                }
//            }
//        }
//        Object object = session.getAttribute(SessionConstants.SESSION_USER);
//        User user = object == null ? null : (User) object;
//        if (user == null) {
//            boolean isAjaxRequest = isAjaxRequest(httpRequest);
//            if (isAjaxRequest) {
//                httpResponse.setCharacterEncoding("UTF-8");
//                httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(),
//                        "您已经太长时间没有操作,请刷新页面");
//            }
//            httpResponse.sendRedirect("/page/login/user_login");
//            return;
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    /**
     * 判断是否为Ajax请求
     *
     * @param request HttpServletRequest
     * @return 是true, 否false
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api");
//        String requestType = request.getHeader("X-Requested-With");
//        return requestType != null && requestType.equals("XMLHttpRequest");
    }

    @Override
    public void destroy() {

    }
}
