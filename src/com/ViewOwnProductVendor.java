package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class ViewOwnProductVendor
 */
@WebServlet("/viewOwnProductVendor.do")
public class ViewOwnProductVendor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOwnProductVendor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			HttpSession hs = request.getSession();
			String vndr_id = (String) hs.getAttribute("logname");
			System.out.println("Ami "+ vndr_id);
			ResultSet rs1=st.executeQuery("select * from vendor_product where vnd_id = '"+vndr_id+"' order by vprod_amt desc");
			List<VendorProduct> vendProducts = new ArrayList<VendorProduct>();
			while(rs1.next()){
					String vprod_id=rs1.getString("vprod_id");
					String vprod_nm=rs1.getString("vprod_nm");
					String vprod_ctgry=rs1.getString("vprod_ctgry");
					String vprod_desc=rs1.getString("vprod_desc");
					int vprod_amt=rs1.getInt("vprod_amt");
					int vprod_price=rs1.getInt("vprod_price");
					System.out.println("Hii " +vprod_id+ "name"+vprod_nm);
					
					VendorProduct vp=new VendorProduct();
					
					vp.setVprod_id(vprod_id);
					
					vp.setVprod_nm(vprod_nm);
					vp.setVprod_ctgry(vprod_ctgry);
					vp.setVprod_desc(vprod_desc);
					vp.setVprod_amt(vprod_amt);
					vp.setVprod_price(vprod_price);
					
					vendProducts.add(vp);
					
				}
			rs1.close();
			con.close();
			request.setAttribute("vendorProduct", vendProducts);
			RequestDispatcher view1=request.getRequestDispatcher("vendorOwnProduct.jsp");
			view1.forward(request, response);
	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println("sql error!!");
		}	

	}

}
