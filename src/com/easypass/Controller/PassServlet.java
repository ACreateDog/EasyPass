package com.easypass.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easypass.Modal.RecordModel;

import net.sf.json.JSONObject;


public class PassServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); 
		String snum = req.getParameter("snum");
		String other = req.getParameter("other" );
		String type = req.getParameter("type");
		String detail = req.getParameter("detail");
		System.out.println(detail);
		boolean issuccess = false;
		RecordModel recordModel;
		if (type == "其他") {
			System.out.println("学号："+snum +"类型："+type+"描述："+detail+"其他："+other);
			recordModel = RecordModel.getLastRecordOfType(snum, type);
			recordModel.setThingdetail(detail);
			recordModel.setType(type);
			PassController passController = new PassController(recordModel);
			issuccess = passController.pass(true); 
		}else{
			System.out.println("学号："+snum +"类型："+type+"描述："+detail);
			recordModel = RecordModel.getLastRecordOfType(snum, type);
			recordModel.setThingdetail(detail);
			recordModel.setType(type);
			PassController passController = new PassController(recordModel);
			issuccess = passController.pass();
		}
		if (issuccess) {
			JSONObject object = NotifiToOtherController.send("学号："+snum+"姓名："+recordModel.getStudentModel().getName());
			if (object.get("content").equals("OK")) {
				resp.getWriter().append(issuccess +"");
			}
		}else{
			resp.getWriter().append(issuccess +"");
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		super.doGet(req, resp);
	}
}
