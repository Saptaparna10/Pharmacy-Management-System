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
 * Servlet implementation class CustomerLogin
 */
@WebServlet("/customerLogin.do")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cust");
        String cpass = request.getParameter("cpass");
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String retid = null;
        String retpass = null;
        ServletContext context = getServletContext();
        int flag = 0;
        try {
           System.out.println("loaded");
             conn = DataConnection.getConnection();
             System.out.println("Connected");
            pstm = conn.prepareStatement("select usr_id,usr_pwd from users");
            rs = pstm.executeQuery();
            while (rs.next()) {
                retid = rs.getString(1);
                retpass = rs.getString(2);

                if (cid.equals(retid) && cpass.equals(retpass)) {
                    System.out.println(retid);
                    System.out.println(retpass);
                    flag = 1;
                   

                }
            }

            if (flag == 0 ) {
            	
            	cid="Invalid username or password";
				request.setAttribute("logname",cid);
				RequestDispatcher rd=request.getRequestDispatcher("customerLogin.jsp");
				rd.forward(request, response);
            }
        } catch (Exception e) {
        }
        if (flag==1){
        	System.out.println("Welcome"+cid);
        String customer = request.getParameter("cust");
        HttpSession hs = request.getSession();
        hs.setAttribute("logname", cid);
        RequestDispatcher rd = context.getRequestDispatcher("/customerHome.jsp");
	    rd.forward(request, response);
       
        } 
	}

}
