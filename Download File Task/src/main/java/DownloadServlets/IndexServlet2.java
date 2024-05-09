package DownloadServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/download")
public class IndexServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public IndexServlet2() {
        super();
        
    }
public int BUFFER_SIZE = 1024*1000;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter out =response.getWriter()){
			
		String name = request.getParameter("filename"); 
        String path = getServletContext().getRealPath("/" +" TXT " +File.separator + name);
        File fl = new File(path);
        if (fl.exists()) {
        	response.setContentType("application/octet-stream");
        	response.setContentLength ((int)fl.length());
        	response.setHeader("Content-Disposition", "attachment; filename=\"%s\"" + fl.getName() + "\"");
        	FileInputStream in = new FileInputStream(fl);
        	int i;
        	while((i=in.read())!= -1){
        		System.out.println(i);
        	}
        in.close();
        out.close();
        }
        else {
        	System.out.println("File Not Found");
        	
        }
	  }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	    
	}

}
