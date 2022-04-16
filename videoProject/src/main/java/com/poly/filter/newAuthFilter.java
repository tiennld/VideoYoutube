package com.poly.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.bean.User;



@WebFilter({"/video/share","/video/like"})
public class newAuthFilter implements HttpFilter{
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();
		User user = (User) req.getSession().getAttribute("user");
		String error="";
		String checkLogin = null;

		if(user==null) {//chua dang nhap
			checkLogin = "0";
			error = "Please login!";
		}
		System.out.println(error);
		if(!error.isEmpty()) {// truy cap kg hop le
			req.getSession().setAttribute("checkLogin", checkLogin);
			req.getSession().setAttribute("message", error);
			System.out.println(error);
			resp.sendRedirect("/PS16590_NguyenLeDuyTien_ASM1/views/signup.jsp");
		}else {//truy cap hop le
			checkLogin = "1";
			req.getSession().setAttribute("checkLogin", checkLogin);
			chain.doFilter(req, resp);

		}
				
	}
}
