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
 * Servlet implementation class AdminOrderStatus
 */
@WebServlet("/adminOrderStatus.do")
public class AdminOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrderStatus() {
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
		String order_id=request.getParameter("order_id");
		PrintWriter out=response.getWriter();
		String message="Invalid order_id";
		
		try{
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from admin_order where aord_id='"+order_id+"'");
			while(rs.next()){
				String admin_id=rs.getString("admin_id");
				String aord_dt=rs.getString("aord_dt");
				int abill_amt=rs.getInt("abill_amt");
				String aord_stat=rs.getString("aord_stat");
				String aord_date=aord_dt.substring(0, 9);
				
				AdminOrder ao=new AdminOrder();
				ao.setAord_id(order_id);
				ao.setAdmin_id(admin_id);
				ao.setAord_dt(aord_date);
				ao.setAbill_amt(abill_amt);
				
				if(aord_stat.equals("0")){
					ao.setAord_stat("Pending");
				}
				else if(aord_stat.equals("1")){
					ao.setAord_stat("Shipped");
				}
				else if(aord_stat.equals("2")){
					ao.setAord_stat("Rejected");
				}
				
				request.setAttribute("adminOrder", ao);
				RequestDispatcher rd = request.getRequestDispatcher("adminOrderStatus.jsp");
				rd.forward(request, response);
				
			}
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("checkAdminOrderStatus.jsp");
			rd.forward(request, response);
			
		}catch(Exception e){
			out.println("SQL error!!");
		}
	}

}
