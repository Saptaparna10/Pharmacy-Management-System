package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewVendorProductDetails
 */
@WebServlet("/viewVendorProductDetails.do")
public class ViewVendorProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewVendorProductDetails() {
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
			ResultSet rs=st.executeQuery("select * from vendor_product where vprod_id='"+id+"' and vprod_amt>0");
			
			
			while(rs.next()){
				System.out.println("inside while");
				
				String vnd_id=rs.getString("vnd_id");
				String vprod_nm=rs.getString("vprod_nm");
				String vprod_ctgry=rs.getString("vprod_ctgry");
				String vprod_desc=rs.getString("vprod_desc");
				int vprod_price=rs.getInt("vprod_price");
				int vprod_amt=rs.getInt("vprod_amt");
				
				System.out.println("inside while2");
				System.out.println(vprod_nm);
				
				VendorProduct vp1=new VendorProduct();
				vp1.setVprod_id(id);
				vp1.setVnd_id(vnd_id);
				vp1.setVprod_nm(vprod_nm);
				vp1.setVprod_ctgry(vprod_ctgry);
				vp1.setVprod_desc(vprod_desc);
				vp1.setVprod_price(vprod_price);
				vp1.setVprod_amt(vprod_amt);
				
				System.out.println("inside while3");
				
				request.setAttribute("vendorProductDetails",vp1);
				request.setAttribute("prodID", id);
				System.out.println("inside while4");
				RequestDispatcher rd=request.getRequestDispatcher("viewVendorProductDetails.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("oops");
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
