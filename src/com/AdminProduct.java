package com;

public class AdminProduct {
	
	private String prod_id;
	private String prod_nm;
	private String prod_ctgry;
	private String prod_desc;
	private int prod_price;
	private int prod_amt;
	private int ord_amt;
	private String vnd_nm;
	
	public String getVnd_nm() {
		return vnd_nm;
	}
	public void setVnd_nm(String vnd_nm) {
		this.vnd_nm = vnd_nm;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_nm() {
		return prod_nm;
	}
	public void setProd_nm(String prod_nm) {
		this.prod_nm = prod_nm;
	}
	public String getProd_ctgry() {
		return prod_ctgry;
	}
	public void setProd_ctgry(String prod_ctgry) {
		this.prod_ctgry = prod_ctgry;
	}
	public String getProd_desc() {
		return prod_desc;
	}
	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	public int getProd_amt() {
		return prod_amt;
	}
	public void setProd_amt(int prod_amt) {
		this.prod_amt = prod_amt;
	}
	public int getOrd_amt() {
		return ord_amt;
	}
	public void setOrd_amt(int ord_amt) {
		this.ord_amt = ord_amt;
	}
	

	
}
