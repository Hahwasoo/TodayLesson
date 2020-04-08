<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<style>
#ej_container{
width:90%;
margin: 0 auto;
}
.ej_likespan{
display: inline-block;
background-color: rgba( 213, 213, 213, 0.5 );
color:  rgba(53, 54, 58,0.9); 
font-weight:bold;
width:33.3%;
padding:20px;
margin:0px;
/* margin-top:0; */
/* height:60px; */
float:left;
text-align: center;
}
#ej_top{
/* margin-left:30%; */
/* width:100%; */
text-align: center;
}
.ej_link{
color:black;}
a.ej_link:hover{
text-decoration: none;
}
</style>

<title>Insert title here</title>
</head>
<body>
<script>
$(".ej_likespan").click(function(){
	alert(hi);
	alert(this);
	var val=$(this).val();
	cnosole.log(val);
});

</script>
<div id="ej_container">
<div id="ej_top">
<a href="?category=all"><div class="ej_likespan all">��ü</div></a>
<a href="?category=lesson"><div class="ej_likespan lesson">����</div></a>
<a href="?category=store"><div class="ej_likespan store">��ǰ</div></a>
</div>
<br>
<br>
<br>
<table class="table">
<thead>
<tr>
<th>����</th><th></th><th>�̸�</th><th>����</th>
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${list}">
<tr>
<c:set var="product_name" value="${item.product_name }"/>
<c:set var="lesson_title" value="${item.lesson_title }"/>

<c:if test="${lesson_title==null }">
<td>��ǰ</td>
<td><a href="${pageContext.request.contextPath}/todaylesson/ej_store_detail/${item.product_no}"><img src="${item.product_thumb}" alt="thumb"></a></td>
<td><a href="${pageContext.request.contextPath}/todaylesson/ej_store_detail/${item.product_no}" class="ej_link">${item.product_name }</a></td>
<td>${item.product_cost }��</td></c:if>


<c:if test="${product_name==null }">
<td>����</td>
<td><a href="${pageContext.request.contextPath}/todaylesson/lesson_detail/${item.lesson_no}"><img src="${item.lesson_thumb}" alt="thumb"></a></td>
<td><a href="${pageContext.request.contextPath}/todaylesson/lesson_detail/${item.lesson_no}" class="ej_link">${item.lesson_title }</a></td>
<td>${item.lesson_cost }��</td></c:if>

</tr>
</c:forEach> 
</tbody>
</table>

</div>
</body>
</html>