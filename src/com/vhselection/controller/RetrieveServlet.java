package com.vhselection.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vhselection.dao.UrlDao;

/**
 * Servlet implementation class Retrieve
 */
@WebServlet("/Retrieve")
public class RetrieveServlet extends HttpServlet {
       
	 
    private static final long serialVersionUID = 1L;
    private UrlDao UrlDao;
    private String domain = "http://localhost:8080/NewURL/";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Initializes with the parameters stored in context
     */
    public void init() {    	
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = 
        		getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = 
        		getServletContext().getInitParameter("jdbcPassword");        
        UrlDao = new UrlDao(jdbcURL, jdbcUsername, jdbcPassword); 
    }
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
 
	    String urlId = request.getServletPath();
	
	    String longUrl = null;
	    if (urlId != null && !"".equals(urlId)) {
	        try {
	        	longUrl = UrlDao.recoveryLongUrl(urlId);
			    request.getSession().setAttribute("longUrl", longUrl);
			} catch (Exception e) {
	        // handling exception here
	        e.printStackTrace();
	        }
	    }
	 
	    if (longUrl == null) {
	        // if long url not found, send to index.jsp
	        System.out.println("long url not found, back to index.jsp");
	        response.sendRedirect("index.jsp");
	    } else {
	        //if long url found, so redirect the browser
	        System.out.println("redirecting to "+longUrl );
	        response.sendRedirect(longUrl);
	    }
    }
}
