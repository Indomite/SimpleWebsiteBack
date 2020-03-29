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
 * ����
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
			System.out.println("���ݲ���Ϊ��");
			jsonObject.put("message", "���ݲ���Ϊ��");
			PrintWriter out = response.getWriter();
			out.write(jsonObject.toString());
		}else {
			MessageDao dao = new MessageDao();
			MessageEntity Message =new MessageEntity();
			Message.setContent(content);
			Message.setAuthor(username);
			//�Ƿ���ӳɹ�
			boolean res = dao.insert(Message);
			if(res){
				jsonObject.put("message", "��ӳɹ�");
				PrintWriter out = response.getWriter();
				out.write(jsonObject.toString());
				}
			else {
				jsonObject.put("message", "δ֪�������ʧ��");
				PrintWriter out = response.getWriter();
				out.write(jsonObject.toString());
			}
			}
		}
	}
