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

/**
 * Servlet implementation class ViewTransactionDetails
 */
@WebServlet("/viewTransactionDetails.do")
public class ViewTransactionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTransactionDetails() {
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
		
		int uprod_id=0;
		List<UserTransaction> ut=new ArrayList<UserTransaction>();
		String date=request.getParameter("date");
		String usr_id=request.getParameter("uid");
		System.out.println(date+" ");
		String sql="select * from user_daily_tran where tran_date='"+date+"' and usr_id='"+usr_id+"'";
		
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			System.out.println("b4 while");
			while(rs.next()){
				System.out.println("inside while");
				usr_id=rs.getString(1);
			    uprod_id=rs.getInt(2);
				String tran_date=rs.getString(3);
				int uprod_amt=rs.getInt(4);
				int uprod_price=rs.getInt(5);
				
				System.out.println("b4 name");
				Statement st1=con.createStatement();
				ResultSet rs1=st1.executeQuery("select uprod_nm from user_product where uprod_id='"+uprod_id+"'");
				rs1.next();
				String uprod_nm=rs1.getString("uprod_nm");
				
				System.out.println("after name");
				System.out.println(usr_id+" "+uprod_id+" "+tran_date+" "+uprod_amt+" "+uprod_price+" "+uprod_nm);
				UserTransaction tran=new UserTransaction(usr_id, uprod_id, tran_date, uprod_amt, uprod_price, uprod_nm);
				ut.add(tran);
				
			}
			request.setAttribute("tran", ut);
			RequestDispatcher rd=request.getRequestDispatcher("viewUserTransactionDetails.jsp");
			rd.forward(request, response);
			
			rs.close();
			con.close();
			st.close();
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
				
	}

}
