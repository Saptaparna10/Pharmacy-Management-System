package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ApproveAdminOrder
 */
@WebServlet("/approveAdminOrder.do")
public class ApproveAdminOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveAdminOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession();
		String vendor_id=(String) hs.getAttribute("logname");
		System.out.println("Ami holam "+vendor_id+ " vendor");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("order_id");
		System.out.println("Order id pelo: " +id);
		int bill = 0;
		String admin_id = null;
		int final_blnc = 0;
		int blnc = 0;
		String payment = "Cash";
		String pay = null;
		try {
			String sql="insert into admin_product(admin_id,prod_id,prod_nm,prod_ctgry,prod_desc,prod_price,prod_amt,vnd_nm)values(?,?,?,?,?,?,?,?)";
			String sql1="select ao.aord_id,ao.admin_id,cv.vnd_nm,vp.vprod_id,vp.vprod_nm,vp.vprod_ctgry,vp.vprod_desc,vp.vprod_price,aop.aord_amt from admin_order ao,admin_order_prod aop,vendor_product vp,current_vendor cv where ao.aord_id=aop.aord_id and aop.vnd_id=vp.vnd_id and aop.vprod_id=vp.vprod_id and vp.vnd_id=cv.vnd_id and ao.aord_id='"+id+"'";
			String sql2 = "update bank_abc set blnc = ? where id = ?";
			String sql3="update admin_order set aord_stat='1' where aord_id='"+id+"'";
			String sql4 = "select abill_amt from admin_order where aord_id = '"+id+"'";
			String sql5 = "select blnc from bank_abc where id = ?";
			String sql6 = "select blnc from bank_abc where id = '"+vendor_id+"'";
			String sql7 ="select crd_no from admin_tran_det where aord_id = ?";
			Connection con=DataConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			PreparedStatement ps1=con.prepareStatement(sql3);
			PreparedStatement ps2 = con.prepareStatement(sql4);
			PreparedStatement ps3 = con.prepareStatement(sql5);
			PreparedStatement ps4 = con.prepareStatement(sql2);
			PreparedStatement ps5 = con.prepareStatement(sql6);
			PreparedStatement ps6 = con.prepareStatement(sql7);
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql1);
			ResultSet rs1 = ps2.executeQuery();
			while(rs1.next()){
				bill = rs1.getInt("abill_amt");
			}
			System.out.println("Bill holo: "+bill);
			while(rs.next()){
				String vnd_nm=rs.getString("vnd_nm");
				admin_id=rs.getString("admin_id");
				String prod_id=rs.getString("vprod_id");
				String prod_nm=rs.getString("vprod_nm");
				String prod_ctgry=rs.getString("vprod_ctgry");
				String prod_desc=rs.getString("vprod_desc");
				int prod_price=rs.getInt("vprod_price");
				int prod_amt=rs.getInt("aord_amt");
				
				//out.println(admin_id+" "+" "+prod_id+" "+prod_nm+" "+prod_ctgry+" "+prod_desc+" "+prod_price+" "+prod_amt);
				
				ps.setString(1,admin_id);
				ps.setString(2,prod_id);
				ps.setString(3,prod_nm);
				ps.setString(4,prod_ctgry);
				ps.setString(5,prod_desc);
				ps.setInt(6,prod_price);
				ps.setInt(7,prod_amt);
				ps.setString(8, vnd_nm);
				//ps3.setString(1, admin_id);
				ps.executeUpdate();
				ps1.executeUpdate();
				//ps3.executeQuery();
			}
			ps3.setString(1, admin_id);
			ResultSet rs2 = ps3.executeQuery();
			while(rs2.next()){
				blnc = rs2.getInt("blnc");
			}
			System.out.println("Admin er blnc ache: "+blnc);
			ps6.setString(1,id);
			ResultSet rss = ps6.executeQuery();
			while(rss.next()){
				pay = rss.getString("crd_no");
			}
			if(pay.equals(payment)){
				System.out.println("Pay korbo "+pay+"e");
				response.sendRedirect("viewAdminOrder.do");
			}
			else{
			final_blnc = blnc-bill;
			ps4.setInt(1, final_blnc);
			ps4.setString(2, admin_id);
			ps4.executeUpdate();
			System.out.println("Admin er current Balance holo: "+final_blnc);
			//ps5.setString(1, vendor_id);
			ResultSet rs3 = ps5.executeQuery();
			while(rs3.next()){
				blnc = rs3.getInt("blnc");
			}
			System.out.println("Vendor er blnc ache: "+blnc);
			final_blnc = blnc+bill;
			ps4.setInt(1, final_blnc);
			ps4.setString(2, vendor_id);
			ps4.executeUpdate();
			System.out.println("Vendor er current Balance holo: "+final_blnc);
			ps.close();
			ps1.close();
			st.close();
			con.close();
			response.sendRedirect("viewAdminOrder.do");
			}
		} catch (Exception e) {
			out.println("sql error!!");
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
