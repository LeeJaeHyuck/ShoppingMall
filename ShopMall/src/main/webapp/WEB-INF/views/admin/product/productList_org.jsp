<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>
<script>
	function selChange() {
		var sel = document.frm.sel.value;
		location.href="admin_product_list?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>
<article>
<h1>상품리스트</h1>	
<form name="frm" method="post">
<table>
  <tr>
  <td width="642">
      상품명 
     <input type="text" name="key">
     <input class="btn" type="button" name="btn_search" value="검색" onClick="go_search()">
     <input class="btn" type="button" name="btn_total" value="전체보기 " onClick="go_total()">
     <input class="btn" type="button" name="btn_write" value="상품등록" onClick="go_wrt()">
  </td>
  </tr>
</table>
<div>
	<div style="float: right; width: 19%;">
		<select id="cntPerPage" name="sel" onchange="selChange()">
			<option value="5"
				<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄보기</option>
			<option value="10"
				<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄보기</option>
			<option value="15"
				<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄보기</option>
		</select>
	</div>
</div>
<table id="productList">
    <tr>
        <th>번호</th><th>상품명</th><th>원가</th><th>판매가</th><th>등록일</th><th>사용유무</th>
    </tr>
    <c:choose>
    <c:when test="${productListSize<=0}">
    <tr>
      <td width="100%" colspan="7" align="center" height="23">
        등록된 상품이 없습니다.
      </td>      
    </tr>
    </c:when>
	<c:otherwise>
	<c:forEach items="${list}" var="productVO">
    <tr>
      <td height="23" align="center" >${productVO.pseq}</td>
      <td style="text-align: left; padding-left: 50px; padding-right: 0px;">   
        <a href="#" onClick="go_detail('${productVO.pseq}')">
    	 ${productVO.name}     
   		</a>
   	  </td>
      <td><fmt:formatNumber value="${productVO.price1}"/></td>
      <td><fmt:formatNumber value="${productVO.price2}"/></td>
      <td><fmt:formatDate value="${productVO.regdate}"/></td>
      <td>
      	<c:choose>
   	 		<c:when test='${productVO.useyn=="n"}'>미사용</c:when>
   	 		<c:otherwise>사용</c:otherwise>   	 		
   	 	</c:choose>	 
   	  </td> 
    </tr>
    </c:forEach>
    <tr><td colspan="6" style="text-align: center;">
    
    </td></tr>
	</c:otherwise>	
</c:choose>  
</table>
<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="admin_product_list?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="admin_product_list?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="admin_product_list?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
		</c:if>
	</div>
</form> 
</article>
<%@ include file="../footer.jsp"%>
</body>
</html>