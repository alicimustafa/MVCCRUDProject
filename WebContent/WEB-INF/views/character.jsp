<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Character Edit</title>
</head>
<body>
<form action="character.do" method="POST">
	<p>Name:<input type="text" name="name" /></p>
	<p>Class:<form:select path="classType" name="classType">
		<form:options items="${classType}" />
	</form:select></p>
	<p>Main Hand:<form:select path="mainHand" name="mainHand">
		<form:options items="${mainHand}" />
	</form:select></p>
	<p>Off Hand<form:select path="offHand" name="offHand">
		<form:options items="${offHand}" />
	</form:select></p>
	<p>Armor:<form:select path="armor" name="armor">
		<form:options items="${armor}" />
	</form:select></p> 
	<input type="submit" name="${submitType}" value="${submitType}" />
</form> 
</body>
</html>