package com.amdocs.cms;

import java.util.Scanner;



public class CMSImpl {
	private static int current_user=-1;
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner sc=new Scanner(System.in);
		//System.out.println(JDBCConnection.getConnection());
		boolean isterminated=false;
		while(!isterminated) {
			if(current_user==-1) {
				System.out.println("\n1.Login\n2.Register\n3.Exit\n");
				int n=sc.nextInt();
				switch (n) {
				case 1:
					System.out.println("Enter Email: ");
					String email=sc.next();
					System.out.println("Enter Password: ");
					String pass=sc.next();
					int retrun_id=CustomerUtility.loginCustomer(email, pass);
					if(retrun_id!=-1) {
						System.out.println("Login successfully");
						current_user=retrun_id;
					}
					else {
						System.out.println("Invalid UserName or Password");
					}
					break;
				case 2:
					
					System.out.println("Enter Name: ");
					String name=sc.next();
					System.out.println("Enter Email: ");
					String reg_email=sc.next();
					System.out.println("Enter Password: ");
					String reg_pass=sc.next();
					String role=null;
					System.out.println("1.Register As an Admin \n2.Register as a customer");
					int key=sc.nextInt();
					switch (key) {
					case 1:
						role="admin";
						break;
					case 2:
						role="customer";
						break;
					default:
						System.out.println("Invalid Choice");
						break;
					}
					current_user=CustomerUtility.registerCustomer(name,reg_email,reg_pass,role);
					break;
				case 3:
					isterminated=true;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
				}
			}
			else {
				System.out.println("\n1.See Your Profile\n2.Place an Order \n"
						+ "3.See Your Order \n4.Cancel Your Order \n"
						+ "5.See List Of Product Available\n6.Add a Product\n"
						+ "7.See Order of other Customer\n8.Change role\n"
						+ "9.See All Customer\n10.Log OUT\n");
				int m=sc.nextInt();
				switch (m) {
				case 1:
					CustomerUtility.seeProfile(current_user);
					break;
				case 2:
					System.out.println("Enter Product id");
					int product_id=sc.nextInt();
					System.out.println("Enter quantity");
					int quantity=sc.nextInt();
					CustomerUtility.orderProduct(current_user,product_id,quantity);
					break;
				case 3:
					OrderItemUtility.seeOrder(current_user);
					break;
				case 4:
					System.out.println("Enter Order Id to be cancelled");
					int order_id=sc.nextInt();
					OrderItemUtility.cancelOrder(order_id, current_user);
					break;
				case 5:
					ProductUtility.showProduct();
					break;
				case 6:
					if(!CustomerUtility.authenticateAdmin(current_user)) {
						System.out.println("Oops , Only Admin can add product");
					}
					else {
						System.out.println("Enter Product Name");
						String prod_name = sc.next();
						System.out.println("Enter Product Quantity");
						int prod_quantity=sc.nextInt();
						ProductUtility.addProduct(prod_name, prod_quantity);
					}
					break;
				case 7:
					if(!CustomerUtility.authenticateAdmin(current_user)) {
						System.out.println("Oops , Only Admin can see other customers order");
					}
					else {
						System.out.println("Enter id of customer to see his/her order");
						int cust_id=sc.nextInt();
						OrderItemUtility.seeOrder(cust_id);
					}
					break;
				case 8:
					if(!CustomerUtility.authenticateAdmin(current_user)) {
						System.out.println("Oops , Only admin can change the role");
					}
					else {
						System.out.println("Enter Customer id to change the role");
						int cust_id=sc.nextInt();
						CustomerUtility.changeRole(cust_id);
					}
					break;
				case 9:
					if(CustomerUtility.authenticateAdmin(current_user)) {
						CustomerUtility.seeAllCustomer();
					}
					else {
						System.out.println("Only Admin can See All customers list");
					}
					break;
				case 10:
					current_user=-1;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
				}
			}
		}
		System.out.println("Program Terminated");
		sc.close();
	}
}
