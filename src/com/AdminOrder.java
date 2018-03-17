package com;

public class AdminOrder {

	private String aord_id;
	private String aord_dt;
	private int abill_amt;
	private String aord_stat;
	private String admin_id;
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAord_id() {
		return aord_id;
	}
	public void setAord_id(String aord_id) {
		this.aord_id = aord_id;
	}
	public String getAord_dt() {
		return aord_dt;
	}
	public void setAord_dt(String aord_dt) {
		this.aord_dt = aord_dt;
	}
	public int getAbill_amt() {
		return abill_amt;
	}
	public void setAbill_amt(int abill_amt) {
		this.abill_amt = abill_amt;
	}
	public String getAord_stat() {
		return aord_stat;
	}
	public void setAord_stat(String aord_stat) {
		this.aord_stat = aord_stat;
	}


}
