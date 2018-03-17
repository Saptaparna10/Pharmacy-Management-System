package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminOrderStatusDetails
 */
@WebServlet("/adminOrderStatusDetails.do")
public class AdminOrderStatusDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrderStatusDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String ord_id=request.getParameter("ord_id");
		//out.print(ord_id);
		try{
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from admin_order_prod a,vendor_product v,admin_order ao,current_vendor c,vendor_master vm where ao.aord_id=a.aord_id and a.vnd_id=v.vnd_id and a.vprod_id=v.vprod_id and c.vnd_id=vm.vnd_id and c.vnd_id=v.vnd_id and a.aord_id='"+ord_id+"'");
			List<AdminOrderDetails> o=new ArrayList<AdminOrderDetails>();
		//	out.println("here1");
			while(rs.next()){
				String vnd_nm=rs.getString("vnd_nm");
				String vprod_nm=rs.getString("vprod_nm");
				String vprod_ctgry=rs.getString("vprod_ctgry");
				int vprod_price=rs.getInt("vprod_price");
				int aord_amt=rs.getInt("aord_amt");
				String aord_dt1=rs.getString("aord_dt");
				String aord_dt=aord_dt1.substring(0, 9);
				int abill_amt=rs.getInt("abill_amt");
				
				AdminOrderDetails aod=new AdminOrderDetails();
				aod.setVnd_nm(vnd_nm);
				aod.setVprod_nm(vprod_nm);
				aod.setVprod_ctgry(vprod_ctgry);
				aod.setVprod_price(vprod_price);
				aod.setAord_amt(aord_amt);
				aod.setAord_dt(aord_dt);
				aod.setAbill_amt(abill_amt);
				
				o.add(aod);
				request.setAttribute("abill_amt", abill_amt);
			//	out.println("here2");
			}
			request.setAttribute("aod",o);
			
		//	out.println("here3");
			
			RequestDispatcher rd=request.getRequestDispatcher("adminOrderStatusDetails.jsp");
			rd.forward(request, response);
		}catch(Exception e){
			out.println("SQL error!!");
		}
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
}
}
