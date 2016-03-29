<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  
 <script type="text/javascript">
    function generateOtp(){
    	/* window.location.href="";
    	var username = document.getElementById("username").value;
    	 var url = 'generateOTP/'+username;
    	 window.location.href = url; */
    	 
    	 var form = document.getElementById("loginForm");
    	 form.action='generateOTP';
    	    form.submit();
           
    }
    
   
    </script>
	   
	   <div class="page-header">
  <h2>User Profile Management</h2>
  
</div>
    <body>
       <div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
        
     <%--    <form:form id="loginForm" method="post" action="otp" modelAttribute="loginBean">
          
            <form:errors path="*" cssClass="alert alert-danger" element="div" />
            <input type="submit" value="Generate OTP"/>
            <center>OTP generated is  ${otp}</center>
        </form:form> --%>
        
     
        
        <form:form id="loginForm" commandName="loginBean" method="post" action="login" modelAttribute="loginBean" cssClass="form-horizontal" enctype="multipart/form-data">
           <form:errors path="*" cssClass="alert alert-danger" element="div" />
            <div class="form-group">
            <form:label path="username" cssClass="control-label col-xs-3">User-Name</form:label>
            <div class="col-xs-5"><form:input id="username" name="username" path="username" cssClass="form-control" /></div>
            </div>
            <div class="form-group">
            <form:label path="password" cssClass="control-label col-xs-3">Password</form:label>
            <div class="col-xs-5"><form:password id="password" name="password" path="password" cssClass="form-control"/></div>
            </div>
            
            <div class="form-group">
             <form:label path="otp" cssClass="control-label col-xs-3">Generated OTP</form:label>
            <div class="col-xs-5"><form:input id="otp" path="otp.otpCode" cssClass="form-control"/></div>
            </div>
            <a href="<c:url value='javascript:generateOtp()' />" class="accordion-toggle btn-block text-warning">Generate OTP</a>
            <input type="submit" name="Login" value="Login" class="btn btn-primary" role="button" />
            <input type="submit" name="Register" value="Register" class="btn btn-primary" role="button"/>
        </form:form>
        </fieldset></div></div></div>
    </body>
    <script src = "https://code.jquery.com/jquery.js"></script>
<!-- <script src = "js/bootstrap.min.js"></script>
	   <script src = "js/bootstrap-theme.min.js"></script>
	   <script src = "js/jquery.min.js"></script> -->
</html>
