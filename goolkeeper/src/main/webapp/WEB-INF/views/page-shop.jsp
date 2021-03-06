<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>  

<spring:url var="css" value="/resources/css"/>
<spring:url var="js" value="/resources/js"/>
<spring:url var="images" value="/resources/images"/>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/> 

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Online Shopping - ${title}</title>
    <script>
    	window.menu = '${title}';
    	window.contextRoot = '${contextRoot}';
    </script>

    <!-- Bootstrap lux Theme -->
    <link href="${css}/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="${css}/bootstrap-lux-theme.css" rel="stylesheet">
    
    <!-- Bootstrap datatables -->
    <link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
    
        
    <!-- Custom styles for this template -->
    <link href="${css}/myapp.css" rel="stylesheet">

  </head>

  <body>

	<div class="wrapper">
	

    <!-- Navigation -->
    <%@include file="./shared/navbar.jsp" %>

    <!-- Page Content -->
    <div class="content">
    	
	     <!-- Load de home content -->
	     <c:if test="${userClickHome == true }">
	     	<%@include file="home.jsp" %>
	     </c:if>
	     
	     <!-- Load de about content -->
	     <c:if test="${userClickAbout == true }">
	     	<%@include file="about.jsp" %>
	     </c:if>
	     
	      <!-- Load de contact content -->
	     <c:if test="${userClickContact == true }">
	     	<%@include file="contact.jsp" %>
	     </c:if>     
	     
	      <!-- Load de products content -->
	     <c:if test="${userClickAllProducts == true  or userClickCategoryProducts == true }">
	     	<%@include file="listProducts.jsp" %>
	     </c:if>

	      <!-- Load de single product content -->
	     <c:if test="${userClickShowSingleProduct == true  }">
	     	<%@include file="singleProduct.jsp" %>
	     </c:if>

	      <!-- Load de product management -->
	     <c:if test="${userClickManageProducts == true  }">
	     	<%@include file="manageProducts.jsp" %>
	     </c:if>
	     	     	     	     
	</div>

    <!-- Footer -->
	<%@include file="./shared/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="${js}/jquery.min.js"></script>
    <script src="${js}/jquery.validate.js"></script>
    <script src="${js}/bootstrap.bundle.min.js"></script>
    <script src="${js}/jquery.dataTables.js"></script>
    <script src="${js}/dataTables.bootstrap.js"></script>
    <script src="${js}/bootbox.min.js"></script>
	<script src="${js}/myapp.js"></script>
  
  	</div>
  </body>

</html>
