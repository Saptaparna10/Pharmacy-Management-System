package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerPayCard
 */
@WebServlet("/customerPayCard.do")
public class CustomerPayCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerPayCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession();
		String cust_id = (String) hs.getAttribute("logname");
		String cordid = null;
		int money = 0;
		System.out.println(cust_id);
		Connection con;
		String sql = "select * from user_curr_tran_det where usr_id = '"+cust_id+"'";
		String sql1 = "delete from user_curr_tran_det";
		try{
			con = DataConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			
			cordid = rs.getString("uord_id");
			money = rs.getInt("money");
			System.out.println("Eta holo "+cordid);
			System.out.println("Taka holo "+money);
			}
			
			
			System.out.println("Latest ta pelum");
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.executeUpdate();
			System.out.println("Current table faka");
			ps.close();
			ps1.close();
			System.out.println("Order id ache: "+cordid);
			request.setAttribute("cord_id",cordid);
			System.out.println("Taka ache: "+money);
			request.setAttribute("money",money);
			RequestDispatcher rd=request.getRequestDispatcher("customerPayment.jsp");
			rd.forward(request, response);
			
		}
		catch(Exception e){
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
