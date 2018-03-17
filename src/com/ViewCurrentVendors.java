package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewCurrentVendors
 */
@WebServlet("/viewCurrentVendors.do")
public class ViewCurrentVendors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCurrentVendors() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		ArrayList<String> vendors=new ArrayList<String>();
		ArrayList<String> vendor_ids=new ArrayList<String>();
		try{
			Connection con1=DataConnection.getConnection();
			Statement st=con1.createStatement();
			ResultSet rs=st.executeQuery("select * from current_vendor");
			while(rs.next()){
				String vnm=rs.getString("vnd_nm");
				String vid=rs.getString("vnd_id");
				vendors.add(vnm);
				vendor_ids.add(vid);
			}
			request.setAttribute("vendors", vendors);
			request.setAttribute("vendor_ids", vendor_ids);
			RequestDispatcher view=request.getRequestDispatcher("viewCurrentVendors.jsp");
			view.forward(request, response);
			st.close();
			con1.close();
			
		}catch(SQLException e){
			out.println("SQL Error: "+e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
