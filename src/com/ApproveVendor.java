package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ApproveVendor
 */
@WebServlet("/approveVendor.do")
public class ApproveVendor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveVendor() {
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
		try {
			String sql="insert into current_vendor(vnd_id,vnd_pwd,vnd_nm,vnd_add,vnd_phn,vnd_mail)values(?,?,?,?,?,?)";
			String sql1="select * from vendor_master where vnd_id='"+id+"'";
			String sql3="update vendor_master set vnd_stat=1 where vnd_id='"+id+"'";
			Connection con = DataConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			PreparedStatement ps1=con.prepareStatement(sql3);
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql1);
			while(rs.next()){
				String vnm=rs.getString("vnd_nm");
				String vpwd=rs.getString("vnd_pwd");
				String vadd=rs.getString("vnd_add");
				int vphn=rs.getInt("vnd_phn");
				String vmail=rs.getString("vnd_mail");
				
				ps.setString(1,id);
				ps.setString(2,vpwd);
				ps.setString(3,vnm);
				ps.setString(4,vadd);
				ps.setInt(5,vphn);
				ps.setString(6,vmail);
			}
			ps.executeUpdate();
			ps1.executeUpdate();
			
			ps.close();
			ps1.close();
			st.close();
			con.close();
			response.sendRedirect("viewVendor.do");
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
