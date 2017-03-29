package com.easypass.Controller;

import com.easypass.Modal.RecordModel;



public class PassController extends Controller {
	
	boolean issuccess = false;
	private RecordModel record = null;
	public PassController() {
	
	}
	public PassController(RecordModel recordModel) {
		this.record = recordModel;
	}
	
	public boolean pass(){
		EasyPassDBController eController = EasyPassDBController.getInstance();
		boolean issuccess = eController.addRecord(this.record);
		
		return issuccess;
	}
	public boolean pass(boolean isother){
		EasyPassDBController eController = EasyPassDBController.getInstance();
		if (isother) {
			eController.insertType(this.record.getStudentModel().getSnum(), this.record.getType());
		}
		
		return pass();
	}
	public RecordModel getLastRecord(String snum ,String type) {
		RecordModel recordModel = null;
		EasyPassDBController easyPassDBController = EasyPassDBController.getInstance(); 
		recordModel =  easyPassDBController.getLastRecord(snum, type);
		
		return recordModel;
	}
	
}
