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
 * 用户注册
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
//			System.out.println("用户名不能为空");
//			jsonObject.put("message", "用户名不能为空");
//			//request.setAttribute("registError", "用户名不能为空");
//			//request.getRequestDispatcher(request.getContextPath()+"/regist.html").forward(request, response);
//			
//		}
//		else if(password==null||password.trim().isEmpty()){
//			System.out.println("密码不能为空");
//			jsonObject.put("message", "密码不能为空");
//			
//		}
		
//		 else if(!password.equals(rpsw)){ 
//			 System.out.println("密码不一致");
//			 jsonObject.put("message", "密码不一致"); 
//			 request.setAttribute("registError","密码不一致");
//			 request.getRequestDispatcher(request.getContextPath()+"/regist.html").forward(request, response);
//		 }
		 
			UserDao dao = new UserDao();
			if(UserDao.findUsername().contains(username)) {
				jsonObject.put("message", "注册失败，该用户名已存在");
				System.out.println("注册失败，该用户名已存在");
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
					jsonObject.put("message", "注册成功");
				}else {
					jsonObject.put("message", "未知错误，注册失败");
				}	
			}
		
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
	}
}