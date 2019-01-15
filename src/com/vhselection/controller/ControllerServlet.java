package com.vhselection.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vhselection.dao.UrlDao;
import com.vhselection.model.Url;
import com.vhselection.util.UrlUtils;



/**
 * Class ControllerServlet
 * 
 * @author ana claudia Costa
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UrlDao UrlDao;
    private String domain = "http://localhost:8080/NewURL/";
 
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
    
    /**
     * Method doPost
     */
    protected void doPost(
    		HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    	
        doGet(request, response);
    }//doPost
    
    /**
     * Method doGet
     */
    protected void doGet(
    		HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String action = request.getServletPath(); 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertUrl(request, response);
                break;
            case "/recovery":
                recoveryUrl(request, response);
                break;
            default:
            	showNewForm(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }//doGet
    
    /**
     * Responsible to recovery a LongURL from a shortURL
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException 
     */
    private void recoveryUrl(
    		HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
        String urlShort = request.getParameter("urlShort");
	    String longUrl = null;	    
	    if (urlShort != null && !"".equals(urlShort)) {
	        try {
	        	longUrl = UrlDao.recoveryLongUrl(urlShort);
			    request.getSession().setAttribute("longUrl", longUrl);

	        } catch (Exception e) {
	        // handling exception here
	        e.printStackTrace();
	        }
	    }else {
		    request.getSession().setAttribute("error", "Invalid URL ");

        }
	    System.out.println(longUrl);
    	response.sendRedirect("Redirect.jsp");  
    	


    }//recoveryUrl

    /**
     * Responsible for redirect to index page
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showNewForm(
    		HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
    	
        RequestDispatcher dispatcher = 
        		request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }//showNewForm
 
 
    /**
     * Responsible for generate the shortURL and insert into Database
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     */
    private void insertUrl(
    		HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	
        String urlLong = request.getParameter("urlLong");
        if (urlLong != null && !"".equals(urlLong)) {
			UrlUtils u = new UrlUtils();
			String urlShort = u.shortenURL(urlLong);
			Url newUrl = new Url(urlShort, urlLong);
			UrlDao.insertUrl(newUrl); 
		    request.getSession().setAttribute("shortUrl", urlShort);
        } else {
		    request.getSession().setAttribute("error", "Please, fill the URL ");

        }
        request.getSession().removeAttribute("urlLong");
        response.sendRedirect("./new");
    }//insertUrl
  
    
}