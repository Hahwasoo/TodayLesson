<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
mycart������<br>
<table>
<thead>
<tr>
<th>��ǰ��ȣ</th><th></th><th>��ǰ��</th><th>����</th><th>����</th>
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${list}">
<tr>
<td>${item.product_no }</td>
<td><a href="${pageContext.request.contextPath}/ej_store_detail/${item.product_no}"><img src="${item.product_thumb}" alt="thumb"></a></td>
<td><a href="${pageContext.request.contextPath}/ej_store_detail/${item.product_no}">${item.product_name }</a></td>
<td>${item.product_cost }</td>
<td>${item.cart_amount }</td>
</tr>
</c:forEach> 
</tbody>
</table>

</body>
</html>