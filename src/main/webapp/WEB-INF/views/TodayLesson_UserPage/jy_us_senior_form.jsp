<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>

<script>
	
	function senior_switch(){
		
		if (${mem_level}==1) {
			opener.location.href="${pageContext.request.contextPath}/todaylessonsenior/senior_switch";
			self.close();
		} else {
			opener.location.href="${pageContext.request.contextPath}/todaylessonsenior/you_are_senior";
			self.close();
		}
				
		
	}	

</script>

<h2>시니어 지원</h2>
<h3>시니어 회원으로 전환하시겠습니까?</h3>
<input type="button" value="예" onclick="senior_switch();">
<input type="button" value="아니요" onclick="self.close();">
</body>
</html>