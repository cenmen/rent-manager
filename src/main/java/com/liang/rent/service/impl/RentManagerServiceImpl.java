package com.liang.rent.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.rent.beans.RentManager;
import com.liang.rent.mapper.RentManagerMapper;
import com.liang.rent.service.IRentManagerService;
import com.liang.rent.util.PageModel;

@Service
public class RentManagerServiceImpl implements IRentManagerService {
	@Autowired
	private RentManagerMapper rentManagerMapper;
	
	@Override
	public List<RentManager> getAllRentManager() {
		return rentManagerMapper.getRentManagers();
	}
	
	@Override
	public RentManager getRentManagerById(String rm_id) {
		RentManager rentManager = new RentManager();
		rentManager.setRm_id(rm_id);
		return rentManagerMapper.getRentManagerById(rentManager);
	}
	
	@Override
	public RentManager getRentManagerByAccountForUpdate(String rm_account) {
		RentManager rentManager = new RentManager();
		rentManager.setRm_account(rm_account);
		return rentManagerMapper.getRentManagerByAccountForUpdate(rentManager);
	}
	
	@Override
	public RentManager getRentManagerByAccount(String rm_account , String rm_password) {
		RentManager rentManager = new RentManager();
		rentManager.setRm_account(rm_account);
		rentManager.setRm_password(rm_password);
		return rentManagerMapper.getRentManagerByAccount(rentManager);
	}

	@Override
	public List<RentManager> getRentManagerByCondition(RentManager rentManager) {
		return rentManagerMapper.getRentManagerByCondition(rentManager);
	}
	
	@Override
	public List<RentManager> getRentManagerByPage(int pageNum) {
		//计算开始查询的位置
		int start = (pageNum - 1) * 12;
		PageModel pageModel =new PageModel();
		pageModel.setStart(start);
		pageModel.setPageSize(12);
		return rentManagerMapper.getRentManagerByPage(pageModel);
	}
	
	@Override
	public boolean getRentManagerIsstatus(String rm_account) {
		return rentManagerMapper.getRentManagerIsstatus(rm_account);
	}
	
	@Override
	public void updateRentManager(RentManager rentManager) {
		Date date = new Date(System.currentTimeMillis());
		rentManager.setRm_last_time(date);
		rentManagerMapper.updateRentManager(rentManager);
	}
	
	@Override
	public Map updateRentManagerPwd(String rm_account , String original_pwd , String new_pwd_1 , String new_pwd_2) {
		Map map =new HashMap();
		RentManager rentManager = new RentManager();
		if(!new_pwd_1.equals(new_pwd_2)) {
			map.put("status", "500");
			return map;
		}
		RentManager rentManagerSetAccount = new RentManager();
		rentManagerSetAccount.setRm_account(rm_account);
		RentManager rentManagerForTestPwd = rentManagerMapper.getRentManagerByAccountForUpdate(rentManagerSetAccount);
		String pwd = rentManagerForTestPwd.getRm_password();
		System.out.println("pwd : "+pwd);
		if(original_pwd.equals(pwd)) {
			System.out.println("original_pwd is right");
			rentManager.setRm_account(rm_account);
			rentManager.setRm_password(new_pwd_2);
			rentManagerMapper.updateRentManagerPwd(rentManager);
			map.put("status", "200");
			return map;
		}else {
			map.put("status", "500");
			return map;
		}
	}
}
