package com.poly.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.bean.Favorite;
import com.poly.bean.User;
import com.poly.bean.Video;
import com.poly.utils.UserDAO;
import com.poly.utils.VideoDAO;

/**
 * Servlet implementation class VideoServlet
 */
@WebServlet({"/video/video-list","/video/video-detail","/video/sigList",
	"/video/sigDetail","/video/liked-videos","/video/page","/video/share",
	"/video/index-video","/video/edit/*","/video/create","/video/update","/video/delete",
	"/video/video-number","/video/like","/video/search"	
})
public class VideoServlet extends HttpServlet {

       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("video-detail")) {		
			handleVideoDetail(request,response);
		}
		else if(uri.contains("video-list")){
			this.handleVideoList(request,response);
		}else if(uri.contains("sigList")) {
			handleVideoSignout(request,response);
		}else if(uri.contains("sigDetail")){
			handleVideoSignout(request,response);
		}else if(uri.contains("liked-videos")){
			handleVideoFavorite(request,response);
		}else if(uri.contains("page")){
			handleVideoPage(request,response);
		}else if(uri.contains("search")){
			handleVideoSearch(request,response);
		}else if(uri.contains("share")){
			handleSendMail(request,response);
		}if (uri.contains("edit")) {
			VideoDAO dao = new VideoDAO();
			Video video = new Video();
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			video = dao.findById(id);
			request.setAttribute("form", video);
			request.setAttribute("number", dao.countPage(5, dao.countAll()));
			request.setAttribute("items", dao.pageOne(1, 5));
			request.getRequestDispatcher("/views/manegeVideo.jsp").forward(request, response);
		} else if (uri.contains("create")) {
			VideoDAO dao = new VideoDAO();
			Video video = new Video();
			try {
				
				BeanUtils.populate(video, request.getParameterMap());
				dao.create(video);
				request.setAttribute("message", "Create suscess!");
				request.setAttribute("check", "1");
			} catch (Exception e) {
				request.setAttribute("message", "Create fail!");
				request.setAttribute("check", "0");
			}
			request.setAttribute("form", video);
			request.setAttribute("number", dao.countPage(5, dao.countAll()));
			request.setAttribute("items", dao.pageOne(1, 5));
			request.getRequestDispatcher("/views/manegeVideo.jsp").forward(request, response);
		} else if (uri.contains("update")) {
			VideoDAO dao = new VideoDAO();
			Video video = new Video();
			try {
				
				BeanUtils.populate(video, request.getParameterMap());
				dao.update(video);
				request.setAttribute("message", "Update suscess!");
				request.setAttribute("check", "1");
			} catch (Exception e) {
				request.setAttribute("message", "Update fail!");
				request.setAttribute("check", "0");
			}
			request.setAttribute("form", video);
			request.setAttribute("number", dao.countPage(5, dao.countAll()));
			request.setAttribute("items", dao.pageOne(1, 5));
			request.getRequestDispatcher("/views/manegeVideo.jsp").forward(request, response);
		} else if (uri.contains("delete")) {
			VideoDAO dao = new VideoDAO();
			Video video = new Video();
			try {
				
				String id = request.getParameter("id");
				dao.remove(id);
				request.setAttribute("message", "Delete suscess!");
				request.setAttribute("check", "1");
				
			} catch (Exception e) {
				request.setAttribute("message", "Delete fail!");
				request.setAttribute("check", "0");
			}
			request.setAttribute("form", video);
			request.setAttribute("number", dao.countPage(5, dao.countAll()));
			request.setAttribute("items", dao.pageOne(1, 5));
			request.getRequestDispatcher("/views/manegeVideo.jsp").forward(request, response);
		}else if(uri.contains("video-number")) {
			VideoDAO dao = new VideoDAO();
			Video video = new Video();
			String numberPage = request.getParameter("numberPage");
			List<Video> list = dao.pageOne(Integer.parseInt(numberPage), 5);
			
			request.setAttribute("form", video);
			request.setAttribute("number", dao.countPage(5, dao.countAll()));
			request.setAttribute("items", list);
			request.getRequestDispatcher("/views/manegeVideo.jsp").forward(request, response);
		}else if(uri.contains("index-video")){
			VideoDAO dao = new VideoDAO();
			Video video = new Video();
			request.setAttribute("form", video);
			request.setAttribute("number", dao.countPage(5, dao.countAll()));
			request.setAttribute("items", dao.pageOne(1, 5));

			request.getRequestDispatcher("/views/manegeVideo.jsp").forward(request, response);
		}else if(uri.contains("like")) {
			this.handleVideoLike(request, response);
		}
		
	}
	
	//Xu ly trong videoList.jsp
	protected void handleVideoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Video video = new Video();
