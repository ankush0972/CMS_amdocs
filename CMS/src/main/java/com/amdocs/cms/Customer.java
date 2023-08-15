package com.amdocs.cms;


class Customer {
	private int cust_id;
	private String cust_name;
	private String cust_email;
	private String cust_pass;
	private String cust_role;
	
	public Customer(int cust_id, String cust_name, String cust_email, String cust_pass, String cust_role) {
		super();
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.cust_email = cust_email;
		this.cust_pass = cust_pass;
		this.cust_role = cust_role;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_email() {
		return cust_email;
	}
	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}
	public String getCust_pass() {
		return cust_pass;
	}
	public void setCust_pass(String cust_pass) {
		this.cust_pass = cust_pass;
	}
	public String getCust_role() {
		return cust_role;
	}
	public void setCust_role(String cust_role) {
		this.cust_role = cust_role;
	}
	@Override
	public String toString() {
		return String.format("%-10s%-30s%-40s%-10s",cust_id,cust_name,cust_email,cust_role);
	}
}
