<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
				<h1><spring:message code="orderHead"/></h1>
			</div>
		</div>
	</section>
	
	<section class="container">
<form:form modelAttribute="customer" class="form-horizontal">	
				<legend><spring:message code="order.orderData"/></legend>
				<form:errors path="*" cssClass="alert alert-danger" element="div"/>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2">
						<spring:message code="order.totalPrice"/>
					</label>
					<div class="col-lg-10">
						</br>${totalPrice}PLN 
					</div>
				</div>
					
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">
						<spring:message code="customer.name"/>
					</label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text" maxlength="40" class="form:input-large"/>
						<form:errors path="name" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">
						<spring:message code="customer.surname"/>
					</label>
					<div class="col-lg-10">
						<form:input id="surname" path="surname" type="text" maxlength="40" class="form:input-large"/>
						<form:errors path="surname" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="unitPrice">
						<spring:message code="customer.city"/>
					</label>
					<div class="col-lg-10">
						<form:input id="city" path="city" type="text" maxlength="40" class="form:input-large"/>
						<form:errors path="city" cssClass="text-danger"/>
					</div>
				</div>
					
				<div class="form-group">	
					<label class="control-label col-lg-2 col-lg-2" for="category">
						<spring:message code="customer.street"/>
					</label>
					<div class="col-lg-10">
						<form:input id="street" path="street" type="text" maxlength="40" class="form:input-large"/>
						<form:errors path="street" cssClass="text-danger"/>
					</div>
				</div>
					
				<div class="form-group">	
					<label class="control-label col-lg-2 col-lg-2" for="unitsInStock">
						<spring:message code="customer.streetNumber"/>
					</label>
					<div class="col-lg-10">
						<form:input id="streetNumber" path="streetNumber" type="text" maxlength="11" class="form:input-large"/>
						<form:errors path="streetNumber" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">	
					<label class="control-label col-lg-2 col-lg-2" for="unitsInStock">
						<spring:message code="customer.phoneNumber"/>
					</label>
					<div class="col-lg-10">
						<form:input id="phoneNumber" path="phoneNumber" type="text" maxlength="11" class="form:input-large"/>
						<form:errors path="phoneNumber" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">	
					<label class="control-label col-lg-2 col-lg-2" for="unitsInStock">
						<spring:message code="customer.email"/>
					</label>
					<div class="col-lg-10">
						<form:input id="email" path="email" type="text" maxlength="40" class="form:input-large"/>
						<form:errors path="email" cssClass="text-danger"/>
					</div>
				</div>
					
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value =<spring:message code="editProduct.editButton"/>/>	
					</div>

				</div>
			</fieldset>
		</form:form></section>
</body>
</html>