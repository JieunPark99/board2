package egovframework.wini.edu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.wini.edu.dao.CmpMgrDAO;
import egovframework.wini.edu.service.CmpMgrService;

@Service("CmpMgrService")
public class CmpMgrServiceImpl extends EgovAbstractServiceImpl implements CmpMgrService{

	/* Logger log = Logger.getLogger(this.getClass()); */
	
	@Resource(name="CmpMgrDAO")
	private CmpMgrDAO cmpMgrDAO;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 목록 조회 */
	public List<Map<String, Object>> selectCmpList(Map<String, Object> commandMap) throws Exception{
		return cmpMgrDAO.selectCmpList(commandMap);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 정보 상세조회 */
	public Map<String, Object> selectDetailCmp(Map<String, Object> commandMap) throws Exception{
		return cmpMgrDAO.selectDetailCmp(commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/* 회사 정보 추가 */
	@Override
	public void insertCmp(Map<String, Object> commandMap) throws Exception {
		cmpMgrDAO.insertCmp(commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 정보 수정 */
	@Override
	public void updateCmp(Map<String, Object> commandMap) throws Exception {
		cmpMgrDAO.updateCmp(commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 정보 삭제 */
	@Override
	public void deleteCmp(Map<String, Object> commandMap) throws Exception {
		cmpMgrDAO.deleteCmp(commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 수 세기 */
	@Override
	public int CountEmp(Map<String, Object> commandMap) throws Exception {
		return cmpMgrDAO.CountEmp(commandMap);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}