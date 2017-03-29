package com.easypass.Modal;

import java.sql.Connection;

import com.easypass.Controller.EasyPassDBController;

public class Modal {
	private Connection connection = null;
	
	public Modal() {
		if (connection == null) {
			this.connection = EasyPassDBController.getInstance().getConnection();
		}
	}
	public Connection getConnection() {
		return connection;
	}
}
