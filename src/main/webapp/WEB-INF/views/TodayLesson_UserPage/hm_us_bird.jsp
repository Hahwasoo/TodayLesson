<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>얼리버드</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/CSS/hm_us_bird.css?ver=3">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>




</head>
<body>

	<img src="${main.banner_filepath}" alt="얼리버드 메인 이미지"
		class="birdmainimg">
	


	<div class="earlybirdtotal">
		<img src="${mini.banner_filepath}" alt="얼리버드 미니 배너"
			class="birdminiimg">
	</div>
	





	<!-- 실시간 베스트 얼리버드 -->
	<div
		style="background-color:#fff2f2; margin-bottom : 60px;">
		<div class="row" style="width: 80%; margin: auto;">
			<div>
				<h2 style="margin-top:50px;">
					<b style="font-size: 25px;">실시간 베스트 얼리버드</b>
				</h2>
				<!-- 기준 시간 출력 -->
				<p id="time-result" style="font-size:16px;"></p>
				<h5>실시간 조회수가 가장 높은 얼리버드 레슨 <b style="color: #FF7883;  font-size:30px;">BEST4</b></h5>
			
			
			
				<script>
       var d = new Date();
       var currentTime = d.getHours() + "시 " + d.getMinutes() + "분 ";
       var result = document.getElementById("time-result");
       result.innerHTML = currentTime + " 기준";
      </script>
      
      
			</div>

			<div style="margin-top: 30px; width: 100%;">
				<c:forEach begin="0" end="3" step="1" var="bestlist" items="${list}" varStatus="status">
			<%-- 	<p>
					<c:choose>
							<c:when test="${status.count == 1}">
							<h3>Best 1</h3>
							</c:when>
							<c:when test="${status.count == 2}">
							<h3>Best 2</h3>
							</c:when>
							<c:when test="${status.count == 3}">
							<h3>Best 3</h3>
							</c:when>
							<c:when test="${status.count == 4}">
							<h3>Best 4</h3>
							</c:when>
							</c:choose>

				</p> --%>
				 
				
					<div class="col-md-3" style="margin-bottom:30px;">
						<div class="hm_user_bird_best">
							<div class="hm_user_bird_best_img">	
								<a href="${pageContext.request.contextPath}/todaylesson/lesson_detail/${bestlist.lesson_no}"> <img src="${bestlist.lesson_thumb}" />
								</a>
								<ul class="hm_user_bird_best_social">
									<li><a href="#" class="fas fa-heart" id="${bestlist.lesson_no}"></a></li>
									<li><a href="#" class="fa fa-shopping-cart" id="${bestlist.lesson_no}"></a></li>
								</ul>
								<span class="hm_user_bird_best_label"> <c:out value="27%" />
								</span>
							</div>
							<div class="hm_user_bird_best_content">
								<c:set var="category" value="${bestlist.lesson_category }" />
								<c:choose>
									<c:when test="${bestlist.lesson_category == 1}">
										<c:out value="운동" />
									</c:when>

									<c:when test="${bestlist.lesson_category == 2}">
										<c:out value="교육" />
									</c:when>

									<c:when test="${bestlist.lesson_category == 3}">
										<c:out value="핸드메이드" />
									</c:when>

									<c:when test="${bestlist.lesson_category == 4}">
										<c:out value="IT" />
									</c:when>

									<c:when test="${bestlist.lesson_category == 5}">
										<c:out value="요리" />
									</c:when>

									<c:otherwise>
										<c:out value="기타" />
									</c:otherwise>
								</c:choose>
								<h6 class="hm_user_bird_best_title">
									<a href="#">${bestlist.lesson_title}</a>
								</h6>
								<div class="hm_user_bird_best_line"></div>
								<div class="hm_user_bird_best_nick">'${bestlist.senior_nick}'
									시니어</div>
								<div class="hm_user_bird_best_likenum_box">
									<span class="hm_user_bird_best_likenum"> <i
										class="fas fa-heart"></i> ${bestlist.lesson_like}
									</span>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 실시간 베스트 얼리버드  -->















