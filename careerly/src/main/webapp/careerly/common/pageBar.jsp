<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pagePath = request.getContextPath();
%>
<div id="pagelist">
	<c:if test="${page.totalPages gt 0}">
	<c:set scope="page" var="index" value="${(page.totalPages>page.showPages)?(page.showPages):(page.totalPages)}"/>
	<ul class="clearfix">
		<li><a href="javascript:{}" onclick="pageHandle(1,'${page.url}')">首页</a></li>
		<li><a href="javascript:{}"
			onclick="pageHandle(${page.previousPage},'${page.url}')">上页</a></li>
		<c:forEach var="item"
			begin="${page.currentIndex==1?page.currentPage:(page.currentIndex==index?(page.currentPage-index+1):(page.currentPage-page.currentIndex+1))}"
			end="${page.currentIndex==1?(page.currentPage+index-1):(page.currentIndex==index?page.currentPage:(page.currentPage+(index-page.currentIndex)))}">
			<c:if test="${item eq page.currentPage}">
				<li><a class="current" href="javascript:{}"
					onclick="pageHandle(${item},'${page.url}')"><c:out
							value="${item}" /></a></li>
			</c:if>
			<c:if test="${item ne page.currentPage}">
				<li><a href="javascript:{}"
					onclick="pageHandle(${item},'${page.url}')"><c:out
							value="${item}" /></a></li>
			</c:if>
		</c:forEach>
		<li><a href="javascript:{}"
			onclick="pageHandle(${page.nextPage},'${page.url}')">下页</a></li>
		<li><a href="javascript:{}"
			onclick="pageHandle(${page.totalPages},'${page.url}')">尾页</a></li>
		<li class="pageinfo">第<c:out value="${page.currentPage}" />页
		</li>
		<li class="pageinfo">共<c:out value="${page.totalPages}" />页
		</li>
	</ul>
	</c:if>
	<!-- form表单页面数据 -->
	<form id="pageForm" name="pageForm" action="#" method="post">
		<input type="hidden" id="currentPage" name="currentPage"
			value="${page.currentPage}" /> <input type="hidden" id="currentIndex"
			name="currentIndex" value="${page.currentIndex}" />
		<!-- 需要显示的页面个数-->
		<input type="hidden" id="showPages" name="showPages"
			value="${page.showPages}" />
	</form>
</div>
<script type="text/javascript">
	/***页面参数处理**/
	function pageHandle(currentPage,url)
	{
		setCurrentIndex(currentPage);
		var path ="<%=pagePath%>";
		var url =path+url+"?"+getSearchForm();
		$("#currentPage").attr("value",currentPage);
    	$("#pageForm").attr("action",url);
	    $("#pageForm").submit();
	}
	
	/***序列化搜索条件**/
	function getSearchForm()
	{
		return $("#form-search").serialize();
	}
	
	/**指向当前索引**/
	function setCurrentIndex(currentPage)
	{
		/***索引位置处理 start**/
		var preCurrentPage = $("#currentPage").val();//选择前的当前页面
		var preCurrentIndex =  $("#currentIndex").val();//选择前的当前索引
		var currentIndex = parseInt(preCurrentIndex)+parseInt(currentPage)-parseInt(preCurrentPage);
		var showPages =  parseInt($("#showPages").val());//显示页个数
		currentIndex = currentIndex>showPages?showPages:currentIndex;
		currentIndex = currentIndex<1?1:currentIndex;
		$("#currentIndex").attr("value",currentIndex);
		/***索引位置处理end**/
	}
</script>
