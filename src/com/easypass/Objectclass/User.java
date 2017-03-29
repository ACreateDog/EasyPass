package com.easypass.Objectclass;
import com.easypass.GlobalInterface.CallBack;

public class User {
	private String name = null;
	private String address = null;
	private CallBack callBack = null;
	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void execute(String thing) {
		if (this.callBack != null) {
			System.out.println("let call back:");
			callBack.solve("you must do it!");
		}
		
	}
}
