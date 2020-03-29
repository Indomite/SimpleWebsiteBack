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
 * 查找留言
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
		 int pageNum =Integer.parseInt(request.getParameter("pageNum"));//获取页码
		 int totalRecord = messagedao.getCount();//获取总记录数 
		 int pageSize = 3;//每页显示3条数据
		 pagemanage = new Pagemanage<MessageEntity>(pageNum, totalRecord, pageSize); //查询当前页对应的数据
		 List<MessageEntity> list = messagedao.findAll(pagemanage.getIndex(),pageSize); 
		 pagemanage.setList(list);
		 pagemanage.setTotalPage(pagemanage.getTotalPage());
//		  
//		 request.getSession().setAttribute("list", list);
//		 request.getSession().setAttribute("pagemanage", pagemanage);
//		 response.sendRedirect("message.jsp");
		
	}
	
}
