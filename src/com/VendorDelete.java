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
 * Servlet implementation class VendorDelete
 */
@WebServlet("/vendorDelete.do")
public class VendorDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		//out.println(id);
		try {
			String sql3="update vendor_master set vnd_stat=2 where vnd_id='"+id+"'";
			String sql="delete from current_vendor where vnd_id='"+id+"'";
			Connection con=DataConnection.getConnection();
			PreparedStatement ps1=con.prepareStatement(sql3);
			PreparedStatement ps2=con.prepareStatement(sql);
			ps1.executeUpdate();
			ps2.executeUpdate();
			

			ps1.close();
			ps2.close();
			con.close();
			response.sendRedirect("viewCurrentVendors.do");
		} catch (Exception e) {
			out.println("sql error!!");
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
