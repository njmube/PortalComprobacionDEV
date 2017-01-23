<%@page import="com.wise.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Olvido su password</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<% String URL_AppRelative = request.getContextPath(); %>
<link href="<%= URL_AppRelative %>/resources/img/favicon.ico" rel="shortcut icon">
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/bootstrap/css/bootstrap-combined.min.css" >
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/xtheme-gray.css"/>
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/custom.css"/>
<script type="text/javascript" src="<%= URL_AppRelative %>/resources/sencha/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/resources/sencha/ext/ext-all.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/resources/sencha/ext/ext-lang-es.js"></script>

<script type="text/javascript"> 	
 	var contextrootpath = "<%= URL_AppRelative %>";
 	Ext.Ajax.timeout = 120000;
</script>
<script type="text/javascript" src="<%= URL_AppRelative %>/resources/sencha/ext/forgotPassword.js"></script>
<style>
.dropdown-menu li > a:hover, .dropdown-menu li > a:focus, .dropdown-submenu:hover > a {
	background-color: #eee;
	background-image: none;
	color: black;	
}
.dropdown-header {
  font-weight: bold;
  text-align: center;
}
.navbar-inner {
	background-image: linear-gradient(to bottom, #CCCCCC, #F2F2F2);
}
.navbar .nav > .active > a, .navbar .nav > .active > a:hover, .navbar .nav > .active > a:focus {
  -moz-text-decoration-color: -moz-use-text-color;
  -moz-text-decoration-line: none;
  -moz-text-decoration-style: solid;
  background-color: #FEFEFE;
  box-shadow: 0 3px 8px rgba(0, 0, 0, 0.125) inset;
  color: #555555;
}
.navbar {
  margin-bottom: 0;
  overflow-x: visible;
  overflow-y: visible;
}
.navbar.navbar-static {
  z-index: 1000;
}
</style>

</head>
<body style="background-color: #eee;">
</body>
</html>