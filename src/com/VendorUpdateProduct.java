package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VendorUpdateProduct
 */
@WebServlet("/vendorUpdateProduct.do")
public class VendorUpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorUpdateProduct() {
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
		String vprod_nm = request.getParameter("vprod_nm");
        String vprod_ctgry = request.getParameter("vprod_ctgry");
        String vprod_desc = request.getParameter("vprod_desc");
        String vprod_price = request.getParameter("vprod_price");
        String vprod_amt = request.getParameter("vprod_amt");
        HttpSession hs = request.getSession();
		String vid = (String) hs.getAttribute("logname");
		String pid = (String) hs.getAttribute("vprodid");
		System.out.println("Pelam "+vid);
		System.out.println("Pelam "+pid);
        Connection conn = null;
        PreparedStatement pstm = null;
        ServletContext context = getServletContext();
        ResultSet rs = null;
        String retvid = null;
        String retpid = null;
        int flag = 0;
        int flag2 = 0;


           
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    System.out.println("Driver Loaded");
                    conn = DataConnection.getConnection();
                              System.out.println("Driver connected");
                    pstm = conn.prepareStatement("select vnd_id,vprod_id from vendor_product");
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        retvid = rs.getString(1);
                        retpid = rs.getString(2);
                        if (vid.equals(retvid) && pid.equalsIgnoreCase(retpid)) {
                            flag = 1;
                            //System.out.println(retid);
                            System.out.println("vendor approved");
                            System.out.println("DB theke pelo" +retvid);
                            System.out.println("DB theke pelo" +retpid);
                            
                            pstm = conn.prepareStatement("update vendor_product set vprod_nm=?,vprod_ctgry=?,vprod_desc=?,vprod_price=?,vprod_amt=? where vnd_id ='"+vid+"' and vprod_id ='"+pid+"'");
                           //pstm.setString(1, cid);
                            pstm.setString(1, vprod_nm);
                            pstm.setString(2, vprod_ctgry);
                            pstm.setString(3, vprod_desc);
                            pstm.setInt(4, Integer.valueOf(vprod_price));
                            pstm.setInt(5, Integer.valueOf(vprod_amt));
                            pstm.executeUpdate();
                            System.out.println("Succesfully product updated");
                            String msg="Succesfully product updated";
                            request.setAttribute("logname", msg);
                            RequestDispatcher rd = request.getRequestDispatcher("vendorHome.jsp");
                            rd.forward(request, response);
                        }
                    }
                    if (flag == 0) {
                        
                        
                       
                        System.out.println("Succesfully product not created.");
                   
                    }


                } catch (Exception e) {
                }

	}

}
