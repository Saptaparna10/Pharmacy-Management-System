package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StoreDailyTransaction
 */
@WebServlet("/storeDailyTransaction.do")
public class StoreDailyTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreDailyTransaction() {
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
		int price=0;
		String usr_id=request.getParameter("cust");
		String uprod_id=request.getParameter("uprod_id");
		String date=request.getParameter("date");
		String amt=request.getParameter("amt");
		String sql="select uprod_price from user_product where uprod_id='"+uprod_id+"'";
		String sql1="insert into user_daily_tran values(?,?,?,?,?)";
		
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				price=rs.getInt("uprod_price");
			}
			price = Integer.valueOf(amt)*price;
			PreparedStatement ps=con.prepareStatement(sql1);
			ps.setString(1, usr_id);
			ps.setInt(2, Integer.valueOf(uprod_id));
			ps.setString(3, date);
			ps.setInt(4, Integer.valueOf(amt));
			ps.setInt(5, price);
			
			int x = ps.executeUpdate();
			if(x==1){
				System.out.println("inserted");
				String msg="Details stored successfully";
				request.setAttribute("logname", msg);
				RequestDispatcher rd=request.getRequestDispatcher("/customerHome.jsp");
				rd.forward(request, response);
			}
			else
				System.out.println("Failed");
			
			st.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
