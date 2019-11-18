<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet"
		href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Witaj</title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<h1> ${greeting} </h1>
					<p> ${tagline} </p>
					
					<a href="<c:url value="http://localhost:8080/webstore/products" />" class="btn btn-primary" >Przejdz do sklepu</a>
				</div>
			</div>
		</section>
	</body>
</html>
