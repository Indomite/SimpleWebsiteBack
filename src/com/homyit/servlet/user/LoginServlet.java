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
 *    登录
 * @author 糯米团
 *
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		
		//根据用户名查询用户
		UserEntity user =new UserDao().selectByUsername(username);
		Aesmanage aesmanage = new Aesmanage();
		JSONObject jsonObject = new JSONObject();
		if(user!=null)
		{
			String getPassword = user.getPassword();
			System.out.println(getPassword);
			byte[] aespwdfrom = aesmanage.parseHexStr2Byte(getPassword);
			byte[] aespwdres = aesmanage.decrypt(aespwdfrom, "123456");
			getPassword = String.valueOf(aespwdres);
			System.out.println(getPassword);
			
				if(getPassword.equals(password)){
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("loginError", "* 登录成功");
					jsonObject.put("message", "登录成功");
		            //System.out.println("登录成功");
				}else {
					//System.out.println("* 密码错误");
					request.getSession().setAttribute("loginError", "* 密码错误");
					jsonObject.put("message", "密码错误");
				}
			}else {
				//System.out.println("* 用户不存在");
				request.getSession().setAttribute("loginError", "* 用户不存在");
				jsonObject.put("message", "用户不存在");
			}
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
		}
	}
