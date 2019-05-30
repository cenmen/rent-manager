package com.liang.rent.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liang.rent.beans.RentA_BicycleManager;
import com.liang.rent.service.IRentA_BicycleManagerService;


@RestController
@RequestMapping("/RentA_BicycleManagerCon")
public class RentA_BicycleManagerController {
	@Autowired
	private IRentA_BicycleManagerService rentA_BicycleManagerService;
	@Autowired	
	RedisTemplate<String, Object> template;
	
	@RequestMapping(path="/getAllRentA_BicycleManager.do", produces="application/json;charset=utf-8")
	public List<RentA_BicycleManager> getAllRentA_BicycleManager() {
		List<RentA_BicycleManager> rentA_BicycleManagerList = rentA_BicycleManagerService.getAllRentA_BicycleManager();
		return rentA_BicycleManagerList;
	}
	
	@RequestMapping(path="/getRentA_BicycleManagerById.do")
	public RentA_BicycleManager getRentA_BicycleManagerById(int rbm_id) {
		return rentA_BicycleManagerService.getRentA_BicycleManagerById(rbm_id);
	}
	
	@RequestMapping(path="/getRentA_BicycleManagerByCondition.do", produces="application/json;charset=utf-8")
	public List<RentA_BicycleManager> getRentA_BicycleManagerByCondition(RentA_BicycleManager rentA_BicycleManager) {
		System.out.println("getRentA_BicycleManagerByCondition : " + rentA_BicycleManager);
		List<RentA_BicycleManager> rentA_BicycleManagerList = rentA_BicycleManagerService.getRentA_BicycleManagerByCondition(rentA_BicycleManager);
		return rentA_BicycleManagerList;
	}
	
	@RequestMapping(path="/getRentA_BicycleManagerByPage.do", produces="application/json;charset=utf-8")
	@CachePut("getRentA_BicycleManagerByPageCache")
	public List<RentA_BicycleManager> getRentA_BicycleManagerByPage(int pageNum) {
		List<RentA_BicycleManager> rentA_BicycleManagerList = rentA_BicycleManagerService.getRentA_BicycleManagerByPage(pageNum);
		return rentA_BicycleManagerList;
	}
	
	@RequestMapping(path="/addRentA_BicycleManager.do")
	public boolean addRentA_BicycleManager(RentA_BicycleManager rentA_BicycleManager) {
		return rentA_BicycleManagerService.addRentA_BicycleManager(rentA_BicycleManager);
	}
	
	@RequestMapping(path="/updateRentA_BicycleManager.do")
	public void updateRentA_BicycleManager(RentA_BicycleManager rentA_BicycleManager) {
		rentA_BicycleManagerService.updateRentA_BicycleManager(rentA_BicycleManager);
	}
	
	@RequestMapping(path="/deleteRentA_BicycleManager.do")
	public void deleteRentA_BicycleManager(int rbm_id) {
		rentA_BicycleManagerService.deleteRentA_BicycleManager(rbm_id);
	}
}