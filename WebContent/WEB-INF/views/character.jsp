<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Character Edit</title>
<link rel="stylesheet" href="master.css">
</head>
<body>
	<div id="wrap">
		<div id="top-section" class="">
			<img src="images/adventuringParty.jpg" alt="adventurers">
		</div>
		<div id="main-section" class="">
			<div id="form-section" class="sub-sections">
				<form action="character.do" method="POST">
					<p>Name:<input type="text" name="name" value="${adventurer.name}"></p>
					<input type="hidden" name="id" value="${adventurer.id}"> 
					<input type="hidden" name="table" value="${table}">
					<p>Class:
						<select name="classType">
							<c:forEach var="cls" items ="${classType}">
								<c:choose>
									<c:when test="${adventurer.characterClass == cls}">
    										<option value="${cls}" selected>${cls}</option>
  									</c:when>
  									<c:otherwise>
  										<option value="${cls}">${cls}</option>
  									</c:otherwise>
								</c:choose>
						    </c:forEach>
						</select>
					</p>
					<p>Main Hand:
						<select name="mainHand">
							<c:forEach var="mainHand" items ="${mainHand}">
								<c:choose>
									<c:when test="${adventurer.mainHand.id == mainHand.id}">
    										<option value="${mainHand.id}" selected>${mainHand.name}</option>
  									</c:when>
  									<c:otherwise>
  										<option value="${mainHand.id}">${mainHand.name}</option>
  									</c:otherwise>
								</c:choose>
						    </c:forEach>
						</select>
					</p>
					<p>Off Hand
						<select name="offHand">
							<c:forEach var="offHand" items ="${offHand}">
								<c:choose>
									<c:when test="${adventurer.offHand.id == offHand.id}">
    										<option value="${offHand.id}" selected>${offHand.name}</option>
  									</c:when>
  									<c:otherwise>
  										<option value="${offHand.id}">${offHand.name}</option>
  									</c:otherwise>
								</c:choose>
						    </c:forEach>
						</select>
					</p>
					<p>Armor: 
						 <select name="armor">
							<c:forEach var="armor" items ="${armor}">
									<c:choose>
									<c:when test="${adventurer.armor.id == armor.id}">
    										<option value="${armor.id}" selected>${armor.name}</option>
  									</c:when>
  									<c:otherwise>
  										<option value="${armor.id}">${armor.name}</option>
  									</c:otherwise>
								</c:choose> 
						    </c:forEach>
						</select> 
					</p>
 					<input type="submit" name="${submitType}" value="${submitType}" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>