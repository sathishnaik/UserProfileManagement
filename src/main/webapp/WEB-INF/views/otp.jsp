<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OTP Generation</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  
<body>
       <div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
        
       <%--  <form:form id="loginForm" method="post" action="otp" modelAttribute="loginBean">
          
            
            <input type="submit" value="Generate OTP"/>
            
        </form:form> --%>
        
     
        
        <form:form id="otpBean" method="post" action="login" commandName="otpBean" modelAttribute="otpBean" cssClass="form-horizontal">
         <center>OTP generated is  ${otp}</center>
            <div class="form-group">
            <form:label path="username" cssClass="control-label col-xs-4">User-Name</form:label>
            <div class="col-xs-5"><form:input id="username" name="username" path="username" cssClass="form-control" /></div>
            </div>
             <div class="form-group">
             <form:label path="otp" cssClass="control-label col-xs-4">Generated OTP</form:label>
            <div class="col-xs-5"><form:input id="otp" name="otp" path="otp.otpCode" cssClass="form-control"/></div>
            </div>
            
            
           <a href="<c:url value='/backToLogin' />" class="accordion-toggle btn-block text-warning">Back to Login</a>
            
        </form:form>
        </fieldset></div></div></div>
    </body>

 <script src = "https://code.jquery.com/jquery.js"></script>
</html>
