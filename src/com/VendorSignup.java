package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class VendorSignup
 */
@WebServlet("/vendorSignup.do")
public class VendorSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorSignup() {
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
		PrintWriter out=response.getWriter();
		String vid = request.getParameter("vend");
        String pass = request.getParameter("vpass");
        String Name = request.getParameter("vname");
        String add = request.getParameter("vaddress");
        String Phno = request.getParameter("vphno");
        String repass = request.getParameter("vrepass");
        String email = request.getParameter("vemail");
        String vdesc = request.getParameter("vdesc");
     
       
        String retid = null;
        int flag = 0;
        System.out.println(vid+""+Name+""+add+""+Phno);
                try {
                    Connection conn= DataConnection.getConnection();
                    PreparedStatement pstm = conn.prepareStatement("select vnd_id from vendor_master");
                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {
                        retid = rs.getString(1);
                        if (vid.equals(retid)) {
                            flag = 1;
                            System.out.println(retid);
                            System.out.println("vendor already exists");
                        }
                    }
                    if (flag == 0) {
                    	System.out.println("here2");
                    	int vnd_stat=0;
                    	PreparedStatement pstm1;
                        pstm1 = conn.prepareStatement("insert into vendor_master(vnd_id,vnd_pwd,vnd_nm,vnd_add,vnd_phn,vnd_mail,vnd_desc,vnd_stat) values(?,?,?,?,?,?,?,?)");
                        pstm1.setString(1, vid);
                        pstm1.setString(2, pass);
                        pstm1.setString(3, Name);
                        pstm1.setString(4, add);
                        pstm1.setInt(5, Integer.valueOf(Phno));
                        pstm1.setString(6, email);
                        pstm1.setString(7, vdesc);
                        pstm1.setInt(8, vnd_stat);
                        int i=pstm1.executeUpdate();
                        System.out.println("Succesfully account created."+i);
                        String s="Account created..login to continue";
                        HttpSession session=request.getSession();
                        session.setAttribute("logname",s);
                        RequestDispatcher rd = request.getRequestDispatcher("vendorLogin.jsp");
                	    rd.forward(request, response);
                	    pstm.close();
                	    pstm1.close();
                	    conn.close();
                    }

                    	
                } catch (Exception e) {
                }
            }
	}


