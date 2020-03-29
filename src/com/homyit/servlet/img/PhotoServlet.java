package com.homyit.servlet.img;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homyit.dao.PhotoDao;
import com.homyit.entity.PhotoEntity;

import net.sf.json.JSONArray;

@WebServlet("/PhotoServlet")
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PhotoDao dao = new PhotoDao();
		List<PhotoEntity> list = dao.findAll();
		PrintWriter writer = response.getWriter();
		JSONArray array = JSONArray.fromObject(list);
		writer.write(array.toString());
		//request.getSession().setAttribute("list", list);
		
	}

}
