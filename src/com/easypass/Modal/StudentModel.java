package com.easypass.Modal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.easypass.Controller.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentModel extends Modal {
	private String name;
	private String snum; 
	private String phone;
	private String sclass;
	private String department;
	private String cardID;
	final   static int PAGE_SIZE = 15; 
	private static String SELECT_STUDENT         = "SELECT * FROM student ";
	private static String INSERT_STUDENT         = "insert into student(snum,sname,phone,department,class,cardID) values(?,?,?,?,?,?)";
	
	private static String SELECT_STUDENT_BY_PAGE = "SELECT * FROM student where id limit ?,?";
	
	public StudentModel() {
		super();
	}
	public void addStudent() {
		try {
			
			PreparedStatement pStatement = (PreparedStatement)this.getConnection().prepareStatement(INSERT_STUDENT);
			long count = Long.parseLong("20141514114");
			long id = Long.parseLong("412726199507191613");
			for (int i = 0; i < 1000; i++) {
				pStatement.setString(1, count+"");
				pStatement.setString(2, "王二"+i);
				pStatement.setString(3, "15237381295");
				pStatement.setString(4, "信息工程学院");
				pStatement.setString(5, "计科141");
				pStatement.setString(6, id+"");
				
				int j = pStatement.executeUpdate();
				System.out.println(j);
				count++;
				id++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public StudentModel getInstenceBySnum(String snum) {
		
		EasyPassDBController eController = EasyPassDBController.getInstance();
		StudentModel studentModel =  eController.getStudentBySnum(snum);
	
		return studentModel;
	}
	public static void main(String[] args) {
		StudentModel studentModel = new StudentModel();
		studentModel.addStudent();;
	}
	public  JSONArray  getStudentInfo(int start) {
		JSONArray object = new JSONArray();
		try {
			PreparedStatement preparedStatement = this.getConnection().prepareStatement(SELECT_STUDENT_BY_PAGE);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, PAGE_SIZE);
			
			ResultSet set = preparedStatement.executeQuery();

			if (!set.wasNull()) {
			
				ArrayList<JSONObject> list = new ArrayList<JSONObject>();
				while (set.next()) {
					JSONObject jsObject = new JSONObject();
					jsObject.put("snum", set.getString(2));
					jsObject.put("sname", set.getString(3));
					jsObject.put("phone", set.getString(4));
					jsObject.put("department", set.getString(5));
					jsObject.put("cls", set.getString(6));
					jsObject.put("cardID", set.getString(7));
					
					list.add(jsObject);
				}
				 object = JSONArray.fromObject(list);
			}	
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return object;
	}
	public String toString() {
		return "StudentModel [name=" + name + ", snum=" + snum + ", phone=" + phone + ", sclass=" + sclass
				+ ", department=" + department + ", cardID=" + cardID + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSnum() {
		return snum;
	}



	public void setSnum(String snum) {
		this.snum = snum;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getSclass() {
		return sclass;
	}



	public void setSclass(String sclass) {
		this.sclass = sclass;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getCardID() {
		return cardID;
	}



	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	
}
