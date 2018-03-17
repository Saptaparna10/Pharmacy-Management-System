package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ApproveCustomerOrder
 */
@WebServlet("/approveCustomerOrder.do")
public class ApproveCustomerOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveCustomerOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession();
		String admin_id=(String) hs.getAttribute("logname");
		System.out.println("Ami holam "+admin_id+ " admin");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("order_id");
		System.out.println("Order id pelo: " +id);
		int bill = 0;
		String cust_id = null;
		int final_blnc = 0;
		int blnc = 0;
		String payment = "Cash";
		String pay = null;
		try {
			//String sql="insert into user_order_prod(admin_id,prod_id,prod_nm,prod_ctgry,prod_desc,prod_price,prod_amt,vnd_nm)values(?,?,?,?,?,?,?,?)";
			//String sql1="select usr_id from user_order where uord_id = '"+id+"'";
			
			String sql="update user_order set uord_stat='1' where uord_id='"+id+"'";
			String sql1 = "update bank_abc set blnc = ? where id = ?";
			String sql2 = "select usr_id,ubill_amt from user_order where uord_id = '"+id+"'";
			String sql3 = "select blnc from bank_abc where id = ?";
			String sql4 = "select blnc from bank_abc where id = '"+admin_id+"'";
			String sql5 ="select crd_no from user_tran_det where uord_id = ?";
			Connection con=DataConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			PreparedStatement ps1 = con.prepareStatement(sql1);
			PreparedStatement ps2 = con.prepareStatement(sql2);
			PreparedStatement ps3 = con.prepareStatement(sql3);
			PreparedStatement ps4 = con.prepareStatement(sql4);
			PreparedStatement ps5 = con.prepareStatement(sql5);
			int i= ps.executeUpdate();
			if(i==1){
				System.out.println("Update successful");
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()){
					cust_id = rs2.getString("usr_id");
					bill = rs2.getInt("ubill_amt");
				}
				System.out.println("user holo: "+cust_id);
				System.out.println("Bill holo: "+bill);
				ps5.setString(1,id);
				ResultSet rss = ps5.executeQuery();
				while(rss.next()){
					pay = rss.getString("crd_no");
					System.out.println(pay);
				}
				if(pay.equals(payment)){
					System.out.println("Pay korbo "+pay+"e");
					response.sendRedirect("viewCustomerOrder.do");
				}
				else{
				System.out.println("Card e dichchi");
				ps3.setString(1, cust_id);
				ResultSet rs3 = ps3.executeQuery();
				while(rs3.next()){
					blnc = rs3.getInt("blnc");
				}
				System.out.println("User er balance ache: "+blnc);
				final_blnc = blnc-bill;
				ps1.setInt(1, final_blnc);
				ps1.setString(2, cust_id);
				ps1.executeUpdate();
				System.out.println("Customer er current Balance holo: "+final_blnc);
				ResultSet rs4 = ps4.executeQuery();
				while(rs4.next()){
					blnc = rs4.getInt("blnc");
				}
				System.out.println("Admin er balance ache: "+blnc);
				final_blnc = blnc+bill;
				ps1.setInt(1, final_blnc);
				ps1.setString(2, admin_id);
				ps1.executeUpdate();
				System.out.println("Vendor er current Balance holo: "+final_blnc);
				}
			}else{
				System.out.println("Update failed");
			}
			ps.close();
			
			con.close();
			response.sendRedirect("viewCustomerOrder.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
