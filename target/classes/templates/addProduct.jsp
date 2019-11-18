<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<title><spring:message code="productsHead"/></title>
</head>
<script>
function validateForm(){
	if(!isNaN(unitPrice.value)){
		return(!isNaN(unitsInStock.value))
	}
	return false;
}
</script>
<body>
<script>
</script>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="productsHead"/></h1>
				<a href="<spring:url value="/products"/>" class="btn btn-primary" >
					<spring:message code="backToTheListButton"/>
				</a>
			</div>
		</div>
	</section>
	<section class="container">
		<form:form modelAttribute="newProduct" class="form-horizontal" >
			<form:errors path="*" cssClass="alert alert-danger" element="div"/>
			<fieldset>
				<legend><spring:message code="addProduct.legend"/></legend>										
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">
						<spring:message code="productName"/>
					</label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text" maxlength="40" class="form:input-large"/>
						<form:errors path="name" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="unitPrice">
						<spring:message code="unitPrice"/>
					</label>
					<div class="col-lg-10">
						<form:input id="unitPrice" path="unitPrice" type="text" maxlength="10" class="form:input-large"/>
						<form:errors path="unitPrice" cssClass="text-danger"/>
					</div>
				</div>
					
				<div class="form-group">	
					<label class="control-label col-lg-2 col-lg-2" for="category">
						<spring:message code="category"/>
					</label>
					<div class="col-lg-10">
						<form:input id="category" path="category" type="text" maxlength="40" class="form:input-large"/>
					</div>
				</div>
					
				<div class="form-group">	
					<label class="control-label col-lg-2 col-lg-2" for="unitsInStock">
						<spring:message code="unitsInStock"/>
					</label>
					<div class="col-lg-10">
						<form:input id="unitsInStock" path="unitsInStock" type="text" maxlength="11" class="form:input-large"/>
						<form:errors path="unitsInStock" cssClass="text-danger"/>
					</div>
				</div>
					
					
					<div class="form-group">
						<label class="control-label col-lg-2" for="description">
							<spring:message code="description"/>
						</label>
						<div class="col-lg-10">
							<form:textarea id="description" path="description" maxlength="150" rows = "2"/>
							<form:errors path="description" cssClass="text-danger"/>
						</div>
					</div>
	
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value =<spring:message code="products.addProductButton"/>/>	
					</div>

				</div>
			</fieldset>
		</form:form>
	</section>

</body>
</html>