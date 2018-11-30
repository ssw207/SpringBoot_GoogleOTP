<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>moim</title>
</head>
<script type="text/javascript">
function signUpForm(){
	location.href="./signUpForm.do";
	
}
</script>

<body>
	<div id="title">
		<h1>구글 OTP만들기</h1>
	</div>
	<br>
	<form action="./userLogin.do" method="post">
		<table>
			<tr>
				<td><input placeholder="ID" type="text" name="username" required="required"></td>
				<td><input type="button" value="회원가입" onclick="signUpForm()"></td>
			</tr>
			<tr>
				<td><input placeholder="PW" type="password" name="plainPassword" required="required"></td>
				<td><input type="submit" value="로그인"></td>
			</tr>
		</table>
	</form>
</body>
</html>








