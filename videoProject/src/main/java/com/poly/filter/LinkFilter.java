package com.poly.filter;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.bean.User;

@WebFilter({"/video/video-detail"})
public class LinkFilter implements HttpFilter{
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		String url = req.getParameter("url");
		String title = req.getParameter("title");
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("title", title);
		System.out.println("đã qua");
		
		chain.doFilter(req, resp);
	}
}
