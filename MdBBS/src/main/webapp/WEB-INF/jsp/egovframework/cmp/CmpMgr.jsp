<%@ page session="false" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/egovframework/sample.css'/>"/>
	<script type="text/javascript" src="<c:out value='/resources/script/jquery/jquery-1.12.2.min.js'/>"/></script>
	<script type="text/javascript" src="<c:out value='/resources/script/jquery/jquery-ui.min.js'/>"/></script>
	<script type="text/javascript" src="<c:out value='/resources/script/jquery/jquery.form.js'/>"/></script>
	<title>회사정보관리</title>
</head>
<body>
<div id="grid1">
	<table>
		<thead>
			<tr>
				<td><h2>회사 목록</h2></td>
				<td colspan="3" style="text-align:right;"><p style="display:inline;">회사명</p>
				<input type="text" id="SEARCH_COMP_NM" size="20" onkeydown="enterEvent();">
				<button onclick="selectCmpList();">조회</button>
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>회사명</th>
				<th>사업자번호</th>
				<th>사용여부</th>
			</tr>
		</thead>
		<tbody id="cmpListTbody"></tbody>
	</table>
	<input type="hidden" id="CountEmp"/>
</div>
<div id="grid2">
	<form id="detailForm">
		<table>
			<tr>
				<td><h2>회사정보관리</h2></td>
			</tr>
			<tr>
				<th>회사명</th>
				<td><input type="text" id="COMP_NM" name="COMP_NM" size="30"></td>
				<td colspan="3"></td>
			</tr>
			<tr>
				<th>사업자번호</th>
				<td><input type="text" id="COMP_NO" name="COMP_NO" size="15"></td>
				<td colspan="4"></td>
			</tr>
			<tr>
				<th>사용여부</th>
				<td><input type="checkbox" id="USE_YN" name="USE_YN"></td>
				<td colspan="4"></td>
			</tr>
			<tr>
				<td colspan="2"></td>
				<td>
					<button type="button" id="btn_reset" class="button_function blueBtn" onclick="resetDetail();">초기화</button>
					<button type="button" id="btn_register" class="button_function blueBtn" onclick="insertCmp();">등록</button>
					<button type="button" id="btn_modify" class="button_function blueBtn" style="display:none;" onclick="updateCmp();">수정</button>
					<button type="button" id="btn_delete" class="button_function redBtn" style="display:none;" onclick="deleteCmp();">삭제</button>
				</td>
			</tr>
		</table>
		<input type="hidden" id="HIDDEN_COMP_SEQ" />
	</form>
</div>
<a href="/goToCmpUserList.do">2. 사원 정보 관리 페이지로 이동</a>
<script type="text/javascript" src="<c:out value='/resources/script/cmpMgr/CmpMgr.js'/>"/></script>
</body>
</html>
