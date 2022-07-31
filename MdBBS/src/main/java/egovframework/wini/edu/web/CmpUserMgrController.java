package egovframework.wini.edu.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.wini.edu.service.CmpUserMgrService;

@Controller
public class CmpUserMgrController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmpUserMgrController.class);
	
	@Resource(name="CmpUserMgrService")
	private CmpUserMgrService cmpUserMgrService;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 조회  페이지 시작 */
	@RequestMapping(value="/goToCmpUserList.do")
	public String goToCmpUserList(@RequestParam Map<String,Object> commandMap) throws Exception{
		return "cmp/CmpUserMgr";
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 목록 조회 */
	@RequestMapping(value="/selectCmpList2.do")
	@ResponseBody
	public Map<String,Object> selectCmpList2(@RequestParam Map<String,Object> commandMap) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		int resultCode = 0;
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		try {
			if(MapUtils.isEmpty(commandMap)) {//commandMap에 들어오는 값이 null인지 체크
				logger.info("selectCmpList2 ===> commandMap is Null");
			}else {//null이 아니라면 트랜잭션 실행
				resultList = cmpUserMgrService.selectCmpList2(commandMap);
				resultMap.put("resultList", resultList);
				resultCode = 1;
			}
		} catch(IllegalArgumentException iae) {
			logger.info("selectCmpList2.do ==> " + iae.getMessage());
		} catch(SQLException se) {
			logger.info("selectCmpList2.do ==> " + se.getMessage());
		} catch(Exception e) {
			logger.info("selectCmpList2.do ==> " + e.getMessage());
		} finally {
			resultMap.put("resultCode", resultCode);
		}
		return resultMap;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 목록 조회 */
	@RequestMapping(value="/selectEmpList.do")
	@ResponseBody
	public Map<String,Object> selectEmpList(@RequestParam Map<String,Object> commandMap) throws Exception{
		
		Map<String,Object> resultMap = new HashMap<>();
		int resultCode = 0;
		
		List<Map<String,Object>> resultList = new ArrayList<>();
		
		try {
			if(MapUtils.isEmpty(commandMap)) {
				logger.info("selectEmpList ===> commandMap is Null");
			}else {
				resultList = cmpUserMgrService.selectEmpList(commandMap);
				resultMap.put("resultList",resultList);
				resultCode = 1;
			}
		} catch(IllegalArgumentException iae) {
			logger.info("selectEmpList ===> " + iae.getMessage());
		} catch(SQLException se) {
			logger.info("selectEmpList ===> " + se.getMessage());
		} catch(Exception e) {
			logger.info("selectEmpList ===> " + e.getMessage());
		} finally {
			resultMap.put("resultCode",resultCode);
		}
		return resultMap;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/* 사원 정보 상세조회 */
	@RequestMapping(value="/selectDetailEmp.do",  method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectDetailEmp(@RequestParam Map<String,Object> commandMap) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		int resultCode = 0;
		Map<String,Object> detailMap = new HashMap<>();
		
		try {
			if(MapUtils.isEmpty(commandMap)) {
				logger.info("selectDetailEmp ===> commandMap is Null");
			}else {
				detailMap = cmpUserMgrService.selectDetailEmp(commandMap);
				resultMap.put("detailMap",detailMap);
				resultCode = 1;
			}
		} catch(IllegalArgumentException iae) {
			logger.info("selectDetailEmp ===> " + iae.getMessage());
		} catch(SQLException se) {
			logger.info("selectDetailEmp ===> " + se.getMessage());
		} catch(Exception e) {
			logger.info("selectDetailEmp.do ===>" + e.getMessage());
		}finally {
			resultMap.put("resultCode",resultCode);
		}
		return resultMap;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 추가 */
	@RequestMapping(value="/insertEmp.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> insertEmp(@RequestParam Map<String,Object> commandMap) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		int resultCode = 0;
		int CountEmpNo = 0;
		
		try {
			if(MapUtils.isEmpty(commandMap)) {
				logger.info("selectDetailEmp ===> commandMap is Null");
			}else {
				CountEmpNo = cmpUserMgrService.CountEmpNo(commandMap);//사원번호 중복체크
				if(CountEmpNo == 0){//중복이 아닌 경우 insert 실행
					cmpUserMgrService.insertEmp(commandMap);
					resultCode = 1;
				}else {
					resultCode = 2;
				}	
			}
		} catch(IllegalArgumentException iae) {
			logger.info("insertEmp ===> " + iae.getMessage());
		} catch(SQLException se) {
			logger.info("insertEmp ===> " + se.getMessage());
		} catch(Exception e) {
			logger.info("insertEmp.do ===> "+e.getMessage());
		}finally {
			resultMap.put("resultCode", resultCode);
			resultMap.put("CountEmpNo", CountEmpNo);
		}
		return resultMap;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원번호 중복체크 */
	@RequestMapping(value="/CheckOverlap.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> CheckOverlap(@RequestParam Map<String,Object> commandMap) {
		Map<String,Object> resultMap = new HashMap<>();
		int CheckOverlap = 0;
		int resultCode = 0;
		
		try {
			if(MapUtils.isEmpty(commandMap)) {
				logger.info("CheckOverlap ===> commandMap is Null");
			}else {
				CheckOverlap = cmpUserMgrService.CountEmpNo(commandMap);
				resultCode = 1;
			}
		} catch(IllegalArgumentException iae) {
			logger.info("CheckOverlap.do ===> " + iae.getMessage());
		} catch(SQLException se) {
			logger.info("CheckOverlap.do ===> " + se.getMessage());
		} catch(Exception e) {
			logger.info("CheckOverlap.do ===> " + e.getMessage());
		}finally {
			resultMap.put("resultCode", resultCode);
			resultMap.put("CheckOverlap", CheckOverlap);
		}
		return resultMap;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 수정 */
	@RequestMapping(value="/updateEmp.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateEmp(@RequestParam Map<String,Object> commandMap) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		int resultCode =0;
		
		try {
			if(MapUtils.isEmpty(commandMap)) {
				logger.info("updateEmp ===> commandMap is Null");
			}else {
				cmpUserMgrService.updateEmp(commandMap);
				resultCode = 1;
			}
		} catch(IllegalArgumentException iae) {
			logger.info("updateEmp.do ===> "+ iae.getMessage());
		} catch(SQLException se) {
			logger.info("updateEmp.do ===> "+ se.getMessage());
		} catch(Exception e) {
			logger.info("updateEmp.do ===> "+e.getMessage());
		}finally {
			resultMap.put("resultCode",resultCode);
		}
		return resultMap;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 사원 정보 삭제 */
	@RequestMapping(value="/deleteEmp.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteEmp(@RequestParam Map<String,Object> commandMap) throws Exception{
		Map<String,Object> resultMap=new HashMap<>();
		int resultCode=0;
		
		try {
			if(MapUtils.isEmpty(commandMap)) {
				logger.info("deleteEmp ===> commandMap is Null");
			}else {
				cmpUserMgrService.deleteEmp(commandMap);
				resultCode=1;
			}
		} catch(IllegalArgumentException iae) {
			logger.info("deleteEmp.do ===> "+ iae.getMessage());
		} catch(SQLException se) {
			logger.info("deleteEmp.do ===> "+ se.getMessage());
		} catch(Exception e){
			logger.info("deleteEmp.do ===> "+e.getMessage());
		}finally {
			resultMap.put("resultCode",resultCode);
		}
		return resultMap;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}