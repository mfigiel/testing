<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			
			<c:forEach var="order" items="${orderList}">
		
    		<div> 
    		Zamawiajacy:
    		<c:out value="${order.id}" /> 
    		<c:out value="${order.orderDate}" />
    		<c:out value="${order.customer.name}" />
    		<c:out value="${order.customer.surname}" />
    		<c:out value="${order.customer.city}" />
    		<c:out value="${order.customer.street}" />
    		<c:out value="${order.customer.streetNumber}" />
    		<c:out value="${order.customer.phoneNumber}" />
    		<c:out value="${order.customer.email}" />   		
    		</div>
    		
    		<div>Zamowienie: </div>
    		<c:forEach var="product" items="${order.products}">
    		<div>
    		<c:out value="${product.quantity}" />
    		<c:out value="${product.product.name}" />
    		<c:out value="${product.product.unitPrice}" />
    		<c:out value="${product.product.unitPrice * product.quantity}" />
 			</div>
 			</c:forEach>
 			<br/>
			</c:forEach>
		</fieldset>
		
		<a href="http://localhost:8080/webstore/products/" class="btn btn-primary"><spring:message code="backToTheListButton"/></a>		
	</section>
</body>
</html>