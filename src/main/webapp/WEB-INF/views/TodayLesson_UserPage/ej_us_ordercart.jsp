<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<!--���� ����Ʈ ����  -->
�ֹ���¥  ${orderlistdto.orderlist_date }<br>
 �ֹ���ȣ  ${orderlistdto.orderlist_no }<br>
������  ${orderlistdto.orderlist_receiver }<br>
���� �ּ�  ${orderlistdto.orderlist_addr }<br>
�����ݾ� <fmt:formatNumber value="${orderlistdto.orderlist_cost }" type="number" maxFractionDigits="3"/> ��<br>
�������Ʈ <fmt:formatNumber value="${orderlistdto.orderlist_usepoint  }" type="number" maxFractionDigits="3"/> <br>
<c:set var="cost" value="${orderlistdto.orderlist_cost }"> </c:set>
<c:set var="addedpoint" value="${(cost*0.1)}"></c:set>
<fmt:formatNumber value="${addedpoint }" type="number" maxFractionDigits="3"/> ����Ʈ�� �����Ǿ����ϴ�!<br>
��ۿ�û����  ${orderlistdto.orderlist_msg  }
<hr>
<!--���� ������ ����  -->
<c:forEach var="item" items="${list}"> 
<c:set var="product_name" value="${item.product_name }"/>
<c:set var="lesson_title" value="${item.lesson_title }"/>
<c:if test="${lesson_title==null }">
��ǰ��: ${item.product_name }  </c:if>
<c:if test="${product_name==null }">
������:${item.lesson_title} </c:if>
����: ${item.order_count }
<br>
</c:forEach>


</body>
</html>