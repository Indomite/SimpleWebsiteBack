package com.homyit.servlet.message;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homyit.dao.MessageDao;
import com.homyit.entity.MessageEntity;

import net.sf.json.JSONObject;

/**
 * 留言
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-All"
        		+ "ow-Methods", "POST, GET, OPTIONS, DELETE");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String content = request.getParameter("content");
		String username = request.getParameter("username");
		
		System.out.println(username);
		System.out.println(content);
		
		JSONObject jsonObject = new JSONObject();
		if(content==null||content.trim().isEmpty()){
			System.out.println("内容不能为空");
			jsonObject.put("message", "内容不能为空");
			PrintWriter out = response.getWriter();
			out.write(jsonObject.toString());
		}else {
			MessageDao dao = new MessageDao();
			MessageEntity Message =new MessageEntity();
			Message.setContent(content);
			Message.setAuthor(username);
			//是否添加成功
			boolean res = dao.insert(Message);
			if(res){
				jsonObject.put("message", "添加成功");
				PrintWriter out = response.getWriter();
				out.write(jsonObject.toString());
				}
			else {
				jsonObject.put("message", "未知错误，添加失败");
				PrintWriter out = response.getWriter();
				out.write(jsonObject.toString());
			}
			}
		}
	}
