package edu.nju.controller;

import edu.nju.model.SystemContext;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dora on 2016/5/3.
 * 分页Filter，用于获取offSet和pageSize的值
 */
public class PageFilter implements Filter {
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest=(HttpServletRequest)request;

        SystemContext.setOffset(getOffSet(httpRequest));
        SystemContext.setSize(getPageSize());

        try {
            chain.doFilter(request, response);
        }
        //使用完Threadlocal，将其删除。使用finally确保一定将其删除
        finally{
            SystemContext.removeOffset();
            SystemContext.removeSize();
        }
    }
    /**
     * 获得pager.offset参数的值
     * @param request
     * @return
     */
    protected int getOffSet(HttpServletRequest request) {
        int offSet = 0;
        try {
            String pageOff = request.getParameter("pager.offset");
            if (pageOff != null) {
                offSet =Integer.parseInt(pageOff);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return offSet;
    }
    /**
     * 设置默认每页大小
     * @return
     */
    protected int getPageSize() {
        return 10;
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {}
}

