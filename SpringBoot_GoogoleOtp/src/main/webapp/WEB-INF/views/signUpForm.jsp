<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<link type="text/css" rel="stylesheet" href="./css/signup.css">
<script type="text/javascript" src="./js/signUpForm.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<H2>회원가입</H2>
	<form action="./userInsert.do" method="post">
		<table>
			<col width="150px">
			<col width="200px">
			<col width="500px">
			<col width="100px">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="username" required="required"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="plainPassword"></td>
			</tr>
		</table>
		<input type="submit" value="가입">
	</form>

</body>
</html>