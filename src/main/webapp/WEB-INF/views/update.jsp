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
  
<style type="text/css">
	#main {
		background-color: #F4BBFF;
	}
</style>
	   
    <body id="main">
       <div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
				
<div class="page-header">
  <h3>User Profile Management</small></h3>
</div>
        
        <form:form id="loginForm" commandName="updateBean" method="post" action="update" modelAttribute="updateBean" cssClass="form-horizontal" enctype="multipart/form-data">
           <form:errors path="*" cssClass="alert alert-danger" element="div" />
           <c:if test="${not empty sucessMsg}">
           <center> <form:label path="*" cssClass="alert alert-danger" element="div">${sucessMsg}</form:label></center>
           </c:if>
          	<form:hidden path="id" />
          	 <form:hidden path="userAddress.addressId" /> 
          	<form:hidden path="password" />  
            
              <div class="form-group">
            <form:label path="image" cssClass="control-label col-xs-3">Profile Image</form:label>
            <div class="col-xs-4"><img  src="data:image/jpeg;base64,${userImage}" height="120px" width="120px" align="left" /></div>
            </div>
            
            <div class="form-group">
            <form:label path="image" cssClass="control-label col-xs-3">Change Profile Image</form:label>
            <div class="col-xs-4"><form:input path="image" id="image" type="file" cssClass="form-control" cssStyle="background-color: #A2A2D0"/></div>
            </div>
            
            <div class="form-group">
            <form:label path="username" cssClass="control-label col-xs-3">User-Name</form:label>
            <div class="col-xs-4"><form:input id="username" name="username" path="username" cssClass="form-control" cssStyle="background-color: #A2A2D0"/></div>
            </div>
           
            
            <div class="form-group">
            <form:label path="email" cssClass="control-label col-xs-3">Email</form:label>
            <div class="col-xs-4"><form:input id="email" name="email" path="email" cssClass="form-control" cssStyle="background-color: #A2A2D0"/></div>
            </div>
            
            <div class="form-group">
            <form:label path="userAddress.houseNo" cssClass="control-label col-xs-3">House No</form:label>
            <div class="col-xs-4"><form:input id="houseNo" name="houseNo" path="userAddress.houseNo" cssClass="form-control" cssStyle="background-color: #A2A2D0"/></div>
            </div>
            
            <div class="form-group">
            <form:label path="userAddress.street" cssClass="control-label col-xs-3">Street</form:label>
            <div class="col-xs-4"><form:input id="street" name="street" path="userAddress.street" cssClass="form-control" cssStyle="background-color: #A2A2D0"/></div>
            </div>
            
            <div class="form-group">
            <form:label path="userAddress.city" cssClass="control-label col-xs-3">City</form:label>
            <div class="col-xs-4"><form:input id="city" name="city" path="userAddress.city" cssClass="form-control" cssStyle="background-color: #A2A2D0"/></div>
            </div>
            
             <div class="form-group">
            <form:label path="userAddress.state_id" cssClass="control-label col-xs-3">State</form:label>
            <div class="col-xs-4"><form:select id="state" name="state" path="userAddress.state_id" cssClass="form-control" cssStyle="background-color: #A2A2D0">
             <form:options items="${states}" />
             </form:select>
            </div>
            </div>
            
            <div class="form-group">
            <form:label path="userAddress.country" cssClass="control-label col-xs-3">Country</form:label>
            <div class="col-xs-4"><form:input id="country" name="country" path="userAddress.country" cssClass="form-control" cssStyle="background-color: #A2A2D0"/></div>
            </div>
           
            <input type="submit" name="Update" value="Update" class="btn btn-primary" role="button"/>
             <input type="submit" name="Logout" value="Logout" class="btn btn-primary" role="button"/>
        </form:form>
        </fieldset></div></div></div>
    </body>
    <script src = "https://code.jquery.com/jquery.js"></script>

</html>
