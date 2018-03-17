package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewAdminProductDetails
 */
@WebServlet("/viewAdminProductDetails.do")
public class ViewAdminProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAdminProductDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		System.out.println("I am"+id);
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from admin_product_sale where prod_id='"+id+"'");
			
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_nm=rs.getString("prod_nm");
				String prod_ctgry=rs.getString("prod_ctgry");
				String prod_desc=rs.getString("prod_desc");
				int prod_price=rs.getInt("prod_price");
				int prod_amt=rs.getInt("prod_amt");
				
				System.out.println("I am buying" +prod_nm);
				
				AdminProduct ap1=new AdminProduct();
				ap1.setProd_id(id);
				ap1.setProd_nm(prod_nm);
				ap1.setProd_ctgry(prod_ctgry);
				ap1.setProd_desc(prod_desc);
				ap1.setProd_price(prod_price);
				ap1.setProd_amt(prod_amt);
		
				
				request.setAttribute("adminProductDetails",ap1);
				request.setAttribute("prodID",id);
				RequestDispatcher rd=request.getRequestDispatcher("viewAdminProductDetails.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			
		}
		
	}
	}


