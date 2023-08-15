package com.amdocs.cms;

class OrderItems {
	private int order_id;
	private int cust_id;
	private int product_id;
	private int quantity;
	
	public OrderItems(int order_id, int cust_id, int product_id, int quantity) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.cust_id = cust_id;
		this.quantity = quantity;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%-10s%-10s%-10s%-10s",order_id,cust_id,product_id,quantity);
	}
	
}
