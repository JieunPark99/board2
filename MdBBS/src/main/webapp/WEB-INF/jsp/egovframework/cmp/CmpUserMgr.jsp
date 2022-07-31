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
	<title>사원정보관리</title>
</head>
<body>
<div>
	<table id="grid1">
		<thead>
			<tr>
				<td style="border-right:0;"><h2>회사 목록</h2></td>
				<td colspan="3" style="text-align:right;"><p style="display:inline;">회사명</p>
				<input type="text" id="SEARCH_COMP_NM" size="20" onkeydown="CmpEnterEvent();">
				<button onclick="selectCmpList2();">조회</button>
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
	<input type="hidden" id="SELECTED_OBJ"/>
</div>
<div>
	<form id="detailEmpList">
		<table  id="grid2">
			<thead>
				<tr>
					<td><h2>사원 목록</h2></td>
					<td colspan="3" style="text-align:right;"><p style="display:inline;">사원명</p>
					<input type="text" id="SEARCH_EMP_NM" size="20" onkeydown="EmpEnterEvent();">
					<!-- form 안의 버튼 태그는 type="button" 이라고 명시해주지 않으면 submit이 됨.
					submit이 된다는 것은 새로고침이 된다는 것인데 그렇게 되면 담고 있던 값들이 날아갈 수 있음. -->
					<button type="button" onclick="selectEmpList();">조회</button>
					</td>
				</tr>
				<tr>
					<th>번호</th>
					<th>사원번호</th>
					<th>사원명</th>
					<th>전화번호</th>
				</tr>
			</thead>
			<tbody id="empListTbody"></tbody>
		</table>
		<input type="hidden" id="HIDDEN_COMP_SEQ_EMP_LIST" />
	</form>
</div>
<form id="detailEmp">
	<table id="grid3">
		<thead>
			<tr>
				<td><h2>사원정보관리</h2></td>
			</tr>
		</thead>
		<tbody id="selectEmpTbody">
		<tr style="border-top:hidden;">
			<td colspan="4"><p style="text-align:right;color:red;"><b>*</b>는 필수입력항목입니다.</p></td>
		</tr>
		<tr>
			<th style="width:550px;">* 사원번호</th>
			<td style="width:400px;"><input type="text" id="USER_NO" name="USER_NO" size="15" onKeydown="fn_CheckOverlap_EnterEvent();" style="width:300px;">
				<!-- id랑 함수 이름이랑 같은 걸 써주면 함수를 못 찾음. 이름을 다르게 지정해줘야 제대로 인식한다. -->
				<button type="button" id="CheckOverlap" onclick="fn_CheckOverlap();">중복체크</button><td>
				<p id="showOverlap" style="width:250px;"></p>
			<td colspan="1"></td>
		</tr>
		<tr>
			<th>* 사원명</th>
			<td><input type="text" id="USER_NM" name="USER_NM" size="15" style="width:300px;"></td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<th>* 전화번호</th>
			<td><input type="text" id="USER_TEL" name="USER_TEL" size="15" style="width:300px;"></td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<th>* 이메일</th>
			<td><input type="text" id="USER_EMAIL" name="USER_EMAIL" size="30" style="width:300px;"></td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="3" style="width:300px;"></td>
			<td>
				<button type="button" id="btn_reset" class="button_function blueBtn" onclick="resetDetail();">초기화</button>
				<button type="button" id="btn_register" class="button_function blueBtn" onclick="insertEmp();">등록</button>
				<button type="button" id="btn_modify" class="button_function blueBtn" style="display:none;" onclick="updateEmp();">수정</button>
				<button type="button" id="btn_delete" class="button_function redBtn" style="display:none;" onclick="deleteEmp();">삭제</button>
			</td>
		</tr>
	</tbody>
</table>
<input type="hidden" id="HIDDEN_COMP_SEQ" />
<input type="hidden" id="OLD_USER_NO" />
</form>
<a href ="/start.do">1. 회사 정보 관리 페이지로 이동</a>
<script type="text/javascript" src="<c:out value='/resources/script/cmpMgr/CmpUserMgr.js'/>"/></script>
</body>
</html>