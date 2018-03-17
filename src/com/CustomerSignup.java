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
 * Servlet implementation class CustomerSignup
 */
@WebServlet("/customerSignup.do")
public class CustomerSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerSignup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pass = request.getParameter("pass");
        String Name = request.getParameter("name");
        String add = request.getParameter("address");
        String Phno = request.getParameter("phno");
        String repass = request.getParameter("repass");
        String email = request.getParameter("email");
        HttpSession hs = request.getSession();
		String cid = (String) hs.getAttribute("logname");
		System.out.println("Pelam "+cid);
        Connection conn = null;
        PreparedStatement pstm = null;
        ServletContext context = getServletContext();
        ResultSet rs = null;
        String retid = null;
        int flag = 0;
        int flag2 = 0;


            
                try {
                   
                    System.out.println("Driver Loaded");
                    conn = DataConnection.getConnection();
                              System.out.println("Driver connected");
                    pstm = conn.prepareStatement("select usr_id,usr_pwd from users");
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        retid = rs.getString(1);
                        if (cid.equals(retid)) {
                            flag = 1;
                            //System.out.println(retid);
                            System.out.println("customer already exists");
                            System.out.println("DB theke pelo" +retid);
                            if(pass == "" || repass == ""){
                            	pass = rs.getString(2);
                            	System.out.println("Password theke galo" + pass);
                            }
                            pstm = conn.prepareStatement("update users set usr_pwd=?,usr_nm=?,usr_add=?,usr_phn=?,usr_mail=? where usr_id ='"+cid+"'");
                           //pstm.setString(1, cid);
                            pstm.setString(1, pass);
                            pstm.setString(2, Name);
                            pstm.setString(3, add);
                            pstm.setString(4, Phno);
                            pstm.setString(5, email);
                            pstm.executeUpdate();
                            System.out.println("Succesfully account updated.");
                            String msg="Profile updated";
                            request.setAttribute("logname", msg);
                            RequestDispatcher rd = request.getRequestDispatcher("customerHome.jsp");
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
