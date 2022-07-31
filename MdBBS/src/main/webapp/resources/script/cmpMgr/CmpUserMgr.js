let CheckOverlap = false;

$(function() {
	selectCmpList2();
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

// 사원목록의 특정 행 선택 시 해당 행의 배경색 바꿈.
function fn_EmpListOnclick(obj) {
    $("#empListTbody").children().removeClass("selectedOn");
    $(obj).addClass("selectedOn");
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 회사 목록 조회
function selectCmpList2(){
	
	//회사명 검색 데이터를 담기 위한 변수
	var search_comp_nm = $("#SEARCH_COMP_NM").val();

	//회사목록 삭제시 순서 불일치 문제 발생
	//ex) 4번 회사 삭제시 게시글 순서: 1 2 3 5 
	//이에 게시글 번호를 자동으로 순서대로 매칭시키기 위해 만든 변수
	var noticeCnt = 1;
	
	$.ajax({
		type: "GET",
		url: "./selectCmpList2.do",
		data: {"COMP_NM" : search_comp_nm},//컨트롤러로 넘길 매개변수를 담는다. (commandMap에 들어감)
		dataType: "json",
		error: function(request, status, error){ //ajax 자체에서 문제가 발생한 경우
			alert("조회중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
		},
		success: function(jdata){
			if(jdata.resultCode === 0){ //여기서 발생하는 오류는 대부분 쿼리, 컨트롤러, 서비스 문제다.
				alert("오류 발생!");
			}
			else if(jdata.resultCode === 1){//컨트롤러에서 제대로 값을 반환한 경우(성공)
				var resultList = jdata.resultList;
				var cmpList = "";
				
				//목록 새로고침 전에 테이블 내용을 모두 비운다.
				$("#cmpListTbody").empty();
				
				if(resultList.length > 0){
					for(var i=0; i<resultList.length; i++){
					//여기서 보내주는 this는 tr태그를 말함. tr을 보내니까 한 행에 해당하는 것들이 this로 넘어감.
						cmpList += '<tr style="text-align:center; cursor:pointer;" onclick="selectEmpList(this, \'' + resultList[i].COMP_SEQ + '\')">';
						cmpList += '<td>' + noticeCnt + '</td>';
						cmpList += '<td>' + resultList[i].COMP_NM + '</td>';
						cmpList += '<td>' + resultList[i].COMP_NO + '</td>';
						cmpList += '<td>' + resultList[i].USE_YN + '</td>';
						cmpList += '</tr>';
						
						noticeCnt = noticeCnt + 1;
						$("#SELECTED_OBJ").val(this);
					}
				}
				else{
					cmpList += '<tr>';
					cmpList += '<td colspan="4">조회된 결과가 없습니다.</td>';
					cmpList += '</tr>';
				}
				
				//테이블 내용을 다시 채운다. (목록 생성)
				$("#cmpListTbody").append(cmpList);
			}
		}
	});
}

// 검색조건에서 엔터 이벤트 처리
function CmpEnterEvent(){
	if(event.keyCode == 13){ //keyCode 13은 엔터다.
		selectCmpList2();
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//사원 목록 조회
function selectEmpList(obj, COMP_SEQ){

	var search_emp_nm = $("#SEARCH_EMP_NM").val();
	//var selected_obj = $("#SELECTED_OBJ").val(obj);
	//var selected_obj =obj;
	//사원 목록이 있는 경우와 없는 경우 버튼 디스플레이를 다르게 함.
	var btnMethod ='';
	
	//사원 목록의 번호를 매기기 위한 변수
	var noticeCnt = 1;
	
	//값이 없는지 체크
	if(COMP_SEQ === "" || COMP_SEQ === null || COMP_SEQ === undefined){
		COMP_SEQ = $("#HIDDEN_COMP_SEQ_EMP_LIST").val();
	}
	
	$.ajax({
		type: "POST",
		url: "./selectEmpList.do",
		data: {
		"COMP_SEQ" : COMP_SEQ,
		"USER_NM" : search_emp_nm
		},
		dataType: "json",
		error: function(request, status, error){
			alert("조회중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
		},
		success: function(jdata){
			if(jdata.resultCode === 0){
				alert("오류 발생!");
			}
			else if(jdata.resultCode === 1){
			//사원목록 조회 성공과 동시에 회사 목록에서 선택한 행의 배경색 바꿈.
				fn_CmpListOnclick(obj);
				
				var resultList = jdata.resultList;
				var empList = "";
				
				$("#empListTbody").empty();
				
				if(resultList.length > 0){
					for(var i=0; i<resultList.length; i++){
						empList += '<tr style="text-align:center; cursor:pointer;" onclick="selectDetailEmp(this, \'' + resultList[i].USER_NO +'\',\''+ resultList[i].COMP_SEQ+ '\')">';
						empList += '<td>' + noticeCnt + '</td>';
						empList += '<td>' + resultList[i].USER_NO + '</td>';
						empList += '<td>' + resultList[i].USER_NM + '</td>';
						empList += '<td>' + resultList[i].USER_TEL + '</td>';
						empList += '</tr>';
						
						noticeCnt = noticeCnt + 1;
					}
				}
				else{
					empList += '<tr>';
					empList += '<td colspan="4" style="text-align:center;">조회된 데이터가 없습니다.</td>';
					empList += '</tr>';
					
					//데이터가 없는 경우 등록 기능이 필요하므로
					btnMethod='zero';
				}
				
				//회사 목록으로부터 넘어온 COMP_SEQ값을 히든에 세팅해준다.
				//등록시 사용됨.
				$("#HIDDEN_COMP_SEQ").val(COMP_SEQ);
				//$("#SELECTED_OBJ").val(obj);
				//검색 시 사용될 comp_seq값을 담는다.
				$("#HIDDEN_COMP_SEQ_EMP_LIST").val(COMP_SEQ);
				
				$("#empListTbody").append(empList);
			}
			
			//이전에 보던 회사 목록 -> 사원정보관리 내용이 그대로 남아있는 것을 방지하기 위해
			//사원정보관리 내용을 초기화한다.
			resetDetail();
			
			// 사원 목록 조회시 데이터 유무에 따른 버튼 디스플레이 설정
			if(btnMethod === 'zero'){
				setDetailBtn("reset"); //데이터가 없는 경우 
			}else{
				setDetailBtn("setDetail"); //데이터가 있는 경우
			}
		}
	});
}

function EmpEnterEvent(){
	if(event.keyCode == 13){
		selectEmpList(COMP_SEQ);
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 사원 정보 상세조회
function selectDetailEmp(obj,USER_NO,COMP_SEQ){

	$.ajax({
		type: "POST",
		url: "./selectDetailEmp.do",
		data: {
		"USER_NO": USER_NO,
		"COMP_SEQ": COMP_SEQ},
		dataType: "json",
		error: function(request, status, error){
			alert("조회중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
		},
		success: function(jdata){
			if(jdata.resultCode === 0){
				alert("오류 발생!");
			}
		else if(jdata.resultCode === 1){
		
			//사원정보관리 조회 성공과 동시에 사원목록의 선택한 행 배경색 바꿈.
			fn_EmpListOnclick(obj);
			
			var detailMap = jdata.detailMap;
			
			//회사 목록-> 사원목록으로부터 넘어온 COMP_SEQ값을 히든에 세팅해준다.
			//등록시 사용됨.
			$("#HIDDEN_COMP_SEQ").val(detailMap.COMP_SEQ);
			
			//선택된 회사목록 행을 담는다. hidden값에 세팅
			//사원정보 등록,수정,삭제 시에도 회사목록 선택 상태가 사라지지 않도록 하기 위함.
			$("#SELECTED_OBJ").val(obj);
			
			//사원정보를 화면에 보여준다.
			$("#USER_NO").val(detailMap.USER_NO);
			$("#USER_NM").val(detailMap.USER_NM);
			$("#USER_TEL").val(detailMap.USER_TEL);
			$("#USER_EMAIL").val(detailMap.USER_EMAIL);
			
			//사원번호 수정시 필요한 원래 사원번호를 담는다.
			$("#OLD_USER_NO").val(detailMap.USER_NO);
			
			setDetailBtn("setDetail");
			}
		}
	});
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 초기화
function resetDetail(){
	$("#detailEmp").each(function(){
		this.reset();
		$("#showOverlap").text("");
		
		//사원목록에 선택되어 있던 행의 배경색을 원래대로 되돌림.
		$("#empListTbody").children().removeClass("selectedOn");
		
		//$("input[type=hidden]").val("");
		//등록,수정,삭제 시 해당 회사 번호가 필요하므로 여기서 초기화 시켜주면 안 됨!
		// 주의)hidden은 reset 시에도 초기화가 안되니까 강제로 초기화 시켜줘야 함.
	});
	
	setDetailBtn("reset");
	CheckOverlap = false;
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

// 사원 정보 추가
function insertEmp(){

	// 회사목록-> 사원목록-> 사원정보관리 로 넘어와서 남아있는 회사번호를 담는 변수
	// 해당 회사에 해당 사원을 추가해야 하므로 필요하다.
	var COMP_SEQ = $("#HIDDEN_COMP_SEQ").val();
	
	if($("#HIDDEN_COMP_SEQ").val() === ""){
		alert("사원을 등록할 회사를 먼저 선택해주세요");
		return;
	}
	if($("#USER_NO").val() === ""){
		alert("사원번호를 입력해주세요.");
		$("#USER_NO").focus();
		return;
	}
	if($("#USER_NM").val() === ""){
		alert("사원명을 입력해주세요.");
		$("#USER_NM").focus();
		return;
	}
	if($("#USER_TEL").val() === ""){
		alert("전화번호를 입력해주세요.");
		$("#USER_TEL").focus();
		return;
	}
	if($("#USER_EMAIL").val() === ""){
		alert("이메일을 입력해주세요.");
		$("#USER_EMAIL").focus();
		return;
	}
	
	if(CheckOverlap === false){
		alert("사원번호 중복체크를 해주세요.");
		$("#USER_NO").focus();
		return;
	}
	
	if(confirm("등록 하시겠습니까?")){
		$("#detailEmp").ajaxSubmit({
			type: "POST",
			url: "./insertEmp.do",
			data: {
			"COMP_SEQ": COMP_SEQ,
			"USER_NO" : $("#USER_NO").val()},
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
					selectEmpList($("#cmpListTbody > tr.selectedOn"),COMP_SEQ);//사원 목록을 다시 불러온다.
				}
				//사원번호 중복체크
				else if(jdata.resultCode === 2){
					alert("이미 등록된 사원번호 입니다.");
					resetDetail();
				}
			}
		});
	}
	else{
		return false;
	}
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 사원번호 중복체크
function fn_CheckOverlap(){
	var COMP_SEQ = $("#HIDDEN_COMP_SEQ").val();
	var USER_NO = $("#USER_NO").val();
	
	$.ajax({
		type: "POST",
		url: "./CheckOverlap.do",
		data: {
			"COMP_SEQ": COMP_SEQ,
			"USER_NO" : $("#USER_NO").val()},
		dataType: "json",
		error: function(request, status, error){
				alert("증복체크중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
			},
		success: function(jdata){
			if(jdata.resultCode === 0){
				alert("오류 발생!");
			}
			else if(jdata.resultCode === 1){
				if(jdata.CheckOverlap > 0){
					$("#showOverlap").css("color", "red");
					$("#showOverlap").text("이미 존재하는 사원번호입니다.");
					CheckOverlap = false;
				}else{
					$("#showOverlap").css("color", "blue");
					$("#showOverlap").text("사용가능한 사원번호입니다.");
					CheckOverlap = true;
				}
			}
		}
	});
}

//사원번호 입력 후 엔터치면 중복체크 기능 동작
function fn_CheckOverlap_EnterEvent(){
	if(event.keyCode == 13){
		fn_CheckOverlap();
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 사원 정보 수정
function updateEmp(){

	if($("#USER_NO").val() === ""){
		alert("사원번호를 입력해주세요.");
		$("#USER_NO").focus();
		return;
	}
	if($("#USER_NM").val() === ""){
		alert("사원명을 입력해주세요.");
		$("#USER_NM").focus();
		return;
	}
	if($("#USER_TEL").val() === ""){
		alert("전화번호를 입력해주세요.");
		$("#USER_TEL").focus();
		return;
	}
	if($("#USER_EMAIL").val() === ""){
		alert("이메일을 입력해주세요.");
		$("#USER_EMAIL").focus();
		return;
	}
	
	// 회사목록-> 사원목록-> 사원정보관리 로 넘어와서 남아있는 회사번호를 담는 변수
	// 해당 회사에 해당 사원을 수정해야 하므로 필요하다.
	var COMP_SEQ = $("#HIDDEN_COMP_SEQ").val();
	
	if(confirm("수정 하시겠습니까?")){
		$("#detailEmp").ajaxSubmit({
			type:"POST",
			url: "./updateEmp.do",
			data: {
			"COMP_SEQ" : $("#HIDDEN_COMP_SEQ").val(),
			"NEW_USER_NO" : $("#USER_NO").val(),
			"OLD_USER_NO" : $("#OLD_USER_NO").val()},//사원번호 수정시 이전 사원번호를 알고 있어야 하므로 저장.
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
					selectEmpList($("#cmpListTbody > tr.selectedOn"),COMP_SEQ);//사원 목록을 다시 불러온다.
					//사원 목록을 다시 불러온다.
				}
			}
		});
	}
	else{
		return false;
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 사원 정보 삭제
function deleteEmp(){

	// 회사목록-> 사원목록-> 사원정보관리 로 넘어와서 남아있는 회사번호를 담는 변수
	// 해당 회사에 해당 사원을 삭제해야 하므로 필요하다.
	var COMP_SEQ = $("#HIDDEN_COMP_SEQ").val();
	
	if(confirm("삭제 하시겠습니까?")){
		$.ajax({
			type: "POST",
			url: "./deleteEmp.do",
			data: {
			"USER_NO" : $("#USER_NO").val(),
			"COMP_SEQ" : $("#HIDDEN_COMP_SEQ").val()},
			dataType: "json",
			error: function(request, status, error){
				alert("삭제중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
			},
			success: function(jdata){
				if(jdata.resultCode === 0){
					alert("오류 발생!");
				}
				else if(jdata.resultCode === 1){
					alert("삭제 완료");
					
					//삭제 후 사원목록에서만 데이터가 지워지는 것이 아니라
					//직전까지 보이던 사원정보관리의 내용도 지워져야 하므로 초기화시킨다.
					resetDetail();
					
					//사원 목록을 다시 불러온다.
					selectEmpList($("#cmpListTbody > tr.selectedOn"),COMP_SEQ);//사원 목록을 다시 불러온다.
				}
			}
		});
	}
	else{
		return false;
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
