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
 * Servlet implementation class ViewOwnAdminOrders
 */
@WebServlet("/viewOwnAdminOrders.do")
public class ViewOwnAdminOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOwnAdminOrders() {
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
		
		List<AdminOrder> ao=new ArrayList<AdminOrder>();
		String sql="select * from admin_order where admin_id='"+id+"'";
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				String aord_id=rs.getString("aord_id");
				String aord_dt=rs.getString("aord_dt");
				int abill_amt=rs.getInt("abill_amt");
				
				AdminOrder adod=new AdminOrder();
				adod.setAord_id(aord_id);
				adod.setAord_dt(aord_dt);
				adod.setAbill_amt(abill_amt);
				
				ao.add(adod);
			}
			
			request.setAttribute("adminOrder", ao);
			RequestDispatcher rd=request.getRequestDispatcher("viewOwnAdminOrders.jsp");
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
