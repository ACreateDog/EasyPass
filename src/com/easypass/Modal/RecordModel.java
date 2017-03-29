package com.easypass.Modal;

import com.easypass.Controller.EasyPassDBController;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RecordModel {
	
	private StudentModel studentModel  = null;
	private long passtime = 0;
	private String thingdetail = null;
	private String type = null;
	private static String SELECT_SQL  = "SELECT "
			+ "record.snum,sname,"
			+ "phone,"
			+ "department,"
			+ "class,"
			+ "cardID,"
			+ "passtime,"
			+ "thingdetail,"
			+ "type "
			+ "FROM record ,student "
			+ "where record.snum = student.snum ORDER BY passtime desc limit 10";
	private static String SELECT_SQL_BY_NAME  = "SELECT "
			+ "record.snum,sname,"
			+ "phone,"
			+ "department,"
			+ "class,"
			+ "cardID,"
			+ "passtime,"
			+ "thingdetail,"
			+ "type "
			+ "FROM record ,student "
			+ "where record.snum = student.snum ORDER BY passtime desc limit 10";
	public RecordModel() {
		
		
	}
//	public static void main(String[] args) throws SQLException {
//		getPassRecords();
//	}
	
	public static JSONArray getPassRecords() throws SQLException {
		JSONArray object = new JSONArray();
		EasyPassDBController dbController = EasyPassDBController.getInstance();
		Connection connection = dbController.getConnection();
		PreparedStatement statement =  connection.prepareStatement(SELECT_SQL);
		ResultSet set =  statement.executeQuery();
		
		if (!set.wasNull()) {
		
			ArrayList<JSONObject> list = new ArrayList<JSONObject>();
			while (set.next()) {
				JSONObject jsObject = new JSONObject();
				jsObject.put("snum", set.getString(1));
				jsObject.put("sname", set.getString(2));
				jsObject.put("phone", set.getString(3));
				jsObject.put("department", set.getString(4));
				jsObject.put("class", set.getString(5));
				jsObject.put("cardID", set.getString(6));
				jsObject.put("passtime", set.getLong(7));
				jsObject.put("thingdetail", set.getString(8));
				jsObject.put("type", set.getString(9));
				
				list.add(jsObject);
				
			}
			 object = JSONArray.fromObject(list);
		}
		System.out.println(object);
		return object;
	}
	public static JSONArray getPassRecords(String snum) throws SQLException {
		JSONArray object = new JSONArray();
		EasyPassDBController dbController = EasyPassDBController.getInstance();
		Connection connection = dbController.getConnection();
		PreparedStatement statement =  connection.prepareStatement(SELECT_SQL);
		ResultSet set =  statement.executeQuery();
		
		if (!set.wasNull()) {
		
			ArrayList<JSONObject> list = new ArrayList<JSONObject>();
			while (set.next()) {
				JSONObject jsObject = new JSONObject();
				jsObject.put("snum", set.getString(1));
				jsObject.put("sname", set.getString(2));
				jsObject.put("phone", set.getString(3));
				jsObject.put("department", set.getString(4));
				jsObject.put("class", set.getString(5));
				jsObject.put("cardID", set.getString(6));
				jsObject.put("passtime", set.getLong(7));
				jsObject.put("thingdetail", set.getString(8));
				jsObject.put("type", set.getString(9));
				
				list.add(jsObject);
				
			}
			 object = JSONArray.fromObject(list);
		}
		System.out.println(object);
		return object;
	}
//	public JSONObject getPassRecords(int count) throws SQLException {
//		RecordModel[] rModels = getPassRecords();
//		JSONObject object = new JSONObject();
//		for (int i = 0; i < rModels.length; i++) {
//			RecordModel rModel = rModels[i];
//			JSONObject mJsonObject = new JSONObject();
//			mJsonObject.put("snum",rModel.getStudentModel().getSnum());
//			mJsonObject.put("sname", rModel.getStudentModel().getName());
//			mJsonObject.put("ID", rModel.getStudentModel().getCardID());
//			
//			
//		}
//		return object;
//		
//	}
	public static RecordModel getLastRecordOfType(String snum,String type) {
		
		EasyPassDBController eDbController = EasyPassDBController.getInstance();
		System.out.println("getLastRecordOfType:"+eDbController);
		RecordModel recordModel = eDbController.getLastRecord(snum, type);
		
		return recordModel;
	}
	public boolean isFirstPass(String snum,String type) {
		
		
		return false;
	}
	public String toString() {
		return "RecordModel [studentModel=" + studentModel + ", passtime=" + passtime + ", thingdetail=" + thingdetail
				+ ", type=" + type + "]";
	}
	
	public void setStudentModel(StudentModel studentModel) {
		this.studentModel = studentModel;
	}
	public StudentModel getStudentModel() {
		return studentModel;
	}
	public long getPasstime() {
		return passtime;
	}
	public void setPasstime(long passtime) {
		this.passtime = passtime;
	}
	public String getThingdetail() {
		return thingdetail;
	}
	public void setThingdetail(String thingdetail) {
		this.thingdetail = thingdetail;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
		
}
