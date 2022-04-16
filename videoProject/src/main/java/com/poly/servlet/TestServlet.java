package com.poly.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.bean.User;

@WebServlet("/userrrr")
public class TestServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> list = new ArrayList<>();
		list.add("Video 1");
		list.add("Video 2");
		list.add("Video 3");
		list.add("Video 4");
		list.add("Video 5");
		list.add("Video 6");
		System.out.println(list.size());
		req.setAttribute("video", list);
		req.getRequestDispatcher("/views/hihi.jsp").forward(req, resp);
	}
	
}
