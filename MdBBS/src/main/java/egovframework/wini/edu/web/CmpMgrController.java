package egovframework.wini.edu.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.wini.edu.service.CmpMgrService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CmpMgrController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmpMgrController.class);
	
	@Resource(name="CmpMgrService")
	private CmpMgrService cmpMgrService;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/start.do")
	public String boardList(@RequestParam Map<String,Object> commandMap) throws Exception{
		return "cmp/CmpMgr";
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 목록 조회 */
	@RequestMapping(value="/selectCmpList.do")
	@ResponseBody
	public Map<String,Object> selectCmpList(@RequestParam Map<String,Object> commandMap) throws Exception{
		//여기에 적힌 commandMap이란건 쿼리문에서 넣는 매개변수를 담음.
	
		//resultList들을 키로 받아서 결과를 반환할 resultMap 선언
		Map<String,Object> resultMap = new HashMap<>();
		
		//ajax에 보낼 오류검증용 코드
		//만약 resultCode=0이면 컨트롤러내에서 문제인 것
		int resultCode = 0;
		
		//결과를 담을 resultList 선언
		//commandMap에 담아서 서비스를 거친 결과물들을 담는다.
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		try {
			//(실행할 쿼리문 이름, 매개변수) 이렇게 넣어서 서비스에 보내고 일련의 과정을 거친 뒤 나오는 결과물을 resultList에 담는다.
			resultList = cmpMgrService.selectCmpList(commandMap);
			resultMap.put("resultList", resultList);
			resultCode = 1;
		} catch(Exception e) {
			logger.info("selectCmpList.do ==> " + e.getMessage());
		} finally {
			resultMap.put("resultCode", resultCode);
		}
		
		return resultMap;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 정보 상세조회 */
	@RequestMapping(value="/selectDetailCmp.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectDetailCmp(@RequestParam Map<String,Object> commandMap) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		int resultCode = 0;
		Map<String,Object> detailMap = new HashMap<>();
		
		try {
			detailMap = cmpMgrService.selectDetailCmp(commandMap);
			resultMap.put("detailMap", detailMap);
			resultCode = 1;
		}catch(Exception e) {
			logger.info("selectDetailCmp.do ===> "+e.getMessage());
		}finally {
			resultMap.put("resultCode", resultCode);
		}
		
		return resultMap;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 정보 추가 */
	@RequestMapping(value="/insertCmp.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> insertCmp(@RequestParam Map<String,Object> commandMap) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		int resultCode = 0;
		
		try {
			cmpMgrService.insertCmp(commandMap);
			resultCode = 1;
		}catch(Exception e) {
			logger.info("insertCmp.do ===> "+e.getMessage());
		}finally {
			resultMap.put("resultCode", resultCode);
		}
		
		return resultMap;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 회사 정보 수정 */
	@RequestMapping(value="/updateCmp.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateCmp(@RequestParam Map<String,Object> commandMap) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		int resultCode =0;
		
		try {
			cmpMgrService.updateCmp(commandMap);
			resultCode = 1;
		}catch(Exception e) {
			logger.info("updateCmp.do ===> "+e.getMessage());
		}finally {
			resultMap.put("resultCode",resultCode);
		}
		return resultMap;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/* 회사 정보 삭제 */
	@RequestMapping(value="/deleteCmp.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteCmp(@RequestParam Map<String,Object> commandMap) throws Exception{
		Map<String,Object> resultMap=new HashMap<>();
		int resultCode=0;
		int CountEmp=0;
		
		try {
			//회사에 소속된 사원이 있는 경우 삭제 방지
			CountEmp = cmpMgrService.CountEmp(commandMap);
			
			//회사에 사원이 없는 경우에만 삭제 트랜잭션 실행
			 if(CountEmp == 0) {
				 cmpMgrService.deleteCmp(commandMap); 
			 }
			resultCode=1;
		}catch(Exception e){
			logger.info("deleteCmp.do ===> "+e.getMessage());
		}finally {
			resultMap.put("CountEmp", CountEmp);
			resultMap.put("resultCode",resultCode);
		}
		return resultMap;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
