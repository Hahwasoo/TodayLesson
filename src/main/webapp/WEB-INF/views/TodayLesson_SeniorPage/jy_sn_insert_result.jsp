<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<script>

if (${result} > 0) {
	alert("등록 성공! 내부 검토 후 메일로 등록 여부를 보내드리겠습니다.");

	location.href="${pageContext.request.contextPath }/todaylessonsenior/lesson_list";

} else {
	

	alert("추가 실패!");
	location.href="${pageContext.request.contextPath }/todaylessonsenior/lesson_list";
	
}
</script>


</body>
</html>