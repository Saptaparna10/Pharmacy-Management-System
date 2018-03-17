package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RejectAdminOrder
 */
@WebServlet("/rejectAdminOrder.do")
public class RejectAdminOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RejectAdminOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String id=request.getParameter("order_id");
		//out.println(id);
		try {
			String sql3="update admin_order set aord_stat=2 where aord_id='"+id+"'";
			Connection con=DataConnection.getConnection();
			PreparedStatement ps1=con.prepareStatement(sql3);
			ps1.executeUpdate();

			ps1.close();
			con.close();
			response.sendRedirect("viewAdminOrder.do");
		} catch (SQLException e) {
			out.println("sql error!!");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
