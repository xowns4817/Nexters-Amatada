package com.nexters.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nexters.project.dao.CarrierDao;
import com.nexters.project.dao.OptDao;
import com.nexters.project.dao.PackDao;
import com.nexters.project.dao.RecommendDao;
import com.nexters.project.dao.WeatherDao;
import com.nexters.project.dto.CarrierDto;
import com.nexters.project.dto.PackDto;
import com.nexters.project.dto.RecommendDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private SqlSession sqlSession;

	
	//캐리어 상세정보 ( 특정 캐리어 아이디에 대한 캐리어 리스트 ) ?cId =1 
	@RequestMapping(method=RequestMethod.GET, value="/carrier")
	@ResponseBody
	public Map<String, Object> CarrierList(HttpServletRequest request, Model model) {
		
		CarrierDao CarrierDao = sqlSession.getMapper(CarrierDao.class);
		OptDao OptDao = sqlSession.getMapper(OptDao.class);
		Map<String, Object> res  = new HashMap<String, Object>();
		Map<String, Object> res2  = new HashMap<String, Object>();
		Map<String, Object> res_carrier  = new HashMap<String, Object>();
		Map<String, Object> res_opt  = new HashMap<String, Object>();
		
		int cId = Integer.parseInt(request.getParameter("cId"));
		
		res_carrier.put("carrier", CarrierDao.listDao(cId));
		res_opt.put("opt", OptDao.listDao(cId));
		
		res.putAll(res_carrier);
		res.putAll(res_opt);
		
		res2.put("list", res);
		return res2;
	}
	
	//캐리어 등록하기 ( 이때 category에 해당하는 추천물품 캐리어에 넣어줌 )
	@RequestMapping(method=RequestMethod.POST, value="/carrier")
	@ResponseBody
	 public int CarrierInsert(@RequestHeader HttpHeaders headers, HttpServletRequest request, Model model) {
			
		System.out.println("해더 정보 : " + headers);
		CarrierDao CarrierDao = sqlSession.getMapper(CarrierDao.class);
		String[] category_list;
			
		OptDao OptDao = sqlSession.getMapper(OptDao.class);
			
		CarrierDto CarrierDto = new CarrierDto( );

		CarrierDto.setcName(request.getParameter("cName"));
		CarrierDto.setcCountry(Integer.parseInt(request.getParameter("cCountry")));
		CarrierDto.setStartDate(request.getParameter("startDate"));
			
		CarrierDao.writeDao(CarrierDto);
			
		System.out.println("Carrier dto Id : " + CarrierDto.getcId());
		int cId = CarrierDto.getcId(); // 디비에 현재 입력된 캐리어 id를 리턴
			
		category_list = request.getParameterValues("category_list");
			
		for(int i=0; i<category_list.length; i++) {
			OptDao.writeDao(cId, Integer.parseInt(category_list[i]));
		}
			
		RecommendDao RecommendDao = sqlSession.getMapper(RecommendDao.class);
		PackDao PackDao = sqlSession.getMapper(PackDao.class);
		ArrayList<RecommendDto> RecommendArrList = new ArrayList<RecommendDto>();
		
		for(int i=0; i<category_list.length; i++) {
			 RecommendArrList.addAll(RecommendDao.listDao(Integer.parseInt(category_list[i])));
		}
		
		//추천 준비물을 준비물 테이블에 넣고 Client에게 던져줌
		for(int i=0; i<RecommendArrList.size(); i++) {
			
			PackDto PackDto = new PackDto( );
			PackDto.setPcId(cId);
			PackDto.setpName(RecommendArrList.get(i).getrName());
			PackDto.setpColor("Gray"); // 추천 준비물은 "Gray"
			PackDto.setpCheck("N");
			PackDao.writeDao(PackDto);
		}
		
		return cId;
	}
	
	//캐리어 수정하기
	@RequestMapping(method=RequestMethod.PUT, value="/carrier")
	@ResponseBody
	public Integer CarrierUpdate(HttpServletRequest request, Model mode) {
		
		
		CarrierDao CarrierDao = sqlSession.getMapper(CarrierDao.class);
		
		int cId = Integer.parseInt(request.getParameter("cId"));
		String cName = request.getParameter("cName");
		int cCountry = Integer.parseInt(request.getParameter("cCountry"));
		String startDate = request.getParameter("startDate");
		
		CarrierDao.updateDao(cName, cCountry, startDate, cId);
		
		return cId;
	}

	
	//캐리어 삭제하기
	@RequestMapping(method=RequestMethod.DELETE, value="/carrier")
	@ResponseBody
	 public String CarrierDelete(HttpServletRequest request, Model model) {
			
		CarrierDao CarrierDao = sqlSession.getMapper(CarrierDao.class);
		CarrierDao.deleteDao(request.getParameter("cId"));
		return "Delete OK"; 
	}
	
	//특정 캐리어에 대한 모든 준비물 출력 (라벨순, 등록순 구분) ?cId=2&sort=1  (sort : 0 등록순, 1 라벨순 )
	@RequestMapping(method=RequestMethod.GET, value="/carrier/pack")
	@ResponseBody
	public Map<String, Object> PackageList(HttpServletRequest request, Model model) {
		
		PackDao Packdao = sqlSession.getMapper(PackDao.class);
		Map<String, Object> res  = new HashMap<String, Object>();
		Map<String, Object> res2  = new HashMap<String, Object>();
		Map<String, Object> map_check  = new HashMap<String, Object>();
		Map<String, Object> map_uncheck  = new HashMap<String, Object>();
	
		ArrayList<PackDto> list_y = new ArrayList<PackDto>();
		ArrayList<PackDto> list_n = new ArrayList<PackDto>();
		ArrayList<PackDto> result = new ArrayList<PackDto>();
		
		int cId = Integer.parseInt(request.getParameter("cId"));
		int param = Integer.parseInt(request.getParameter("sort"));
		String sort = new String();
		
		if(param == 0) sort = "pCreated";
		else if(param == 1) sort = "pColor";
	
		// mybatis #는 동적인 변수, $는 정적인 변수
		result = Packdao.listAllDao(cId, sort);
		
		for(int i=0; i<result.size(); i++) {
			if(result.get(i).getpCheck().equals("Y")) list_y.add(result.get(i));
			else list_n.add(result.get(i));
		}
	
		map_check.put("check", list_y);
		map_uncheck.put("uncheck", list_n);
		
		res.putAll(map_check);
		res.putAll(map_uncheck);
		
		res2.put("package", res);
		
		return res2;
	}
	
	//준비물 등록하기 ( 여러개의 추가된 준비들을 어떻게 디비에 넣지? - 쿼리문을 여러개 써줘야 되는건지?)
	@RequestMapping(method=RequestMethod.POST, value="/carrier/pack")
	@ResponseBody
	public String PackageInsert(HttpServletRequest request, Model model) {
		
		PackDao PackDao = sqlSession.getMapper(PackDao.class);
		
		PackDto PackDto = new PackDto( );
		PackDto.setPcId(Integer.parseInt(request.getParameter("pcId")));
		PackDto.setpName(request.getParameter("pName"));
		PackDto.setpColor(request.getParameter("pColor"));
		PackDto.setpCheck(request.getParameter("pCheck"));
		
		PackDao.writeDao(PackDto);
	
		System.out.println("Pack dto Id : " + PackDto.getpId());
		String id = Integer.toString(PackDto.getpId());
		
		return id;
	}
	
	//준비물 수정하기
	@RequestMapping(method=RequestMethod.PUT, value="/carrier/pack")
	@ResponseBody
	public int PackageUpdate(HttpServletRequest request, Model model) {
		
		PackDao PackDao = sqlSession.getMapper(PackDao.class);
		
		PackDto PackDto = new PackDto( );

		int pId = Integer.parseInt(request.getParameter("pId"));
		
		PackDto.setpId(pId);
		PackDto.setpName(request.getParameter("pName"));
		PackDto.setpColor(request.getParameter("pColor"));
		
		PackDao.updateDao(PackDto);
	
		return pId;
	}
	
	//준비물 삭제하기
	@RequestMapping(method=RequestMethod.DELETE, value="/carrier/pack")
	@ResponseBody
	public String PackageDelete(HttpServletRequest request, Model model) {
		
		PackDao PackDao = sqlSession.getMapper(PackDao.class);
		
		PackDao.deleteDao(request.getParameter("pId"));
		return "Delete OK"; 
	}
	
	//준비믈 체크하기 (체크하면 아래로 내려감 -> pCheck 변수만 set 해줌 ) -> 수정!
	@RequestMapping(method=RequestMethod.PUT, value="/carrier/pack/check")
	@ResponseBody
	 public PackDto PackageCheck(HttpServletRequest request, Model model) {
		
		PackDao PackDao = sqlSession.getMapper(PackDao.class);
		
		int pId = Integer.parseInt(request.getParameter("pId"));
		String pCheck = request.getParameter("pCheck");
		
		PackDao.checkDao(pId, pCheck);
		
		PackDto packDto = new PackDto( );
		packDto = PackDao.listDao(pId);
		
		return packDto;
		
	}
	
	//날씨 조회하기  Parmeter -> ( 지역, 월 )
	@RequestMapping(method=RequestMethod.GET, value="/weather")
	@ResponseBody
	 public Map<String, Object> WeatherList(HttpServletRequest request, Model model) {
		
		WeatherDao WeatherDao = sqlSession.getMapper(WeatherDao.class);
		Map<String, Object> res  = new HashMap<String, Object>();	
		
		res.put("weather", WeatherDao.listDao(Integer.parseInt(request.getParameter("city_id")), Integer.parseInt(request.getParameter("month"))));
		return res;
	}
}
