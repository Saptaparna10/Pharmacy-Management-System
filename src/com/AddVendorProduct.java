package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddVendorProduct
 */
@WebServlet("/addVendorProduct.do")
public class AddVendorProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVendorProduct() {
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
		PrintWriter out=response.getWriter();
		String vprod_id=request.getParameter("vprod_id");
		String vprod_nm=request.getParameter("vprod_nm");
		String vprod_ctgry=request.getParameter("vprod_ctgry");
		String vprod_desc=request.getParameter("vprod_desc");
		String vprod_price=request.getParameter("vprod_price");
		String vprod_amt=request.getParameter("vprod_amt");
		
		HttpSession hs=request.getSession();
		String vndr_id=(String) hs.getAttribute("logname");
		
		String sql="insert into vendor_product(vnd_id,vprod_id,vprod_nm,vprod_ctgry,vprod_desc,vprod_price,vprod_amt)values(?,?,?,?,?,?,?)";
		try {
			Connection con=DataConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
		
			ps.setString(1, vndr_id);
			ps.setString(2,vprod_id);
			ps.setString(3,vprod_nm);
			ps.setString(4, vprod_ctgry);
			ps.setString(5, vprod_desc);
			ps.setInt(6,Integer.valueOf(vprod_price));
			ps.setInt(7,Integer.valueOf(vprod_amt));
			
			ps.executeUpdate();
			ps.close();
			con.close();
			System.out.println("Product added");
			hs.setAttribute("vprod_id", vprod_id);
			RequestDispatcher rd=request.getRequestDispatcher("testUploadVendor.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
