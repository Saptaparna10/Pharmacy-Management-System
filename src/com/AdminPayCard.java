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
 * Servlet implementation class AdminPayCard
 */
@WebServlet("/adminPayCard.do")
public class AdminPayCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPayCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession();
		String admin_id = (String) hs.getAttribute("logname");
		String ordid = null;
		int money = 0;
		System.out.println(admin_id);
		Connection con;
		String sql = "select * from admin_curr_tran_det where admin_id = '"+admin_id+"'";
		String sql1 = "delete from admin_curr_tran_det";
		try{
			con = DataConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			
			ordid = rs.getString("aord_id");
			money = rs.getInt("money");
			System.out.println("Eta holo "+ordid);
			System.out.println("Taka holo "+money);
			}
			
			
			System.out.println("Latest ta pelum");
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.executeUpdate();
			System.out.println("Current table faka");
			ps.close();
			ps1.close();
			System.out.println("Order id ache: "+ordid);
			request.setAttribute("ord_id",ordid);
			System.out.println("Taka ache: "+money);
			request.setAttribute("money",money);
			RequestDispatcher rd=request.getRequestDispatcher("adminPayment.jsp");
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
