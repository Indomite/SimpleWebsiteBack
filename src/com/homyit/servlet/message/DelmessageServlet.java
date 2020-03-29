package com.homyit.servlet.message;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homyit.dao.MessageDao;

/**
 * Servlet implementation class DelmessageServlet
 */
@WebServlet("/DelmessageServlet")
public class DelmessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDao dao = new MessageDao();
		String messageid = request.getParameter("messageid");
		
		if(dao.delete(messageid)) {
			System.out.println("ɾ���ɹ�");
			//request.getSession().setAttribute("message", "ɾ���ɹ�");
			//response.sendRedirect("xxxx");
		}else {
			System.out.println("ɾ��ʧ��");
			//request.getSession().setAttribute("message", "ɾ��ʧ��");
			//response.sendRedirect("xxxx");
		}
	}

}
