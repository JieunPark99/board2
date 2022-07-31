# board2
마스터디테일구조 게시판 만들기 (전자정부프레임워크) , 2차 과제


일시 : 2022년 3월


학습목표
1. 마스터/디테일 업무 숙지
2. AJAX 기반 통신 숙지

# 테이블 스키마
tcom_comp(회사정보관리)
- comp_seq : 회사 일련번호 (key, not null)
- comp_nm : 회사명 (not null)
- comp_no : 사업자번호 (not null)
- use_yn : 사용여부
- reg_dt : 등록일시
- mod_dt : 수정일시

tcom_comp_emp(사원정보관리)
- comp_seq : 회사 일련번호 (key, not null)
- user_no : 사번 (key, not null)
- user_nm : 사원명 (not null)
- user_tel : 전화번호
- user_email : 이메일
- reg_dt : 등록일시
- mod_dt : 수정일시

# 1. 회사정보관리
[화면설명]
1. 회사목록
  - 등록된 회사 목록을 조회한다.
  - 회사를 선택하면 회사정보관리 영역에 선택한 회사의 정보를 보여준다.
2. 초기화
  - 회사정보관리 영역의 입력항목을 초기 입력상태로 되돌린다.
3. 등록
  - 입력한 정보를 등록한다.
4. 수정
  - 선택된 정보를 수정한다.
5. 삭제
  - 선택된 정보를 삭제한다.
  - 삭제 시 해당 회사에 등록된 사원이 있는 경우 ‘해당 회사에 소속된 사원이 있습니다. 
    사원 삭제 후 회사를 삭제할 수 있습니다.’ 알림 후 삭제 금지
   
[파일정보]
1. 패키지명
  - egovframework.wini.edu
2. JAVA 파일명
  - CmpMgrController
  - CmpMgrService
  - CmpMgrServiceImpl
  - CmpMgrDAO
3. Mapper 파일명
  - CmpMgrDAO_SQL.xml
4. JSP 파일명
  - 위치 : egovframework\cmp
  - 파일명 : CmpMgr.jsp


# 2. 사원정보관리
[화면설명]
1. 회사목록
  - 등록된 회사 목록을 조회한다.
  - 회사 선택 시 선택된 회사에 소속된 사원 목록을 조회한다.
2. 사원목록
  - 선택된 회사의 사원 목록을 조회한다.
  - 선택된 회사가 없는 경우 ‘조회된 데이터가 없습니다.’ 로 보여준다.
  - 사원 선택 시 선택된 사원의 정보를 사원정보관리 영역에 보여준다.
2. 초기화
  - 회사정보관리 영역의 입력항목을 초기 입력상태로 되돌린다.
3. 등록
  - 입력한 정보를 등록한다.
  - 등록 시 사원번호는 중복체크 하여 중복된 경우 ‘이미 등록된 사원번호 입니다.’ 알림
4. 수정
  - 선택된 정보를 수정한다.
5. 삭제
  - 선택된 정보를 삭제한다.
  
[파일정보]
1. 패키지명
  - egovframework.wini.edu
2. JAVA 파일명
  - CmpUserMgrController
  - CmpUserMgrService
  - CmpUserMgrServiceImpl
  - CmpUserMgrDAO
3. Mapper 파일명
  - CmpUserMgrDAO_SQL.xml
4. JSP 파일명
  - 위치 : egovframework\cmp
  - 파일명 : CmpUserMgr.jsp
