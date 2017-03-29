package com.easypass.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easypass.Modal.RecordModel;

/**
 * Servlet implementation class LastRecordServlet
 */
@WebServlet("/LastRecordServlet")
public class LastRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LastRecordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String snum = request.getParameter("snum");
		String type = request.getParameter("type");
		
		PassController passController = new PassController();
		
		RecordModel recordModel = passController.getLastRecord(snum, type);
		if (recordModel == null) {
			System.out.println(recordModel);
			return;
		}
		System.out.println(recordModel);
		System.out.println("得到数据");
		
		response.getWriter().append(recordModel.getThingdetail());
	}

}
