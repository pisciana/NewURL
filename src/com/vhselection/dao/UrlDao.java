package com.vhselection.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vhselection.model.Url;


/**
 * This DAO class provides CRUD database operations for the table url
 * in the database.
 * @author ana claudia Costa
 * @version 1.0
 */
public class UrlDao {
	
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    
    /**
     * UrlDao constructor
     *  
     * @param <String> jdbcURL
     * @param <String> jdbcUsername
     * @param <String> jdbcPassword
     */
    public UrlDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }//UrlDao
    
    /**
     * Method responsible for connecting to the Database
     * 
     * @throws SQLException
     */
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }//connect
    
    /**
     * Method responsible for disconnecting to the Database
     * 
     * @throws SQLException
     */
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }//disconnect
    
    /**
     * Method responsible for inserting a URL into the database
     *  
     * @param <Url> url to be inserted
     * 
     * @return {@code true} if everything ok with the insert
     * @throws SQLException
     */
    public boolean insertUrl(Url url) throws SQLException {
        String sql = "INSERT INTO url (url_short, url_long) VALUES (?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, url.getUrlShort());
        statement.setString(2, url.getUrlLong());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }//insertUrl
    
    /**
     * Method responsible for recovery a LongURL by the shortUrl
     * 
     * @param <String> urlShort
     * @return <String> urlLong
     * @throws SQLException
     */
    public String recoveryLongUrl(String urlShort) throws SQLException {
        String urlLong = null;
        String sql = "select url_long from url where url_short = ?";         
        
        connect();         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, urlShort);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) 
            urlLong = resultSet.getString("url_long");        
         
        resultSet.close();
        statement.close();
        disconnect();
        
        return urlLong;
    }//recoveryLongUrl
     

}
