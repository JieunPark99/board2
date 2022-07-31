package egovframework.wini.edu.service;

import java.util.List;
import java.util.Map;

public interface CmpUserMgrService {

	/* 회사 목록 조회 */
	List<Map<String, Object>> selectCmpList2(Map<String, Object> commandMap) throws Exception;
	
	/* 사원 목록 조회 */
	List<Map<String, Object>> selectEmpList(Map<String, Object> commandMap) throws Exception;
	
	/* 사원 정보 상세조회 */
	Map<String, Object> selectDetailEmp(Map<String, Object> commandMap) throws Exception;

	/* 사원 정보 추가 */
	void insertEmp(Map<String, Object> commandMap) throws Exception;

	/* 사원 정보 수정 */
	void updateEmp(Map<String, Object> commandMap) throws Exception;

	/* 사원 정보 삭제 */
	void deleteEmp(Map<String, Object> commandMap) throws Exception;

	/* 사원번호 중복 체크 */
	int CountEmpNo(Map<String, Object> commandMap) throws Exception;
	
}
