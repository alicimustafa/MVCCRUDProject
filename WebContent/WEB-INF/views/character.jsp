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
	<form:form action="stuff.do" method="POST" modelAttribute="character">
		<p>Name: <form:input path="email"/><form:errors path="email"/></p>
		<p>Character Class: 
			<select name="characterClass">
				<option></option>
			</select>
		</p>
		<p>Main-hand: 
			<select name="mainHand">
			
			</select>
		<p>Off-hand: <form:input path="firstName"/><form:errors path="firstName"/></p>
		<p>Armor: <form:input path="lastName"/><form:errors path="lastName"/></p>
		<p>Age: <form:input path="age"/><form:errors path="age"/></p>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>