// let cmpObj = {};

$(function() {
	selectCmpList();
});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 회사목록의 특정 행 선택 시 해당 행의 배경색 바꿈.
function fn_CmpListOnclick(obj) {// this값이 들어옴.
	//~.children(): ~아래 id에 해당하는 모든 태그들을 들고옴
	// ex) cmpListTbody에 존재하는 tr태그 6개를 들고옴.
	
    $("#cmpListTbody").children().removeClass("selectedOn");
    $(obj).addClass("selectedOn");
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*cmpObj.selectCmpList = funtion(){

}*/

// 회사 목록 조회
function selectCmpList(){
	var search_comp_nm = $("#SEARCH_COMP_NM").val();

	//회사목록 삭제시 순서 불일치 문제 발생
	//ex) 4번 회사 삭제시 게시글 순서: 1 2 3 5 
	//이에 게시글 번호를 자동으로 순서대로 매칭시키기 위해 만든 변수
	var noticeCnt = 1;
	
	$.ajax({
		type: "POST",
		url: "./selectCmpList.do",
		data: {"COMP_NM" : search_comp_nm},
		dataType: "json",
		error: function(request, status, error){
			alert("조회중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
		},
		success: function(jdata){
			if(jdata.resultCode === 0){
				alert("오류 발생!");
			}
			else if(jdata.resultCode === 1){
				var resultList = jdata.resultList;
				var cmpList = "";
				
				resetDetail();
				$("#cmpListTbody").empty();
				
				if(resultList.length > 0){
					for(var i=0; i<resultList.length; i++){
						cmpList += '<tr style="text-align:center; cursor:pointer;" onclick="selectDetailCmp(this, \'' + resultList[i].COMP_SEQ + '\')">';
						//cmpList += '<td>' + resultList[i].COMP_SEQ + '</td>';
						cmpList += '<td>' + noticeCnt + '</td>';
						cmpList += '<td>' + resultList[i].COMP_NM + '</td>';
						cmpList += '<td>' + resultList[i].COMP_NO + '</td>';
						cmpList += '<td>' + resultList[i].USE_YN + '</td>';
						cmpList += '</tr>';
						
						noticeCnt = noticeCnt + 1;
					}
				}
				else{
					cmpList += '<tr>';
					cmpList += '<td colspan="4">조회된 결과가 없습니다.</td>';
					cmpList += '</tr>';
				}
				
				$("#cmpListTbody").append(cmpList);
			}
		}
	});
}

// 검색조건에서 엔터 이벤트 처리
function enterEvent(){
	if(event.keyCode == 13){
		selectCmpList();
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 회사 정보 상세조회
function selectDetailCmp(obj, COMP_SEQ){
	$.ajax({
		type: "POST",
		url: "./selectDetailCmp.do",
		data: {"COMP_SEQ" : COMP_SEQ},
		dataType: "json",
		error: function(request, status, error){
			alert("조회중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
		},
		success: function(jdata){
			if(jdata.resultCode === 0){
				alert("오류 발생!");
			}
			else if(jdata.resultCode === 1){
				//회사정보 상세조회가 성공함과 동시에 선택한 회사목록 행의 배경색을 바꿈.
				fn_CmpListOnclick(obj)
				
				var detailMap = jdata.detailMap;
				
				$("#HIDDEN_COMP_SEQ").val(detailMap.COMP_SEQ);
				$("#COMP_NM").val(detailMap.COMP_NM);
				$("#COMP_NO").val(detailMap.COMP_NO);
				if(detailMap.USE_YN === "Y"){
					$("#USE_YN").prop("checked", true);
				}
				else{
					$("#USE_YN").prop("checked", false);
				}
				
				setDetailBtn("setDetail");
			}
		}
	});
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 초기화
function resetDetail(){
	$("#detailForm").each(function(){
		this.reset();
		$("#cmpListTbody").children().removeClass("selectedOn");
		$("input[type=hidden]").val("");
	});
	
	setDetailBtn("reset");
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 사원정보관리 하단 버튼 로직
function setDetailBtn(method){
     if(method == "setDetail"){
          $("#btn_register").css("display", "none");
          $("#btn_modify").css("display", "inline-block");
          $("#btn_delete").css("display", "inline-block");
     }
     else if(method == "reset"){
          $("#btn_register").css("display", "inline-block");
          $("#btn_modify").css("display", "none");
          $("#btn_delete").css("display", "none");
     }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 회사 정보 추가
function insertCmp(){
	if($("#COMP_NM").val() === ""){
		alert("회사명을 입력해주세요.");
		$("#COMP_NM").focus();
		return;
	}
	if($("#COMP_NO").val() === ""){
		alert("사업자번호를 입력해주세요.");
		$("#COMP_NO").focus();
		return;
	}
	
	if(confirm("등록 하시겠습니까?")){
		$("#detailForm").ajaxSubmit({
			type: "POST",
			url: "./insertCmp.do",
			dataType: "json",
			error: function(request, status, error){
				alert("등록중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
			},
			success: function(jdata){
				if(jdata.resultCode === 0){
					alert("오류 발생!");
				}
				else if(jdata.resultCode === 1){
					alert("등록 완료");
					resetDetail();// 회사정보관리 내용 초기화
					selectCmpList(); //회사목록 다시 불러옴.
				}
			}
		});
	}
	else {
		return false;
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 회사 정보 수정
function updateCmp(){

	if($("#COMP_NM").val() === ""){
		alert("회사명을 입력해주세요.");
		$("#COMP_NM").focus();
		return;
	}
	if($("#COMP_NO").val() === ""){
		alert("사업자번호를 입력해주세요.");
		$("#COMP_NO").focus();
		return;
	}
	
	if(confirm("수정 하시겠습니까?")){
		$("#detailForm").ajaxSubmit({
			type:"POST",
			url: "./updateCmp.do",
			data: {"COMP_SEQ" : $("#HIDDEN_COMP_SEQ").val()},
			dataType: "json",
			error: function(request, status, error){
				alert("수정중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
			},
			success: function(jdata){
				if(jdata.resultCode === 0){
					alert("오류 발생!");
				}
				else if(jdata.resultCode === 1){
					alert("수정 완료!");
					selectCmpList();
				}
			}
		});
	}
	else{
		return false;
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 회사 정보 삭제
function deleteCmp(){

	//사원 수 체크 
	//회사 삭제 시 사원이 존재하는 경우 삭제 불가 기능 구현을 위함.
	var CountEmp = $("#CountEmp").val();
	
	if(confirm("삭제 하시겠습니까?")){
		$.ajax({
			type: "POST",
			url: "./deleteCmp.do",
			data: {"COMP_SEQ" : $("#HIDDEN_COMP_SEQ").val()},
			dataType: "json",
			error: function(request, status, error){
				alert("삭제중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
			},
			success: function(jdata){
				if(jdata.resultCode === 0){
					alert("오류 발생!");
				}
				else if(jdata.resultCode === 1){
					//사원수를 담는다.
					CountEmp = jdata.CountEmp;
				
					if(CountEmp > 0){
						alert("해당 회사에 소속된 사원이 있습니다. \n사원 삭제 후 회사를 삭제할 수 있습니다.");
					}else{
						alert("삭제 완료");
						resetDetail();// 회사정보관리 내용 초기화
						selectCmpList();
					}
				}
			}
		});
	}
	else{
		return false;
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
