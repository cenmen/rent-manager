package com.liang.rent.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.rent.beans.Information;
import com.liang.rent.beans.RentA_BicycleManager;
import com.liang.rent.mapper.CommonMapper;
import com.liang.rent.mapper.RentA_BicycleManagerMapper;
import com.liang.rent.service.IRentA_BicycleManagerService;
import com.liang.rent.util.PageModel;

@Service
public class RentA_BicycleManagerServiceImpl implements IRentA_BicycleManagerService{
	
	@Autowired
	private RentA_BicycleManagerMapper rentA_BicycleManagerMapper;
	@Autowired
	private CommonMapper commonMapper;

	@Override
	public List<RentA_BicycleManager> getAllRentA_BicycleManager() {
		return rentA_BicycleManagerMapper.getRentA_BicycleManagers();
	}
	
	@Override
	public RentA_BicycleManager getRentA_BicycleManagerById(int rbm_id) {
		RentA_BicycleManager rentA_BicycleManager = rentA_BicycleManagerMapper.getRentA_BicycleManagerById(rbm_id);
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("rm_account");
		String rm_account = String.valueOf(val);
		String rp_id = rentA_BicycleManagerMapper.getRentManagerByAccountForRpId(rm_account);
		rentA_BicycleManager.setRbm_persent_location(rp_id);
		return rentA_BicycleManager;
	}
	
	@Override
	public List<RentA_BicycleManager> getRentA_BicycleManagerByCondition(RentA_BicycleManager rentA_BicycleManager) {
		return rentA_BicycleManagerMapper.getRentA_BicycleManagerByCondition(rentA_BicycleManager);
	}
	
	@Override
	public List<RentA_BicycleManager> getRentA_BicycleManagerByPage(int pageNum) {
		//计算开始查询的位置
		int start = (pageNum - 1) * 12;
		PageModel pageModel =new PageModel();
		pageModel.setStart(start);
		pageModel.setPageSize(12);
		return rentA_BicycleManagerMapper.getRentA_BicycleManagerByPage(pageModel);
	}
	
	@Override
	public boolean addRentA_BicycleManager(RentA_BicycleManager rentA_BicycleManager) {
		boolean isPass = false;
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("rm_account");
		String rm_account = String.valueOf(val);
		String rp_id = rentA_BicycleManagerMapper.getRentManagerByAccountForRpId(rm_account);
		
		List<String> bicycle = rentA_BicycleManagerMapper.getRentManagerByAccountForRpBicycle(rp_id);
		String addBicycleID = rentA_BicycleManager.getRbm_bp_id();
		for (String str : bicycle) {
			if (str.equals(addBicycleID)) {
				//如果车辆不被租借则可以添加记录
				if (!rentA_BicycleManagerMapper.getRentManagerByBp_idForIsStatus(str)) {
					isPass = true;
				}
			}
		}
		
		if (isPass) {
			rentA_BicycleManager.setRbm_hire_location(rp_id);
		
			int rbm_id = rentA_BicycleManagerMapper.getRentA_BicycleManagerLast();
			rentA_BicycleManager.setRbm_id(rbm_id + 1);
			
			Timestamp time = new Timestamp(System.currentTimeMillis());  
			rentA_BicycleManager.setRbm_hire_time(time);
			rentA_BicycleManagerMapper.addRentA_BicycleManager(rentA_BicycleManager);
		}
		
		return isPass;
	}
	
	@Override
	public void updateRentA_BicycleManager(RentA_BicycleManager rentA_BicycleManager) {
		Timestamp time = new Timestamp(System.currentTimeMillis());  
		rentA_BicycleManager.setRbm_retrieve_time(time);
		rentA_BicycleManagerMapper.updateRentA_BicycleManager(rentA_BicycleManager);
		//增加消息
		RentA_BicycleManager bicycle = rentA_BicycleManagerMapper.getRentA_BicycleManagerById(rentA_BicycleManager.getRbm_id());
		//如果归还车辆时质量损坏，则更新租借点的车辆质量状态
		if (rentA_BicycleManager.isRbm_istrouble()) {
			rentA_BicycleManagerMapper.updateRentA_BicycleStatusForIsTrouble(bicycle.getRbm_bp_id());
		}
		if (!bicycle.getRbm_hire_location().equals(bicycle.getRbm_persent_location())) {
			Information information = new Information();
			information.setInfo_from(rentA_BicycleManager.getRbm_persent_location());
			information.setInfo_to(bicycle.getRbm_hire_location());
			information.setInfo_type("车辆租借");
			String str = "你的租借点（"+bicycle.getRbm_hire_location()+"）的（"+bicycle.getRbm_bp_id()+"）车辆已在我的租借点（" + rentA_BicycleManager.getRbm_persent_location() + "）归还";
			information.setInfo_content(str);
			System.out.println("information : " + information);
			commonMapper.addInformation(information);
		}
	}
	
	@Override
	public void deleteRentA_BicycleManager(int rbm_id) {
		RentA_BicycleManager rentA_BicycleManager = new RentA_BicycleManager();
		rentA_BicycleManager.setRbm_id(rbm_id);
		rentA_BicycleManagerMapper.deleteRentA_BicycleManager(rentA_BicycleManager);
	}
}
