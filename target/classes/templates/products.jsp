<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
    function paymentButtonOff() {
        document.getElementById("addtocartButton").disabled = true;
    }

    function paymentButtonOn() {
        document.getElementById("addtocartButton").disabled = false;
    }
</script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title><spring:message code="productsHead"/></title>
gfgdggddgdg
	<style type="text/css">
	.menu-items {
		float: left;
		height: 90px;
		margin-left: 10px;
		margin-right: 10px;
	}
</style>
</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<h1><spring:message code="productsHead"/></h1>
					<p><spring:message code="products.Title"/></p>		
				    <a href="<spring:url value="/products/add"/>" class="btn btn-primary" ><spring:message code="products.addProductButton"/></a>
				    <a href="<spring:url value="/cart"/>" class="btn btn-primary" ><spring:message code="products.cartButton"/></a>
				</div>		
			</div>
		</section>
		<section class="container">
		<c:if test="${productsOrdered == 'true'}">
				<div>
					<h3><spring:message code="productsOrdered"/></h3>
				</div>
			</c:if>
			<div class="row">
			<c:forEach items="${products}" var="product">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">	
						<div class="caption">	
							<h3>${product.name}</h3>
							<p>${product.unitPrice}PLN</p>
							<p><spring:message code="unitsInStock"/>: ${product.unitsInStock}</p>
							<a href="<spring:url value="/products/edit-${product.productId}"/>" class="btn btn-primary" ><spring:message code="products.editProductButton"/></a> 
							<a href="<spring:url value="/products/remove-${product.productId}"/>" class="btn btn-primary" ><spring:message code="products.deleteProductButton"/></a>
							<a href="<spring:url value="/products/details-${product.productId}"/>" class="btn btn-primary"><spring:message code="products.showProductDetailButton"/></a>
							<c:if test="${product.unitsInStock != 0}">
							<button id="addtocartButton" type="button" class="btn btn-primary" onClick="location.href='<spring:url value="/products/addtocart-${product.productId}"/>'" >
							<spring:message code="products.addProductToCartButton"/></button>
							</c:if>	
						</div>												
					</div>
				</div>	
			</c:forEach>
			</div>
		</section>
	</body>
</html>