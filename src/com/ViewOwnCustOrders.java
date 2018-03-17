package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewOwnCustOrders
 */
@WebServlet("/viewOwnCustOrders.do")
public class ViewOwnCustOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOwnCustOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs=request.getSession();
		String id=(String) hs.getAttribute("logname");
		
		List<CustomerOrder> ao=new ArrayList<CustomerOrder>();
		String sql="select * from user_order where usr_id='"+id+"'";
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				String uord_id=rs.getString("uord_id");
				String uord_dt=rs.getString("uord_dt");
				int ubill_amt=rs.getInt("ubill_amt");
				
				System.out.println(uord_id+" "+uord_dt+" "+ubill_amt);
				
				CustomerOrder adod=new CustomerOrder();
				adod.setUord_id(uord_id);
				adod.setUord_dt(uord_dt);
				adod.setUbill_amt(ubill_amt);
				
				ao.add(adod);
			}
			
			request.setAttribute("custOrder", ao);
			RequestDispatcher rd=request.getRequestDispatcher("viewOwnCustOrders.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
