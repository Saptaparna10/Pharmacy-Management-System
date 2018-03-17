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
 * Servlet implementation class VendorUpdateProfile
 */
@WebServlet("/vendorUpdateProfile.do")
public class VendorUpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorUpdateProfile() {
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
		String vpass = request.getParameter("vpass");
        String vname = request.getParameter("vname");
        String vaddress = request.getParameter("vaddress");
        String vphno = request.getParameter("vphno");
        String vrepass = request.getParameter("vrepass");
        String vemail = request.getParameter("vemail");
        HttpSession hs = request.getSession();
		String vid = (String) hs.getAttribute("logname");
		System.out.println("Pelam "+vid);
        Connection conn = null;
        PreparedStatement pstm = null;
        ServletContext context = getServletContext();
        ResultSet rs = null;
        String retid = null;
        int flag = 0;
        int flag2 = 0;


           
                try {
                    conn=DataConnection.getConnection();
                    System.out.println("Driver Loaded");
                   
                     System.out.println("Driver connected");
                    pstm = conn.prepareStatement("select vnd_id,vnd_pwd from current_vendor");
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        retid = rs.getString(1);
                        if (vid.equals(retid)) {
                            flag = 1;
                            //System.out.println(retid);
                            System.out.println("vendor approved");
                            System.out.println("DB theke pelo" +retid);
                            if(vpass == "" || vrepass == ""){
                            	vpass = rs.getString(2);
                            	System.out.println("Password theke galo" + vpass);
                            }

                            pstm = conn.prepareStatement("update current_vendor set vnd_pwd=?,vnd_nm=?,vnd_add=?,vnd_phn=?,vnd_mail=? where vnd_id ='"+vid+"'");
                           //pstm.setString(1, cid);
                            pstm.setString(1, vpass);
                            pstm.setString(2, vname);
                            pstm.setString(3, vaddress);
                            pstm.setString(4, vphno);
                            pstm.setString(5, vemail);
                            pstm.executeUpdate();
                            System.out.println("Succesfully account updated in current_vendor.");
                            pstm = conn.prepareStatement("update vendor_master set vnd_pwd=?,vnd_nm=?,vnd_add=?,vnd_phn=?,vnd_mail=? where vnd_id ='"+vid+"'");
                            pstm.setString(1, vpass);
                            pstm.setString(2, vname);
                            pstm.setString(3, vaddress);
                            pstm.setString(4, vphno);
                            pstm.setString(5, vemail);
                            pstm.executeUpdate();
                            System.out.println("Succesfully account updated in vendor_master.");
                            String msg="Account details updated";
                            
                            request.setAttribute("logname", msg);
                            RequestDispatcher rd = request.getRequestDispatcher("vendorHome.jsp");
                            rd.forward(request, response);
                        }
                    }
                    if (flag == 0) {
                        
                        
                       
                        System.out.println("Succesfully account not created.");
                   
                    }


                } catch (Exception e) {
                }
            
	}

}
