package egovframework.wini.edu.service;

import java.util.List;
import java.util.Map;

public interface CmpMgrService {

	/* 회사 목록 조회 */
	List<Map<String, Object>> selectCmpList(Map<String, Object> commandMap) throws Exception;
	
	/* 회사 정보 상세조회 */
	Map<String, Object> selectDetailCmp(Map<String, Object> commandMap) throws Exception;

	/* 회사 정보 추가 */
	void insertCmp(Map<String, Object> commandMap) throws Exception;

	/* 회사 정보 수정 */
	void deleteCmp(Map<String, Object> commandMap) throws Exception;

	/* 회사 정보 삭제 */
	void updateCmp(Map<String, Object> commandMap) throws Exception;

	/* 사원 수 세기 */
	int CountEmp(Map<String, Object> commandMap) throws Exception;

}
