<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>URL Shortener</title>
</head>

<style type="text/css">
div {
	text-align: center;
	padding: 10px;
	border: 4px solid grey;
	margin: auto;
  	width: 80%;
  	align:center;
}	
div.ex{
	background-color:#e6f2ff;	
}
</style>

<body>
	<h1 align="center"> URL Shortener</h1>
	<div>
		 <form action="./insert" method="GET" >
			<table align ="center" >
				<tr> 
					<td colspan=2 align="center">Please, put your URL to short: </td>
				</tr>
				<tr align="center"> 
					<td colspan=2  align="center">Long URL: <input type="text" name="urlLong" size="70%"/> </td>
				</tr>
				<tr> 
					<td colspan=2 align="center"><input type="submit" value="Get Short!" /></td>
				</tr>
			</table>
			
		</form>
	</div>
	<br>
	<%
	      if (session.getAttribute("shortUrl") != null) {
	 %>
	 	<div class="ex">
			<table align ="center">
				<tr> 
					<td colspan=2 align="center">Your short URL is: <a target="_blank" href="./recovery?urlShort=<%=session.getAttribute("shortUrl")%>">http://localhost:8080/NewURL/<%=session.getAttribute("shortUrl")%> </a></td>
				</tr>
					<tr> 
					<td colspan=2 align="center"> 
						(Click the link above to redirect to the URL)
					</td>
				</tr>
			</table>		
		</div>
		<%
	        }
	    %>  

	   <%
	      if (session.getAttribute("error") != null) {
	 	%>
	 	<div class="ex">
			<table align ="center">
				<tr> 
					<td colspan=2 align="center">Error Message: <% session.getAttribute("error");%></td>
				</tr>
			</table>		
		</div>
		<%
	        }
	    %>  
	 
</body>
</html>