<!-- 신규 얼리버드 레슨 -->
	<div class="earlybirdtotal" >
		<!-- 전체를 감싸는 div -->
		<div id="hm_us_newlesson" style="width: 100%; margin: auto;">


			<div class="heart">
				<span>New</span>
			</div>


			<div>
				<h2>
					<b style="font-size: 25px;"> 신규 얼리버드 </b>
				</h2>
				<p style="font-size:16px;">지금 막 올라온 신규 얼리버드 레슨들입니다!</p>
			</div>
			<div class="hm_us_newlesson_container">
				<div class="hm_us_newlesson_g-scrolling-carousel">
					<div class="hm_us_newlesson_items">

						<c:forEach begin="0" end="9" step="1" var="newlessonlist"
							items="${newbird}">

							<a href="${pageContext.request.contextPath}/todaylesson/lesson_detail/${newlessonlist.lesson_no}"> <img class="hm_us_newlesson_cg_img"
								src="${newlessonlist.lesson_thumb}" alt=""> <c:set
									var="category" value="${newlessonlist.lesson_category }" /> <c:choose>
									<c:when test="${category==1}">
										<p>
											<c:out value="운동" />
										</p>
									</c:when>
									<c:when test="${category==2}">
										<p>
											<c:out value="교육" />
										</p>
									</c:when>
									<c:when test="${category==3}">
										<p>
											<c:out value="핸드메이드" />
										</p>
									</c:when>
									<c:when test="${category==4}">
										<p>
											<c:out value="IT" />
										</p>
									</c:when>
									<c:when test="${category==5}">
										<p>
											<c:out value="요리" />
										</p>
									</c:when>
									<c:when test="${category==6}">
										<p>
											<c:out value="기타" />
										</p>
									</c:when>
								</c:choose>
								<p>
									<c:out value="${newlessonlist.lesson_title}" />
								</p>
								<hr style="margin: 0px 0px 5px;">
								<p class="hm_us_newlesson_senior_nick">
									<c:out value="'${newlessonlist.senior_nick}'시니어" />
								</p>
								<p>
									<i class="fas fa-heart" style="color: rgb(224, 62, 82);"></i>
									${newlessonlist.lesson_like}
								</p>
							</a>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 전체 div-->
	<!-- 레슨 신규 -->



