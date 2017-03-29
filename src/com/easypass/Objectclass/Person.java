package com.easypass.Objectclass;

import java.sql.Statement;
import com.easypass.GlobalInterface.CallBack;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person implements CallBack {
	User user ; 
	
	public String toString() {
		
		return "person info ...";
	}
	
	public void getFile() {
		
		System.out.println("get file!");
	} 
	@Override
	public void solve(String string) {
		System.out.println("return back person:" + string);
	}
//	public void ask(String thing) {
//		System.out.println("Person.this :"+this);
//		
//		user.execute(this, thing);
//	} 
	public static void main(String[] args) {
	
		Connection connection = null;
		String sql = "insert into student (snum,sname) values(?,?)";
		String select = "select id ,snum ,sname from student";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easypass","root","");
			Statement state = connection.createStatement();
			PreparedStatement pStatement =  connection.prepareStatement(select);
			
			
			ResultSet set = pStatement.executeQuery();
			
			int count = set.getMetaData().getColumnCount();
			while (set.next()) {
				for(int i = 1;i <= count;i++){
					
					String string = set.getString(i) + "\t";
					System.out.print(string);
					
				}
				System.out.println();
			}
			
			
			pStatement.close();
			connection.close();
			System.out.println(state);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("hah");
		/*
		Person person = new Person();
		User user = new User();
		user.setCallBack(person);
		user.execute("gogogo");
	*/	
		
		
		
//		person.ask("我是好人！");
	}
}

class Main{
	
}