//		VideoDAO dao = new VideoDAO();
//		List<Video> list = dao.findAll();
//		request.setAttribute("items", list);
//		request.getRequestDispatcher("/views/videoList.jsp").forward(request, response);
		
//				EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
//				EntityManager em = emf.createEntityManager();
//				try {
//					em.getTransaction().begin();
//					VideoDAO dao = new VideoDAO();
//					Query query = em.createNamedQuery("Report.random");
//					List<Video> list = query.getResultList();
//					for (int i = 0; i < list.size(); i++) {
//						System.out.println(list.get(i).getTitle());
//					}
//					request.setAttribute("number", dao.countPage(12, dao.countAll()));
//					request.setAttribute("items", dao.pageOne(1, 12));
//					// --------------------------------------------------------
//					em.getTransaction().commit(); 
//				} catch (Exception e) {
//					em.getTransaction().rollback(); 
//
//				}
//				em.close();
//				emf.close();
//				request.getRequestDispatcher("/views/videoList.jsp").forward(request, response);
				
				VideoDAO dao = new VideoDAO();
				request.setAttribute("number", dao.countPage(12, dao.countAll()));
				request.setAttribute("items", dao.pageOne(1, 12));
				request.getRequestDispatcher("/views/videoList.jsp").forward(request, response);
				
	}
	
	//Xu ly trong videoDetail.jsp
	protected void handleVideoDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String url = request.getParameter("url");
//		String title = request.getParameter("title");
//		String poster = request.getParameter("poster");
//		Video video = new Video();
//		VideoDAO dao = new VideoDAO();
//		List<Video> list = dao.findAll();
//		//Loại được video đang chọn trên list
//		for(int i = 0; i < list.size();i++) {
//			if(list.get(i).getId().equals(url)) {
//				list.remove(i);
//				break;
//			}
//		}
//		request.setAttribute("items", list);
//		request.setAttribute("urlDetail", url);
//		request.setAttribute("titleDetail", title);
//		request.getRequestDispatcher("/views/videoDetail.jsp").forward(request, response);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			String url = (String)request.getSession().getAttribute("url");
			String title = (String)request.getSession().getAttribute("title");
			String poster = (String)request.getSession().getAttribute("url");

