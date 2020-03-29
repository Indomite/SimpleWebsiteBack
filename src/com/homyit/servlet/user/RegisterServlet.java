package com.homyit.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homyit.dao.UserDao;
import com.homyit.entity.UserEntity;
import com.homyit.util.Aesmanage;

import net.sf.json.JSONObject;

/***
 * �û�ע��
 * @author 1979
 *
 */

@WebServlet("/RegisterServlet")

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rpsw = request.getParameter("rpsw");
		String email = request.getParameter("email");
		System.out.println(username);
		System.out.println(password);
		System.out.println(rpsw);
		System.out.println(email);
		JSONObject jsonObject = new JSONObject();
		
//		if(username==null||username.trim().isEmpty()){
//			System.out.println("�û�������Ϊ��");
//			jsonObject.put("message", "�û�������Ϊ��");
//			//request.setAttribute("registError", "�û�������Ϊ��");
//			//request.getRequestDispatcher(request.getContextPath()+"/regist.html").forward(request, response);
//			
//		}
//		else if(password==null||password.trim().isEmpty()){
//			System.out.println("���벻��Ϊ��");
//			jsonObject.put("message", "���벻��Ϊ��");
//			
//		}
		
//		 else if(!password.equals(rpsw)){ 
//			 System.out.println("���벻һ��");
//			 jsonObject.put("message", "���벻һ��"); 
//			 request.setAttribute("registError","���벻һ��");
//			 request.getRequestDispatcher(request.getContextPath()+"/regist.html").forward(request, response);
//		 }
		 
			UserDao dao = new UserDao();
			if(UserDao.findUsername().contains(username)) {
				jsonObject.put("message", "ע��ʧ�ܣ����û����Ѵ���");
				System.out.println("ע��ʧ�ܣ����û����Ѵ���");
			}else {
				UserEntity user =new UserEntity();
				Aesmanage aesmanage = new Aesmanage();
				byte[] aespassword = aesmanage.encrypt(password, "123456");
				password = aesmanage.parseByte2HexStr(aespassword);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				boolean res = dao.insert(user);
				if(res){
					jsonObject.put("message", "ע��ɹ�");
				}else {
					jsonObject.put("message", "δ֪����ע��ʧ��");
				}	
			}
		
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
	}
}