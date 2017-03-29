package com.easypass.GlobalInterface;

import java.sql.Connection;
import java.sql.ResultSet;

public interface EPSDBOperate {
	static Connection connection  = null;
	
	public void connectionDB();
	/**
	 * 
	 * @return boolean ,the true value tell us as the delete operate
	 * is success ,or return false
	 */
	public boolean delete();
	/**
	 * 
	 * @return int value,the  value tell us the number of the insert data
	 * ,or return 0 
	 */
	public int insert();
	/**
	 * @param table 
	 * @return ResultSet 
	 */
	public ResultSet select(String sql);
	/**
	 * 
	 * @return boolean ,the true value tell us as the find operate
	 * is success ,or return false
	 */
	public boolean update(String sql);
	
}
