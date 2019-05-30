package com.liang.rent.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liang.rent.beans.RentA_BicycleStatus;
import com.liang.rent.service.IRentA_BicycleStatusService;

@RestController
@RequestMapping("/RentA_BicycleStatusCon")
public class RentA_BicycleStatusController {
	@Autowired
	private IRentA_BicycleStatusService rentA_BicycleStatusService;
	@Autowired	
	RedisTemplate<String, Object> template;
	
	@RequestMapping(path="/getAllRentA_BicycleStatus.do", produces="application/json;charset=utf-8")
	public List<RentA_BicycleStatus> getAllRentA_BicycleStatus() {
		List<RentA_BicycleStatus> rentA_BicycleStatusList = rentA_BicycleStatusService.getAllRentA_BicycleStatus();
		return rentA_BicycleStatusList;
	}
	
	@RequestMapping(path="/getRentA_BicycleStatusById.do")
	public RentA_BicycleStatus getRentA_BicycleStatusById(String rbs_bp_id) {
		return rentA_BicycleStatusService.getRentA_BicycleStatusById(rbs_bp_id);
	}
	
	@RequestMapping(path="/getRentA_BicycleStatusByCondition.do", produces="application/json;charset=utf-8")
	public List<RentA_BicycleStatus> getRentA_BicycleStatusByCondition(RentA_BicycleStatus rentA_BicycleStatus) {
		List<RentA_BicycleStatus> rentA_BicycleStatusList = rentA_BicycleStatusService.getRentA_BicycleStatusByCondition(rentA_BicycleStatus);
		return rentA_BicycleStatusList;
	}
	
	@RequestMapping(path="/getRentA_BicycleStatusByPage.do", produces="application/json;charset=utf-8")
	@CachePut("getRentA_BicycleStatusByPageCache")
	public List<RentA_BicycleStatus> getRentA_BicycleStatusByPage(int pageNum) {
		List<RentA_BicycleStatus> rentA_BicycleStatusList = rentA_BicycleStatusService.getRentA_BicycleStatusByPage(pageNum);
		return rentA_BicycleStatusList;
	}
	
	@RequestMapping(path="/addRentA_BicycleStatus.do")
	public boolean addRentA_BicycleStatus(RentA_BicycleStatus rentA_BicycleStatus) {
		return rentA_BicycleStatusService.addRentA_BicycleStatus(rentA_BicycleStatus);
	}
	
	@RequestMapping(path="/updateRentA_BicycleStatus.do")
	public void updateRentA_BicycleStatus(RentA_BicycleStatus rentA_BicycleStatus) {
		rentA_BicycleStatusService.updateRentA_BicycleStatus(rentA_BicycleStatus);
	}
	
	@RequestMapping(path="/transferRentA_BicycleStatus.do")
	public void transferRentA_BicycleStatus(String rbs_rp_id, String rbs_bp_id) {
		rentA_BicycleStatusService.transferRentA_BicycleStatus(rbs_rp_id, rbs_bp_id);
	}
}
