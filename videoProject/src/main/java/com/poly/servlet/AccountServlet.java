package com.poly.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.bean.User;
import com.poly.utils.UserDAO;

@WebServlet({"/account/index","/account/sign-up", "/account/sign-in", "/account/sign-out","/account/forgot-password","/account/change-password", "/account/edit-profile"})
public class AccountServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("bai", "bai4/signup.jsp");
		req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		if(uri.contains("sign-in")) {
			this.doSignIn(req, resp);
		}
		else if(uri.contains("sign-up")) {
			this.doSignUp(req, resp);
		}
		else if(uri.contains("sign-out")) {}
		else if(uri.contains("forgot-password")) {}
		else if(uri.contains("change-password")) {}
		else if(uri.contains("edit-profile")) {
			this.doEditProfile(req, resp);
		}
	}
	private void doSignIn(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String method = req.getMethod();

		if(method.equalsIgnoreCase("POST")) {
			// TODO: Ä�Ä‚NG NHáº¬P
			String id = req.getParameter("id");
			String pw = req.getParameter("password");

			try {
				
				UserDAO dao = new UserDAO();
				User user = dao.findById(id);
				if(!user.getPassword().equals(pw)) {
					req.setAttribute("message", "Wrong password!");
					req.setAttribute("check", "0");
					req.setAttribute("bai", "bai4/signup.jsp");
					req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
				}
				else {
//					req.setAttribute("message", "Login suscess!");
					
//					req.setAttribute("check", "1");
					if(user.getAdmin()) {
						req.getSession().setAttribute("menuAdmin", "display: list-item;");
					}else {
						req.getSession().setAttribute("menuAdmin", null);
					}
					
					req.getSession().setAttribute("user", user);
					req.getSession().setAttribute("cssServlet", "display:none");
					resp.sendRedirect("/PS16590_NguyenLeDuyTien_ASM1/video/video-list");
					return;
				}
				
			} catch (Exception e) {
				req.setAttribute("message", "Wrong id!");
				req.setAttribute("check", "0");
			}
		}
		req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
	}
	private void doSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if(method.equalsIgnoreCase("POST")) {
			// TODO: Ä�Ä‚NG KÃ�
			try {
				
				User entity = new User();
				BeanUtils.populate(entity, req.getParameterMap());

				UserDAO dao = new UserDAO();
				dao.create(entity);
				req.setAttribute("message", "Sign up suscess!");
				req.setAttribute("check", "1");
				
			} catch (Exception e) {
				req.setAttribute("message", "Sign up fail!");
				req.setAttribute("check", "0");
			}

		}
		req.setAttribute("bai", "bai4/signup.jsp");
		req.getRequestDispatcher("/views/menu.jsp").forward(req, resp);
	}
	private void doEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		String method = req.getMethod();
		if(method.equalsIgnoreCase("POST")) {
			// TODO: Cáº¬P NHáº¬T
			try {
				
				BeanUtils.populate(user, req.getParameterMap());

				UserDAO dao = new UserDAO();
				dao.update(user);
				req.setAttribute("message", "Update suscess!");
				req.setAttribute("check", "1");
			} catch (Exception e) {
				req.setAttribute("message", "Update fail!");
				req.setAttribute("check", "0");
			}

		}
		req.getRequestDispatcher("/views/bai4/edit.jsp").forward(req, resp);
	}

}
