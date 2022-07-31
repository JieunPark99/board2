package egovframework.wini.edu.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository("CmpMgrDAO")
public class CmpMgrDAO{
	protected static Log log = LogFactory.getLog(CmpMgrDAO.class);
	
	@Resource(name="oracleDAO")
	private OracleDAO oracleDAO;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 목록 조회 */
	public List<Map<String, Object>> selectCmpList(Map<String, Object> commandMap) throws Exception{
		return oracleDAO.selectList("CmpMgrDAO.selectCmpList", commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 정보 상세조회 */
	public Map<String, Object> selectDetailCmp(Map<String, Object> commandMap) throws Exception{
		return oracleDAO.selectOne("CmpMgrDAO.selectDetailCmp",commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 정보 추가 */
	public void insertCmp(Map<String, Object> commandMap) throws Exception {
		oracleDAO.insert("CmpMgrDAO.insertCmp",commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 정보 수정 */
	public void updateCmp(Map<String, Object> commandMap) throws Exception {
		oracleDAO.update("CmpMgrDAO.updateCmp",commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 정보 삭제 */
	public void deleteCmp(Map<String, Object> commandMap) throws Exception {
		oracleDAO.delete("CmpMgrDAO.deleteCmp",commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 수 세기 */
	public int CountEmp(Map<String, Object> commandMap) throws Exception {
		return oracleDAO.selectOne("CmpMgrDAO.CountEmp",commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
