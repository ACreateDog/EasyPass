package com.easypass.Controller;
import java.sql.*;

import org.apache.poi.ss.usermodel.Workbook;

import com.easypass.Modal.RecordModel;
import com.easypass.Modal.StudentModel;
import com.easypass.Modal.TypeModel;
import com.mysql.jdbc.PreparedStatement;

import net.sf.json.JSONObject;


public class EasyPassDBController{
	
	private final String STUDENT = "student";
	private final String GUARD   = "guard";
	private final String RECORD  = "record";
	private final String TYPE    = "type";
	
	public String dburl = "jdbc:mysql://localhost:3306/easypass?characterEncoding=utf8";
	public String user  = "root";
	public String password = "";
	
	private String selectstu_sql    = "select *  from " +STUDENT+" where snum=?";
	private String insertRecord_sql = "insert into "+RECORD+" (snum,passtime,thingdetail,type) values(?,?,?,?)";
	private String selectPrerecord_sql = "select id, snum ,passtime,thingdetail from "+RECORD+" where snum=? and type=?and passtime = (select max(passtime) passtime from "+RECORD+" where snum=? and type=?)";
	private String insertType_sql = "insert into " + TYPE +"(hoder,tname) values(?,?)";
	private String selectType_sql = "select * from "+TYPE+" where hoder in ('1',?)";
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	private static EasyPassDBController instance = null;
	
	public EasyPassDBController() {
		
		try {
			if (this.connection == null) {	
				this.connection = DriverManager.getConnection(this.dburl, this.user, this.password);	
			}	
		} catch (SQLException e) {
			System.out.println("链接出错啦");
			e.printStackTrace();
		}
	}
	
	public static EasyPassDBController getInstance() {
		if (instance == null) {
			synchronized (EasyPassDBController.class) {
				if (instance == null) {
					instance = new EasyPassDBController();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 
	 * student   
	 * 
	 */
	
	private ResultSet  selectStudentByNum(String val){
		ResultSet set = null;
		try {			
			PreparedStatement preparedStatement = (PreparedStatement)this.connection.prepareStatement(this.selectstu_sql);
			preparedStatement.setString(1, val);
			set = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return set;
	} 
	
	/**
	 * 根据学号得到学生信息
	 * @param snum 学号
	 * @return StudentModel 对象
	 */
	public StudentModel  getStudentBySnum(String snum) {
		//System.out.println(this.connection);
		
		ResultSet set = selectStudentByNum(snum);
		StudentModel studentModel = getStudentObject(set);
	
		return studentModel;
		
	}
	private StudentModel getStudentObject(ResultSet set){
		
		StudentModel studentModel = new StudentModel();
		
		try {
			set.next();
			studentModel.setSnum(set.getString(2));
			studentModel.setName(set.getString(3));
			studentModel.setPhone(set.getString(4));
			studentModel.setDepartment(set.getString(5));
			studentModel.setSclass(set.getString(6));
			studentModel.setCardID(set.getString(7));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentModel;
	}
	
	/**
	 * record 封装记录表
	 * 
	 */

	/**
	 * 得到最近的一次出行记录
	 * @param snum 学号
	 * @param type 物品类型
	 * @return
	 */
	
	public RecordModel getLastRecord(String snum ,String type){
		System.out.println(this.connection);
		ResultSet set = null;
		RecordModel recordModel = new RecordModel();
		recordModel.setStudentModel(getStudentBySnum(snum));
		set = selectLastRecord(snum, type);
		
		try {
			
			if (!set.next()) {
				recordModel.setThingdetail("");
				recordModel.setType("");
				
				return recordModel;
			}else {
				
				recordModel.setPasstime(set.getLong(3)); 
				recordModel.setThingdetail(set.getString(4));
				recordModel.setType(type);
				System.out.println("得到数据："+recordModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recordModel;
	}
	private ResultSet selectLastRecord(String snum ,String type){
		ResultSet set = null;
		PreparedStatement pStatement;
		try {
			pStatement = (PreparedStatement)this.connection.prepareStatement(selectPrerecord_sql);
			
			pStatement.setString(1, snum);
			pStatement.setString(2, type);
			pStatement.setString(3, snum);
			pStatement.setString(4, type);
			set = pStatement.executeQuery();
			System.out.println(pStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}
	private boolean insertRecord(String snum,String detail,String type){
		long time = System.currentTimeMillis();
		try {
			PreparedStatement pStatement =  (PreparedStatement) this.connection.prepareStatement(this.insertRecord_sql);
			pStatement.setString(1, snum);
			pStatement.setLong(2, time);
			pStatement.setString(3, detail);
			pStatement.setString(4, type);
			int i = pStatement.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * 添加一个记录
	 * @param record 记录对象
	 * @return 如果成功 返回true 否则返回false
	 */
	public boolean addRecord(RecordModel record) {
		String snum = record.getStudentModel().getSnum();
		String detail = record.getThingdetail();
		String type = record.getType();
		
		boolean issuccess =  insertRecord(snum, detail, type);
		if (issuccess) {
			return true;
		}
		return false;
	}
	
	/**
	 * 添加一个类别
	 */
	
	public boolean insertType(String snum,String type) {
		try {
			PreparedStatement statement = (PreparedStatement)this.connection.prepareStatement(insertType_sql);
			statement.setString(1, snum);
			statement.setString(2, type);
			System.out.println(statement.toString());
			int i =  statement.executeUpdate();
			
			
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			if (e.getSQLState().equals("23000")){
				System.out.println("已存在，插入失败！");
				return false;
			}
		}
		return false;
	}
	public boolean isExistType(String snum,String type) {
		try {
			PreparedStatement statement = (PreparedStatement)this.connection.prepareStatement(selectType_sql);
			statement.setString(1, snum);
			statement.setString(2, type);
			ResultSet set =  statement.executeQuery();
			if (!set.wasNull()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 
	 * 
	 */
	
	public JSONObject getTypeModel(String snum) {
		JSONObject jsonObject = null;
		ResultSet set = this.selectPersonalType(snum);
		if (set != null) {
			try {
				int i = 0;
				jsonObject = new JSONObject();
				while(set.next()){
					jsonObject.put((++i)+"", set.getString("tname"));
				}
				System.out.println(jsonObject);
				if (jsonObject != null) {
					return jsonObject;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public ResultSet  selectPersonalType(String snum){
		ResultSet set = null;
		try {
			System.out.println(snum);
			PreparedStatement statement = (PreparedStatement)this.connection.prepareStatement(selectType_sql);
			statement.setString(1, snum);
			
			set = statement.executeQuery();
			System.out.println(statement.toString());
			if (!set.wasNull()) {
				return set;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
//	public static void main(String[] args) {
//		EasyPassDBController eController = EasyPassDBController.getInstance();
//		eController.getTypeModel("20141514111");
//		
//	}
	/**
	 * 
	 * setter and getter
	 */
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	
}
