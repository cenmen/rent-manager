package com.liang.rent.service;

import java.util.List;
import java.util.Map;

import com.liang.rent.beans.RentManager;

public interface IRentManagerService {
	
	List<RentManager> getAllRentManager();
	
	RentManager getRentManagerById(String rm_id);
	
	RentManager getRentManagerByAccountForUpdate(String rm_account);
	
	RentManager getRentManagerByAccount(String rm_account , String rm_password);
	
	List<RentManager> getRentManagerByCondition(RentManager rentManager);
	
	List<RentManager> getRentManagerByPage(int pageNum);
	
	void updateRentManager(RentManager rentManager);
	
	boolean getRentManagerIsstatus(String rm_account);
	
	Map updateRentManagerPwd(String rm_account , String original_pwd , String new_pwd_1 , String new_pwd_2);
	
}
