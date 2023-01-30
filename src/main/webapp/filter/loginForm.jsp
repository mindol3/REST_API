<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String error = request.getParameter("error");
		if(error != null && error.equals("1")) {
			out.print("<h4>아이디를 입력해 주세요.</h4>");
		}
	
	%>
	<h2>EncForm.jsp</h2>
	<hr>
	<form method="post" action="loginForm_process.jsp">
		<p>아이디 : <input type="text" name="id">
		<p>비밀번호 : <input type="text" name="passwd">
		<p><input type="submit" value="전송">
	</form>
</body>
</html>