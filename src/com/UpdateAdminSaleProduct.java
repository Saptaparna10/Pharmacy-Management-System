package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateAdminSaleProduct
 */
@WebServlet("/updateAdminSaleProduct.do")
public class UpdateAdminSaleProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAdminSaleProduct() {
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
		String prod_id=request.getParameter("prod_id");
		String prod_nm=request.getParameter("prod_nm");
		String prod_ctgry=request.getParameter("prod_ctgry");
		String prod_desc=request.getParameter("prod_desc");
		String prod_price=request.getParameter("prod_price");
		String prod_amt=request.getParameter("prod_amt");
		
		String sql="update admin_product_sale set prod_nm=?,prod_ctgry=?,prod_desc=?,prod_price=?,prod_amt=? where prod_id='"+prod_id+"'";
		try{
			Connection con=DataConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, prod_nm);
			ps.setString(2, prod_ctgry);
			ps.setString(3,prod_desc);
			ps.setInt(4, Integer.valueOf(prod_price));
			ps.setInt(5, Integer.valueOf(prod_amt));
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			response.sendRedirect("adminProductSale.do");
			}catch(Exception e){
			e.printStackTrace();
		}
	}

}
