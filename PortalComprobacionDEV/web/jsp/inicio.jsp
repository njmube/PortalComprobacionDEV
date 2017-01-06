<%@page import="com.wise.dto.FullConfigDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Inicio</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<% String URL_AppRelative = request.getContextPath(); %>
<% FullConfigDto fullConfigDto = (FullConfigDto)session.getAttribute("FullConfigDto"); %>
<link href="<%= URL_AppRelative %>/resources/img/favicon.ico" rel="shortcut icon">
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/bootstrap/css/bootstrap-combined.min.css" >
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/xtheme-gray.css"/>
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/custom.css"/>
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/ux/fileuploadfile.css"/>
<script type="text/javascript" src="<%= URL_AppRelative %>/resources/sencha/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/resources/sencha/ext/ext-all.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/ext-lang-es.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/ux/FileUploadField.js"></script>
<script type="text/javascript"> 	
 	var contextrootpath = "<%= URL_AppRelative %>";
 	Ext.Ajax.timeout = 120000;
 	var bundle;
</script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/utilities.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/ux/CheckColumn.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/ux/PropertyReader.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/ux/Bundle.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/listadoAnticipos.js"></script>
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
<body>
<img style="padding:20px;" src="<%= URL_AppRelative %>/resources/img/mciTop.png" width="200"/>
<span style="float:right">Idioma : <a href="?language=en">English</a>|<a href="?language=es">Español</a></span>
<span class="pull-right label label-default" style="margin-top: 5px; padding: 5px 60px; font-size: 15px; clear: right; background-color: #3c3c3b;"><spring:message code="label.provider"/> <%= fullConfigDto.getLifrn() %> <%= fullConfigDto.getName1() %></span>
<div id="navbar-example" class="navbar navbar-static" style="z-index:1000;">
  <div class="navbar-inner">  	
    <div class="container" style="width: auto;">     	
      <ul class="nav" role="navigation">
        <li class="active"><a id="fat-menu" href="<%= URL_AppRelative%>/Inicio?language=${pageContext.response.locale}" role="button"><spring:message code="menu.advancepaymentslist"/></a>
      </li>
        <li class=""><a id="fat-menu" href="<%= URL_AppRelative%>/CrearSolicitud?language=${pageContext.response.locale}" role="button"><spring:message code="menu.travelform"/></a>
      </li>
        <li class=""><a id="fat-menu" href="<%= URL_AppRelative %>/ListadoComprobaciones?language=${pageContext.response.locale}" id="drop2" role="button" class=""><spring:message code="menu.expenseslist"/></a>
      </li>
        <li class=""><a id="fat-menu" href="<%= URL_AppRelative %>/EstadoCuenta?language=${pageContext.response.locale}" id="drop2" role="button" class=""><spring:message code="menu.accountstatement"/></a>
      </li>
      </ul>
      <ul class="nav pull-right">
        <li id="fat-menu"><a tabindex="-1" href="<%= URL_AppRelative%>/logout" role="button"><spring:message code="label.logout"/></a>
      </li>
      </ul>
    </div>
  </div>
</div>
<div id="formListaSolicitud"></div>
<script src="<%= URL_AppRelative %>/resources/bootstrap/js/jquery.min.js"></script>
<script src="<%= URL_AppRelative %>/resources/bootstrap/js/bootstrap303.js"></script>
<script>
(function ($, window, delay) {
	  var theTimer = 0;
	  var theElement = null;
	    var theLastPosition = {x:0,y:0};
	  $('[data-toggle]')
	    .closest('li')
	    .on('mouseenter', function (inEvent) {
	    if (theElement) theElement.removeClass('open');
	    window.clearTimeout(theTimer);
	    theElement = $(this);

	    theTimer = window.setTimeout(function () {
	      theElement.addClass('open');
	    }, delay);
	  })
	    .on('mousemove', function (inEvent) {
	        if(Math.abs(theLastPosition.x - inEvent.ScreenX) > 4 || 
	           Math.abs(theLastPosition.y - inEvent.ScreenY) > 4)
	        {
	            theLastPosition.x = inEvent.ScreenX;
	            theLastPosition.y = inEvent.ScreenY;
	            return;
	        }
	        
	    if (theElement.hasClass('open')) return;
	    window.clearTimeout(theTimer);
	    theTimer = window.setTimeout(function () {
	      theElement.addClass('open');
	    }, delay);
	  })
	    .on('mouseleave', function (inEvent) {
	    window.clearTimeout(theTimer);
	    theElement = $(this);
	    theTimer = window.setTimeout(function () {
	      theElement.removeClass('open');
	    }, delay);
	  });
	})(jQuery, window, 200); // 200 is the delay in milliseconds
</script>
</body>
</html>