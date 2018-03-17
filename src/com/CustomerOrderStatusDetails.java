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
 * Servlet implementation class CustomerOrderStatusDetails
 */
@WebServlet("/customerOrderStatusDetails.do")
public class CustomerOrderStatusDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerOrderStatusDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String cord_id=request.getParameter("cord_id");
		System.out.print("Pelo :" +cord_id);
		try{
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from user_order_prod u,admin_product_sale a,user_order uo where a.prod_id = u.uprod_id and uo.uord_id = u.uord_id and uo.uord_id='"+cord_id+"'");
			List<CustomerOrderDetails> o=new ArrayList<CustomerOrderDetails>();
			System.out.println("After Query");
			while(rs.next()){
				int uprod_id=rs.getInt("uprod_id");
				System.out.println("prod_id holo: "+uprod_id);
				//String vprod_nm=rs.getString("vprod_nm");
				String prod_ctgry=rs.getString("prod_ctgry");
				System.out.println("prod_ctgry holo: "+prod_ctgry);
				int prod_price=rs.getInt("prod_price");
				System.out.println("prod_price holo: "+prod_price);
				int uord_amt=rs.getInt("uord_amt");
				System.out.println("prod_amt holo: "+uord_amt);
				String uord_dt1=rs.getString("uord_dt");
				String uord_dt=uord_dt1.substring(0, 9);
				System.out.println("prod_dt holo: "+uord_dt);
				int ubill_amt=rs.getInt("ubill_amt");
				System.out.println("bill holo: "+ubill_amt);
				
				CustomerOrderDetails cod=new CustomerOrderDetails();
				cod.setUprod_id(uprod_id);
				//cod.setVprod_nm(vprod_nm);
				cod.setProd_ctgry(prod_ctgry);
				cod.setProd_price(prod_price);
				cod.setUord_amt(uord_amt);
				cod.setUord_dt(uord_dt);
				cod.setUbill_amt(ubill_amt);
				
				o.add(cod);
				request.setAttribute("cbill_amt", ubill_amt);
			//	out.println("here2");
			}
			request.setAttribute("cod",o);
			
		//	out.println("here3");
			
			RequestDispatcher rd=request.getRequestDispatcher("customerOrderStatusDetailsByCust.jsp");
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
