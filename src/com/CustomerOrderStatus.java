package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerOrderStatus
 */
@WebServlet("/customerOrderStatus.do")
public class CustomerOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerOrderStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String corder_id=request.getParameter("corder_id");
		System.out.println("eta holo::"+ corder_id);
		PrintWriter out=response.getWriter();
		String message="Invalid order_id";
		
		try{
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from user_order where uord_id='"+corder_id+"'");
			System.out.println("Query executed");
			while(rs.next()){
				String cust_id=rs.getString("usr_id");
				String cord_dt=rs.getString("uord_dt");
				int cbill_amt=rs.getInt("ubill_amt");
				String cord_stat=rs.getString("uord_stat");
				String cord_date=cord_dt.substring(0, 9);
				
				CustomerOrder co=new CustomerOrder();
				co.setUord_id(corder_id);
				co.setUsr_id(cust_id);
				co.setUord_dt(cord_dt);
				co.setUbill_amt(cbill_amt);
				
				if(cord_stat.equals("0")){
					co.setUord_stat("Pending");
				}
				else if(cord_stat.equals("1")){
					co.setUord_stat("Shipped");
				}
				else if(cord_stat.equals("2")){
					co.setUord_stat("Rejected");
				}
				System.out.println("Somak");
				request.setAttribute("customerOrder", co);
				RequestDispatcher rd = request.getRequestDispatcher("customerOrderStatus.jsp");
				rd.forward(request, response);
				
			}
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("checkCustomerOrderStatus.jsp");
			rd.forward(request, response);
			
		}catch(Exception e){
			out.println("SQL error!!");
		}

	}

}
