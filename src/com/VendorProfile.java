package com;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.xml.crypto.Data;
import com.VendorDetails;

/**
 * Servlet implementation class VendorProfile
 */
@WebServlet("/vendorProfile.do")
public class VendorProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorProfile() {
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
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs1=st.executeQuery("select * from vendor_master where vnd_id='"+id+"'");
			while(rs1.next()){
				String vnm=rs1.getString("vnd_nm");
				String vpwd=rs1.getString("vnd_pwd");
				String vadd=rs1.getString("vnd_add");
				int vphn=rs1.getInt("vnd_phn");
				String vmail=rs1.getString("vnd_mail");
				String vdesc=rs1.getString("vnd_desc");
				
				VendorDetails vd=new VendorDetails();
				vd.setVid(id);
				vd.setVpwd(vpwd);
				vd.setVnm(vnm);
				vd.setVadd(vadd);
				vd.setVphn(vphn);
				vd.setVmail(vmail);
				vd.setDesc(vdesc);
				request.setAttribute("vendor", vd);
				RequestDispatcher view=request.getRequestDispatcher("vendorProfile.jsp");
				view.forward(request, response);
			}
			st.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
