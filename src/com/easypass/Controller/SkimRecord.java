package com.easypass.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easypass.Modal.RecordModel;
import com.easypass.Modal.StudentModel;

public class SkimRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SkimRecord() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath()).append("get");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		try {
			String type = request.getParameter("type");
			
			if (type.equals("record")) {
				response.getWriter().append(RecordModel.getPassRecords().toString());
			}else if (type.equals("student")) {
				StudentModel studentModel = new StudentModel();
				
				response.getWriter().append(studentModel.getStudentInfo(0).toString());
			}
				
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
