package com;

public class UserTransaction {
	private String usr_id;
	private int uprod_id;
	private String tran_date;
	private int uprod_amt;
	private int uprod_price;
	private String uprod_nm;

	
	public UserTransaction(String usr_id, int uprod_id, String tran_date,
			int uprod_amt, int uprod_price, String uprod_nm) {
		super();
		this.usr_id = usr_id;
		this.uprod_id = uprod_id;
		this.tran_date = tran_date;
		this.uprod_amt = uprod_amt;
		this.uprod_price = uprod_price;
		this.uprod_nm = uprod_nm;
	}
	public String getUprod_nm() {
		return uprod_nm;
	}
	public void setUprod_nm(String uprod_nm) {
		this.uprod_nm = uprod_nm;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public int getUprod_id() {
		return uprod_id;
	}
	public void setUprod_id(int uprod_id) {
		this.uprod_id = uprod_id;
	}
	public String getTran_date() {
		return tran_date;
	}
	public void setTran_date(String tran_date) {
		this.tran_date = tran_date;
	}
	public int getUprod_amt() {
		return uprod_amt;
	}
	public void setUprod_amt(int uprod_amt) {
		this.uprod_amt = uprod_amt;
	}
	public int getUprod_price() {
		return uprod_price;
	}
	public void setUprod_price(int uprod_price) {
		this.uprod_price = uprod_price;
	}
}
