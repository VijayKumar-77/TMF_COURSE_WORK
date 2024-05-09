//package DownloadServlets;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletOutputStream;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@WebServlet("/download")
//public class IndexServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//   
//    public IndexServlet() {
//        super();       
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		  String fileName = request.getParameter("file");
//	        File file = new File(getServletContext().getRealPath("/") + "/WEB-INF/Resources/" + fileName + ".txt");
//	        response.setContentType("application/octet-stream");
//	        response.setHeader("Content-Disposition", "attachment; filename =\"" + file.getName() + "\"");
//	        FileInputStream fileInputStream = new FileInputStream(file);
//	        ServletOutputStream outputStream = response.getOutputStream();
//	        byte[] buffer = new byte[4096];
//	        int bytesRead;
//	        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
//	            outputStream.write(buffer, 0, bytesRead);
//	        }
//	        fileInputStream.close();
//	        outputStream.close();
//	}
//
//}
package DownloadServlets;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Download")
public class IndexServlet extends HttpServlet {
	public IndexServlet() {
        super();
       
    }
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        File file = new File(getServletContext().getRealPath("/") + "E:\\DownloadFiles\\src\\main\\webapp\\File1.txt" + fileName + ".txt");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        FileInputStream fileInputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        fileInputStream.close();
        outputStream.close();
    }
}