<!-- 마감 얼리버드 레슨 -->

	<div
		style="background-color:#fff2f2; margin : 60px 0px; padding:20px 0px;">
 	<!-- <div class="earlybirdtotal"> -->
		<!-- 전체를 감싸는 div -->
		
		<div id="hm_us_endlesson" style="width: 80%; margin: auto;">
			
			
			
			
			
			
			
			
			
			
			
			
			<div style="margin-top:50px;">
				<h2>
					<b style="font-size: 25px;"> 곧 <b style="color:#FF7883;">마감</b>되는 얼리버드 레슨 </b>
				</h2>
				<p style="font-size:16px;">다시 돌아오지 않을 할인 혜택 놓치지 마세요!</p>
			</div>
			<div class="hm_us_endlesson_container">
				<div class="hm_us_endlesson_g-scrolling-carousel">
					<div class="hm_us_endlesson_items" style="margin-bottom:30px;">

						<c:forEach begin="0" end="9" step="1" var="endlessonlist"
							items="${endbird}">
						
							<a href="${pageContext.request.contextPath}/todaylesson/lesson_detail/${endlessonlist.lesson_no}"> <img class="hm_us_endlesson_cg_img"
								src="${endlessonlist.lesson_thumb}" alt=""> <c:set
									var="category" value="${endlessonlist.lesson_category }" /> <c:choose>
									<c:when test="${category==1}">
										<p>
											<c:out value="운동" />
										</p>
									</c:when>
									<c:when test="${category==2}">
										<p>
											<c:out value="교육" />
										</p>
									</c:when>
									<c:when test="${category==3}">
										<p>
											<c:out value="핸드메이드" />
										</p>
									</c:when>
									<c:when test="${category==4}">
										<p>
											<c:out value="IT" />
										</p>
									</c:when>
									<c:when test="${category==5}">
										<p>
											<c:out value="요리" />
										</p>
									</c:when>
									<c:when test="${category==6}">
										<p>
											<c:out value="기타" />
										</p>
									</c:when>
								</c:choose>
								<p>
									<c:out value="${endlessonlist.lesson_title}" />
								</p>
								<hr style="margin: 0px 0px 5px;">
								<p class="hm_us_endlesson_senior_nick">
									<c:out value="'${endlessonlist.senior_nick}'시니어" />
									
								</p>
								<p>
									<i class="fas fa-heart" style="color: rgb(224, 62, 82);"></i>
									${endlessonlist.lesson_like}
								</p>
							</a>
						</c:forEach>

					</div>
				</div>
			</div>
		<!-- </div> -->
	</div> 
	</div>
	<!-- 전체 div-->
	<!-- 레슨 마감 -->









	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/JS/hm_us_bird.js?ver=2"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/JS/hm_us_bird_endlesson.js?ver=1"></script>












	<!-- 레슨슬라이더  신규 -->
	<script>
        $(document).ready(function() {
            $(".hm_us_newlesson_g-scrolling-carousel .hm_us_newlesson_items").gScrollingCarousel();
        });   
   </script>
   	<!-- 레슨슬라이더  마감임박-->
	 <script>
        $(document).ready(function() {
            $(".hm_us_endlesson_g-scrolling-carousel .hm_us_endlesson_items").ELgScrollingCarousel();
        });  
   </script>
 
	<script type="text/javascript">
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-36251023-1']);
        _gaq.push(['_setDomainName', 'jqueryscript.net']);
        _gaq.push(['_trackPageview']);

        (function() {
            var ga = document.createElement('script');
            ga.type = 'text/javascript';
            ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(ga, s);
        })();
   </script>
   
   	<script type="text/javascript">
        var EL_gaq = EL_gaq || [];
        EL_gaq.push(['_setAccount', 'UA-36251023-1']);
        EL_gaq.push(['_setDomainName', 'jqueryscript.net']);
        EL_gaq.push(['_trackPageview']);

        (function() {
            var ELga = document.createElement('script');
            ELga.type = 'text/javascript';
            ELga.async = true;
            ELga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var ELs = document.getElementsByTagName('script')[0];
            ELs.parentNode.insertBefore(ELga, ELs);
        })();
   </script>
   
   
   <script>


 $(".fas.fa-heart").click(function(){
 let lessonno=$(this).prop("id");
 let memberid='${pageContext.request.userPrincipal.name}';

  
  let data = {
       lesson_no : lessonno,
       member_id: memberid
    };
  if(memberid=='')
  {
  alert('로그인이 필요합니다.');
  }else{
 
  $.ajax({
   url :"${pageContext.request.contextPath}/todaylesson/hm_lesson_like",// 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
   //request mapping value랑 맞추면되는듯
   type : "post",
   data : data,
   success : function(result){
	   console.log('result:',result);
    if(data.member_id==null)
       {
       alert('로그인이 필요합니다.');
       }
    else{
    	if(result=="success")
		{
		alert('♥');
	    location.href = "${pageContext.request.contextPath}/todaylesson/hm_us_bird";
		}
	else{
		alert('이미 좋아요에 추가된 상품입니다.');
	}
    		
    }
   
    } 
   ,error: function(){
      console.log(data);
      console.log('error');
     // alert('로그인이 필요합니다.');
      }
  }); 
  }
 });


$(".fa.fa-shopping-cart").click(function(){ 

 let lessonno=$(this).prop("id");
  let memberid='${pageContext.request.userPrincipal.name}';
  
  let data = {
       lesson_no : lessonno,
       member_id: memberid
    };
  console.log(memberid);
  if(memberid=='')
  {
  alert('로그인이 필요합니다.');
  }else{
 
  $.ajax({
   url :"${pageContext.request.contextPath}/todaylesson/hm_lesson_cart",
   type : "post",
   data : data,
   success : function(result){
	   console.log('result:',result);
    if(data.member_id==null)
       {
       alert('로그인이 필요합니다.');
       }
    else{
    	if(result=="success")
    		{
    		alert('장바구니에 추가되었습니다.');
    		location.href = "${pageContext.request.contextPath}/todaylesson/hm_us_bird";
    		}
    	else{
    		alert('이미 장바구니에 추가된 상품입니다.');
    	}
    		
    }
   
    } 
   ,error: function(){
      console.log(data);
      console.log('error');
     // alert('로그인이 필요합니다.');
      }
  }); 
  }
 });




</script>
   
</body>
</html>