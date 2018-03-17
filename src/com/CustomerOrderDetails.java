package com;

public class CustomerOrderDetails {
	
	private int uprod_id;
	private int uord_amt;
	private String uord_dt;
	private int ubill_amt;
	private String prod_ctgry;
	private int prod_price;
	public int getUprod_id() {
		return uprod_id;
	}
	public void setUprod_id(int uprod_id) {
		this.uprod_id = uprod_id;
	}
	public int getUord_amt() {
		return uord_amt;
	}
	public void setUord_amt(int uord_amt) {
		this.uord_amt = uord_amt;
	}
	public String getUord_dt() {
		return uord_dt;
	}
	public void setUord_dt(String uord_dt) {
		this.uord_dt = uord_dt;
	}
	public int getUbill_amt() {
		return ubill_amt;
	}
	public void setUbill_amt(int ubill_amt) {
		this.ubill_amt = ubill_amt;
	}
	public String getProd_ctgry() {
		return prod_ctgry;
	}
	public void setProd_ctgry(String prod_ctgry) {
		this.prod_ctgry = prod_ctgry;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	

}
