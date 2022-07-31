package egovframework.wini.edu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.wini.edu.dao.CmpMgrDAO;
import egovframework.wini.edu.dao.CmpUserMgrDAO;
import egovframework.wini.edu.dao.OracleDAO;
import egovframework.wini.edu.service.CmpUserMgrService;

@Service("CmpUserMgrService")
public class CmpUserMgrServiceImpl extends EgovAbstractServiceImpl implements CmpUserMgrService{

	@Resource(name="CmpUserMgrDAO")
	private CmpUserMgrDAO cmpUserMgrDAO;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 목록 조회 */
	@Override
	public List<Map<String, Object>> selectCmpList2(Map<String, Object> commandMap) throws Exception {
		return cmpUserMgrDAO.selectCmpList2(commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/* 사원 목록 조회 */
	@Override
	public List<Map<String, Object>> selectEmpList(Map<String, Object> commandMap) throws Exception {
		return cmpUserMgrDAO.selectEmpList(commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 상세조회 */
	@Override
	public Map<String, Object> selectDetailEmp(Map<String, Object> commandMap) throws Exception {
		return cmpUserMgrDAO.selectDetailEmp(commandMap);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 추가 */
	@Override
	public void insertEmp(Map<String, Object> commandMap) throws Exception {
		cmpUserMgrDAO.insertEmp(commandMap);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 수정 */
	@Override
	public void updateEmp(Map<String, Object> commandMap) throws Exception {
		cmpUserMgrDAO.updateEmp(commandMap);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 삭제 */
	@Override
	public void deleteEmp(Map<String, Object> commandMap) throws Exception {
		cmpUserMgrDAO.deleteEmp(commandMap);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원번호 중복 체크 */
	@Override
	public int CountEmpNo(Map<String, Object> commandMap) throws Exception {
		return cmpUserMgrDAO.CountEmpNo(commandMap);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
