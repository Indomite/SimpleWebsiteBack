package com.homyit.servlet.message;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homyit.dao.MessageDao;
import com.homyit.entity.MessageEntity;
import com.homyit.util.Pagemanage;

/***
 * ��������
 * @author 1979
 */

@WebServlet("/FindmessageServlet")
public class FindmessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 Pagemanage<MessageEntity> pagemanage = null;
		 MessageDao messagedao = new MessageDao(); 
		 int pageNum =Integer.parseInt(request.getParameter("pageNum"));//��ȡҳ��
		 int totalRecord = messagedao.getCount();//��ȡ�ܼ�¼�� 
		 int pageSize = 3;//ÿҳ��ʾ3������
		 pagemanage = new Pagemanage<MessageEntity>(pageNum, totalRecord, pageSize); //��ѯ��ǰҳ��Ӧ������
		 List<MessageEntity> list = messagedao.findAll(pagemanage.getIndex(),pageSize); 
		 pagemanage.setList(list);
		 pagemanage.setTotalPage(pagemanage.getTotalPage());
//		  
//		 request.getSession().setAttribute("list", list);
//		 request.getSession().setAttribute("pagemanage", pagemanage);
//		 response.sendRedirect("message.jsp");
		
	}
	
}
