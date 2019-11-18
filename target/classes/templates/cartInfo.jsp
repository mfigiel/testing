<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<title><spring:message code="productsHead"/></title>
	<script src="<c:url value="/resources/js/order.js" />"></script>
</head>
<body>
<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="productsHead"/></h1>
				<a href="<spring:url value="/products"/>" class="btn btn-primary" >
					<spring:message code="backToTheListButton"/>
				</a>
					
				<button id="paymentButton" type="button" class="btn btn-primary" 
				onClick="location.href='<spring:url value="/payment"/>'" >
				<spring:message code="cart.payment"/></button>
				
				<c:if test="${totalPrice eq 0}">
					<script>paymentButtonOff()</script>
				</c:if>	
			</div>
		</div>
	</section>
	
	<section class="container">
		<fieldset>
	<div class="form-group">
					<h4><label class="control-label col-lg-2 col-lg-2">
					<spring:message code="order.totalPrice"/></label> ${totalPrice}PLN </h4>
				</div> 
	</fieldset>
	</section></br>
	
	<c:forEach items="${cartItemList}" var="product">
	<section class="container">
		<fieldset>
			<legend><spring:message code="productDetails.legend"/></legend>
			
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="productHead"/></label> ${product.cartProduct.name}
			</div> </br>
			
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="description"/></label> ${product.cartProduct.description}
			</div> </br>
			
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="unitPrice"/></label> ${product.cartProduct.unitPrice}PLN
			</div> </br>
			
			
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="category"/></label> ${product.cartProduct.category}
			</div> </br>
			
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="order.productQuantity"/></label> ${product.quantity} 
			</div> </br>
			<div class="form-group">
			<label class="control-label col-lg-2 col-lg-2">
			<spring:message code="order.cartItem.price"/></label> ${product.price}PLN 
			</div>
			
			<a href="<spring:url value="/deleteProductFromCart-${product.cartProduct.productId}"/>" class="btn btn-primary" >
					<spring:message code="order.deleteProductFromCart"/>
				</a>
		</fieldset></br>
	</section>
	</c:forEach>
</body>
</html>