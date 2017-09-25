<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Edit</title>
<link rel="stylesheet" href="master.css">
</head>
<body>
	<div id="wrap">
		<div id="top-section" class="">
			<img src="images/adventuringParty.jpg" alt="adventurers">
		</div>
		<div id="main-section" class="">
			<div id="backpack-form" class="sub-sections">
				<h3>${name} Backpack</h3>
				<table>
				  <tbody>
				  <c:forEach var="back" items="${backpack}">
				  	<tr>
				  		<td>${back.name}</td>
			              <td>
			                <form class="" action="deleteItem.do" method="post">
			                  <input type="hidden" name="${back.id }" value="1">
			                  <input type="hidden" name="${id }" value="1">
			                  <input type="submit" name="submit" value="Delete">
			                </form>
			              </td>
				  	</tr>
				  </c:forEach>
				  </tbody>
				</table>
        <a href="updateCharacter.do?id=${id}&table=party">Go back to ${name} Equipment</a>
        <a href="home.do">Go to home page</a>
			</div>
      <div id="iem-section" class="sub-sections">
				<h3>Add Items to Backpack</h3>
				<form class="" action="addItem.do" method="post">
					<select class="" name="itemId">
						<c:forEach var="item" items="${itemList}">
							<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select>
					<input type="submit" name="submit" value="Add Item">
				</form>
				<h3>Create Item</h3>
				<form class="" action="createItem.do" method="post">
					<p>Item Name:
						<input type="text" name="name" value="">
					</p>
					<p>Item type
						<select class="" name="type">
							<c:forEach var="type" items="itemTypes">
								<option>${type}</option>
							</c:forEach>
						</select>
					</p>
					<input type="submit" name="submit" value="Create Item">
				</form>
			</div>
		</div>
	</div>
</body>
</html>