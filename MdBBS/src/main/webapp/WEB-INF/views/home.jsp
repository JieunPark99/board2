<%@ page session="false" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
	<title>회사정보관리</title>
</head>
<body>
<div id=grid1 style="width:800px;float:left;background-color:#f7e4ed;">
	<table style="width:800px;">
		<tr>
			<td><h2>회사 목록</h2></td>
			<td colspan="3" style="text-align:right;"><p style="display:inline;">회사명</p>
			<input type="text" name="search" size="20">
			<button>조회</button>
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>회사명</th>
			<th>사업자번호</th>
			<th>사용여부</th>
		</tr>
	</table>
</div>
<div id="grid2" style="width:700px;float:right;background-color:#f5eedc;">
	<table style="width:700px;">
		<tr>
			<td><h2>회사정보관리</h2></td>
		</tr>
		<tr>
			<th>회사명</th>
			<td><input type="text" name="company" size="30"></td>
			<td colspan="3"></td>
		</tr>
		<tr>
			<th>사업자번호</th>
			<td><input type="text" name="compNo" size="15"></td>
			<td colspan="4"></td>
		</tr>
		<tr>
			<th>사용여부</th>
			<td><input type="checkbox" name="useYn"></td>
			<td colspan="4"></td>
		</tr>
		<tr>
			<td colspan="2"></td>
			<td>
				<button id="blueBtn">초기화</button>
				<button id="blueBtn">등록</button>
				<button id="blueBtn">수정</button>
				<button style="background-color:red;color:white;font-weight:bold;">삭제</button>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
