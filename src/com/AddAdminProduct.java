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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddAdminProduct
 */
@WebServlet("/addAdminProduct.do")
public class AddAdminProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAdminProduct() {
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
		String prod_id=request.getParameter("prod_id");
		String prod_nm=request.getParameter("prod_nm");
		String prod_ctgry=request.getParameter("prod_ctgry");
		String prod_desc=request.getParameter("prod_desc");
		String prod_price=request.getParameter("prod_price");
		String prod_amt=request.getParameter("prod_amt");
		
		HttpSession hs=request.getSession();
		String admin_id=(String) hs.getAttribute("logname");
		
		String sql="insert into admin_product_sale(admin_id,prod_id,prod_nm,prod_ctgry,prod_desc,prod_price,prod_amt)values(?,?,?,?,?,?,?)";
		try {
			Connection con = DataConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
		
			ps.setString(1, admin_id);
			ps.setInt(2,Integer.valueOf(prod_id));
			ps.setString(3,prod_nm);
			ps.setString(4, prod_ctgry);
			ps.setString(5, prod_desc);
			ps.setInt(6,Integer.valueOf(prod_price));
			ps.setInt(7,Integer.valueOf(prod_amt));
			
			ps.executeUpdate();
			ps.close();
			con.close();
			//out.println("Product added");
			hs.setAttribute("prod_id", prod_id);
			RequestDispatcher rd=request.getRequestDispatcher("testUpload.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
