<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pl-PL">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title><spring:message code="productDetails.information"/></title>
</head>
<body>
<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="productHead"/></h1>
			</div>
		</div>
	</section>
	
	<section class="container">
		<fieldset>
			<legend><spring:message code="productDetails.legend"/></legend>
			
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="productHead"/></label> ${product.name}
			</div> </br>
			
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="description"/></label> ${product.description}
			</div> </br>
			
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="unitPrice"/></label> ${product.unitPrice}PLN
			</div> </br>
			
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="unitsInStock"/></label> ${product.unitsInStock}
			</div> </br>
			
			
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="category"/></label> ${product.category}
			</div> </br>
		</fieldset>
		
		<a href="http://localhost:8080/webstore/products/" class="btn btn-primary"><spring:message code="backToTheListButton"/></a>		
	</section>
</body>
</html>