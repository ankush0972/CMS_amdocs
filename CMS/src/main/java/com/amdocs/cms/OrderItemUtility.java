package com.amdocs.cms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderItemUtility {
	public static void orderItem(int cust_id,int product_id,int quantity) {
		Connection connection=JDBCConnection.getConnection();
		String query="insert into order_items values (order_seq.nextval,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, cust_id);
			stmt.setInt(2, product_id);
			stmt.setInt(3, quantity);
			int num=stmt.executeUpdate();
			if(num>0) {
				System.out.println("Order Placed Successfully\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void seeOrder(int cust_id) {
		Connection connection=JDBCConnection.getConnection();
		String query="select * from order_items where cust_id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, cust_id);
			ResultSet rs = stmt.executeQuery();
			boolean found=false;
			while(rs.next()) {
				found=true;
				OrderItems orderItems=new OrderItems(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				System.out.println(orderItems);
			}
			if(!found) {
				System.out.println("You have not placed any Order\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	public static OrderItems findOrder(int order_id) {
		Connection connection=JDBCConnection.getConnection();
		String query = "select * from order_items where order_id=?";
		try {
			PreparedStatement stmt=connection.prepareStatement(query);
			stmt.setInt(1, order_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				OrderItems orderItems=new OrderItems(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				return orderItems;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	public static void cancelOrder(int order_id,int cust_id) {
		OrderItems orderItems=findOrder(order_id);
		if(orderItems==null) {
			System.out.println("No Order Exist with Given id");
			return ;
		}
		Connection connection=JDBCConnection.getConnection();
		String query="delete from order_items where order_id=? and cust_id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, order_id);
			stmt.setInt(2, cust_id);
			int num=stmt.executeUpdate();
			if(num==0) {
				System.out.println("You have not placed any order with id "+order_id);
			}
			else {
				System.out.println(orderItems.getProduct_id()+" "+orderItems.getQuantity());
				CustomerUtility.setquantity(orderItems.getProduct_id(), ProductUtility.getQuantity(orderItems.getProduct_id())+orderItems.getQuantity());
				System.out.println("Deleted Successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