//			String url = request.getParameter("url");
//			String title = request.getParameter("title");
//			String poster = url;

			Video video = new Video();
			VideoDAO dao = new VideoDAO();
			Video vidID = dao.findById(url);
			User user = (User)request.getSession().getAttribute("user");

			
			
			if(vidID != null && user != null) {
				TypedQuery<Video> queryVidID = em.createNamedQuery("Video.findFavoriteVideo", Video.class);
				queryVidID.setParameter("vidid", vidID.getId());
				queryVidID.setParameter("userid", user.getId());
				List<Video> listTemp = queryVidID.getResultList();
				if(listTemp.size() != 0) {
					request.setAttribute("fav", "Unlike");
				}else {
					request.setAttribute("fav", "Like");
				}
			}else {
				request.setAttribute("fav", "Like");	
			}
			Query query = em.createNamedQuery("Report.random");
			List<Video> list = query.getResultList();
			for(int i = 0; i < list.size();i++) {
				if(list.get(i).getId().equals(url)) {
					list.remove(i);
					break;
				}
			}
			request.setAttribute("items", list);
			request.setAttribute("urlDetail", url);
			request.setAttribute("titleDetail", title);
			
			// --------------------------------------------------------
			em.getTransaction().commit(); 
		} catch (Exception e) {
			em.getTransaction().rollback(); 

		}
		em.close();
		emf.close();
		request.getRequestDispatcher("/views/videoDetail.jsp").forward(request, response);
	}
	
	protected void handleVideoSignout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute("cssServlet", "");
		request.getSession().setAttribute("menuAdmin", null);
		if(uri.contains("sigList")) {
			request.getRequestDispatcher("/video/video-list").forward(request, response);
		}else if(uri.contains("sigDetail")) {
			request.getRequestDispatcher("/video/video-detail").forward(request, response);
		}
			
	}
	
	protected void handleVideoFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
				EntityManager em = emf.createEntityManager();
				try {
					em.getTransaction().begin();
					TypedQuery<Video> query = em.createNamedQuery("Video.findByUser", Video.class);
					User user = (User) request.getSession().getAttribute("user");
					query.setParameter("id", user.getId());
					List<Video> list = query.getResultList();
					for (int i = 0; i < list.size(); i++) {
						System.out.println(list.get(i).getTitle());
					}
					request.setAttribute("items", list);
					// --------------------------------------------------------
					em.getTransaction().commit(); 
				} catch (Exception e) {
					em.getTransaction().rollback(); 

				}
				em.close();
				emf.close();
				request.getRequestDispatcher("/views/videoFavorite.jsp").forward(request, response);
	}
	
	protected void handleVideoSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<Video> query = em.createNamedQuery("Video.findByTitle", Video.class);
			String title = request.getParameter("searchValue");
			System.out.println(title);
			query.setParameter("keyword", "%" + title + "%");
			List<Video> list = query.getResultList();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getTitle());
			}
			request.setAttribute("items", list);
			// --------------------------------------------------------
			em.getTransaction().commit(); 
		} catch (Exception e) {
			em.getTransaction().rollback(); 

		}
		em.close();
		emf.close();
		request.getRequestDispatcher("/views/videoList.jsp").forward(request, response);
}
	
	protected void handleVideoLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Favorite fav = new Favorite();
			User user = (User) request.getSession().getAttribute("user");
			VideoDAO dao = new VideoDAO();
			Video video = dao.findById(request.getParameter("url"));
			fav.setUser(user);
			fav.setVideo(video);
			fav.setLikeDate(new Date());
			System.out.println("do chua i");
			String valuee = request.getParameter("btnName");
			System.out.println(valuee);
			if(valuee.equalsIgnoreCase("Like")) {
				em.persist(fav);
			}else {
				String hql = "DELETE FROM Favorite o WHERE o.video.id = :vidid and o.user.id = :userid";
				Query query = em.createQuery(hql);
				query.setParameter("vidid", video.getId());
				query.setParameter("userid", user.getId());
				int affectedRows = query.executeUpdate();
			}
			// --------------------------------------------------------
			em.getTransaction().commit(); 
		} catch (Exception e) {
			em.getTransaction().rollback(); 

		}
		em.close();
		emf.close();
		this.handleVideoDetail(request, response);	
}
	
	protected void handleVideoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDAO dao = new VideoDAO();
		String numberPage = request.getParameter("numberPage");
		List<Video> list = dao.pageOne(Integer.parseInt(numberPage), 12);
		
		request.setAttribute("number", dao.countPage(12, dao.countAll()));
		request.setAttribute("items", list);
		request.getRequestDispatcher("/views/videoList.jsp").forward(request, response);
	}
	
	protected void handleSendMail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//Thong so ket noi SMTP Server
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "587");
           
            //Ket noi SMTP Server
            Session s = Session.getInstance(p, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {                     	
                	String username = "";
                    String password = "";
                	return new PasswordAuthentication(username,password );
                }});          
            
            User user = (User) req.getSession().getAttribute("user");
            String from = user.getEmail();
            System.out.println(from);
    		String to = req.getParameter("to");	
    		System.out.println(to);
    		String subject = "From your friend: " + from;
    		System.out.println(subject);
    		String body = req.getParameter("body");
    		System.out.println(body);
    		
    		//Tao message
            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(body);
            //msg.setReplyTo(msg.getFrom());
    		
    		//Gui mail
            Transport.send(msg);
            
            req.setAttribute("message", "Gửi email thành công !");
            
        } catch (Exception ex) {           
            req.setAttribute("message", "Gửi email thất bại !");
            ex.printStackTrace();
        }   
		this.handleVideoDetail(req, resp);	

	}
}
