<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

당신의 키는 → ${urlKeyMap.encodedKey} 입니다. <br>
당신의 바코드 주소는 → <a href="${urlKeyMap.url}">클릭</a> <br><br>
 
<form action="/result.do" method="get">
<!--     code : <input type="text" name="user_code"> -->
<%--     <input type="hidden" name="encodedKey" value="${urlKeyMap.encodedKey}" readonly="readonly"><br><br> --%>
    <p><input type="button" onclick="location.href='./otpCheck.do'" value="OTP로그인"><input type="button" onclick="location.href='./logout.do'" value="로그아웃"></p>
</form>

</body>
</html>