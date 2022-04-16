package com.poly.servlet;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.bean.User;
import com.poly.utils.UserDAO;

@WebServlet({"/user/index-user","/user/edit/*","/user/create","/user/update","/user/delete","/user/page"})
public class UserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		UserDAO dao = new UserDAO();
		User user = new User();
		String uri = req.getRequestURI();
		req.setAttribute("check", "2");

		if (uri.contains("edit")) {
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			user = dao.findById(id);
		} else if (uri.contains("create")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());
				dao.create(user);
				req.setAttribute("message", "Create suscess!");
				req.setAttribute("check", "1");
			} catch (Exception e) {
				req.setAttribute("message", "Create fail!");
				req.setAttribute("check", "0");
			}
		} else if (uri.contains("update")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());
				dao.update(user);
				req.setAttribute("message", "Update suscess!");
				req.setAttribute("check", "1");
			} catch (Exception e) {
				req.setAttribute("message", "Update fail!");
				req.setAttribute("check", "0");
			}
		} else if (uri.contains("delete")) {
			try {
				String id = req.getParameter("id");
				dao.remove(id);
				req.setAttribute("message", "Delete suscess!");
				req.setAttribute("check", "1");
			} catch (Exception e) {
				req.setAttribute("message", "Delete fail!");
				req.setAttribute("check", "0");
			}
		}else if(uri.contains("page")) {
			String numberPage = req.getParameter("numberPage");
			List<User> list = dao.pageOne(Integer.parseInt(numberPage), 5);
			
			req.setAttribute("form", user);
			req.setAttribute("number", dao.countPage(5, dao.countAll()));
			req.setAttribute("items", list);
			req.setAttribute("bai", "bai3/bai3.jsp");
			req.getRequestDispatcher("/views/manegeUser.jsp").forward(req, resp);
			return;
		}
		req.setAttribute("form", user);
		  req.setAttribute("number", dao.countPage(5, dao.countAll()));
		req.setAttribute("items", dao.pageOne(1, 5));

		req.getRequestDispatcher("/views/manegeUser.jsp").forward(req, resp);

	}
}
