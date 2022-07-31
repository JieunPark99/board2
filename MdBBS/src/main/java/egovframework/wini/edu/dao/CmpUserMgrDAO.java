package egovframework.wini.edu.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("CmpUserMgrDAO")
public class CmpUserMgrDAO{
	protected static Log log = LogFactory.getLog(CmpUserMgrDAO.class);
	
	@Resource(name="oracleDAO")
	private OracleDAO oracleDAO;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 목록 조회 */
	public List<Map<String, Object>> selectCmpList2(Map<String, Object> commandMap) throws Exception {
		return oracleDAO.selectList("CmpUserMgrDAO.selectCmpList2",commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 목록 조회 */
	public List<Map<String, Object>> selectEmpList(Map<String, Object> commandMap) throws Exception {
		return oracleDAO.selectList("CmpUserMgrDAO.selectEmpList",commandMap);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 상세조회 */
	public Map<String, Object> selectDetailEmp(Map<String, Object> commandMap) throws Exception {
		return oracleDAO.selectOne("CmpUserMgrDAO.selectDetailEmp",commandMap);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 추가 */
	public void insertEmp(Map<String, Object> commandMap) throws Exception {
		oracleDAO.insert("CmpUserMgrDAO.insertEmp",commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 수정 */
	public void updateEmp(Map<String, Object> commandMap) throws Exception {
		oracleDAO.update("CmpUserMgrDAO.updateEmp",commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 삭제 */
	public void deleteEmp(Map<String, Object> commandMap) throws Exception {
		oracleDAO.delete("CmpUserMgrDAO.deleteEmp",commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원번호 중복 체크 */
	public int CountEmpNo(Map<String, Object> commandMap) throws Exception {
		return oracleDAO.selectOne("CmpUserMgrDAO.CountEmpNo",commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
