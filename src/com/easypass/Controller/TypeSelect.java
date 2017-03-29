package com.easypass.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class TypeSelect
 */
//@WebServlet("/TypeSelect")
public class TypeSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeSelect() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String snum = request.getParameter("snum");
		EasyPassDBController eController = EasyPassDBController.getInstance();
		JSONObject jsObject =  eController.getTypeModel(snum);
		
		response.getWriter().append("Served at: ").append(request.getContextPath()+jsObject.toString());
	}

}
