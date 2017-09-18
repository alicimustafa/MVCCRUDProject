<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Party Picker</title>
<link rel="stylesheet" href="master.css">
</head>
<body>
	<div id="wrap">
		<div id="top-section" class="">
			<img src="images/adventuringParty.jpg" alt="adventurers">
		</div>
		<div id="main-section" class="">
			<div id="party-section" class="sub-sections">
				<h3>List of Characters In Your Party</h3>
				<table>
					<thead>
						<tr>
							<th>Name</th>
							<th>Class</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="party" items="${party}" varStatus="loop">
						<tr>
						<td><a href="updateCharacter.do?id=${loop.index}&table=party">
						${party.name}
						</a></td>
						<td>${party.characterClass}</td>
						<td><form class="" action="party.do" method="post">
								<input type="hidden" name="partyMember" value="${loop.index }"> 
								<input type="submit" name="remove" value="Remove">
							</form>
						</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="pool-section" class="sub-sections">
				<h3>List of Characters Avaliable</h3>
				<table>
					<thead>
						<tr>
							<th>Name</th>
							<th>Class</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pool" items="${pool}" varStatus="loop">
						<tr>
						<td><a href="updateCharacter.do?id=${loop.index}&table=pool">
						${pool.name}
						</a></td>
						<td>${pool.characterClass}</td>
						<td><form class="" action="pool.do" method="post">
								<input type="hidden" name="poolMember" value="${loop.index }"> 
								<input type="submit" name="delete" value="Delete">
								<input type="submit" name="move" value="Add to Party">
							</form>
						</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<a href="createCharacter.do">Add a Character</a>
			</div>
		</div>
	</div>
</body>
</html>