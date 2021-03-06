package com;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


/**
 * Servlet implementation class PicUploader
 */
@WebServlet("/picUploader.do")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class PicUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private static final String SAVE_DIR = "uploadFiles";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicUploader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 // gets absolute path of the web application
        String appPath = "C:/Windows/System32/config/systemprofile/workspace/PharmacyManagementSystem/WebContent/images";  
       	//request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
         System.out.println(savePath);
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        HttpSession hs=request.getSession();
        String pid=(String) hs.getAttribute("prod_id");

         
        for (Part part : request.getParts()) {
           String fileName = extractFileName(part);
            part.write(savePath + File.separator + pid+"."+fileName);
            System.out.println(pid+fileName);
        }
 
        
    		 request.setAttribute("logname", "Upload has been done successfully!");
    	        getServletContext().getRequestDispatcher("/adminHome.jsp").forward(
    	                request, response);
    }
		
 
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
            	System.out.println(s);
              //  return s.substring(s.indexOf("=") + 2, s.length()-1);
            	return s.substring(s.lastIndexOf(".") + 1, s.length()-1);
            	
            }
        }
        return "";
    }
    
	}
	

