<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <script>
      var result = ${bannerDeleteResult}
      if(result>0){
         alert('배너 삭제가 완료되었습니다.');
         location.href="${pageContext.request.contextPath}/todaylessonadmin/admin_banner_list";
      }else{
	     alert('배너 삭제가 실패하였습니다.');
	     location.href="${pageContext.request.contextPath}/todaylessonadmin/admin_banner_list";
      }
   </script>
</body>
</html>