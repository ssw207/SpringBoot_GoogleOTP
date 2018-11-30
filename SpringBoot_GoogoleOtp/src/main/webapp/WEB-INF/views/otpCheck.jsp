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
<H1>OPT인증</H1>
<%-- 세션 데이터 : ${userDto} --%>
<form action="/result.do" method="get">
    <input type="text" name="user_code" placeholder="OTP code입력"><br>
    <input type="hidden" name="encodedKey" value="${userDto.otpSecretKey}" readonly="readonly"><br>
    <input type="submit" value="확인"><input type="button" onclick="location.href='./logout.do'" value="로그아웃">
</form>

</body>
</html>