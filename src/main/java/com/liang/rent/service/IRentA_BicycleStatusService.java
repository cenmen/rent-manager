package com.liang.rent.service;

import java.util.List;

import com.liang.rent.beans.RentA_BicycleStatus;

public interface IRentA_BicycleStatusService {
	
	List<RentA_BicycleStatus> getAllRentA_BicycleStatus();
	
	RentA_BicycleStatus getRentA_BicycleStatusById(String rbs_bp_id);
	
	List<RentA_BicycleStatus> getRentA_BicycleStatusByCondition(RentA_BicycleStatus rentA_BicycleStatus);
	
	List<RentA_BicycleStatus> getRentA_BicycleStatusByPage(int pageNum);
	
	boolean addRentA_BicycleStatus(RentA_BicycleStatus rentA_BicycleStatus);
	
	void updateRentA_BicycleStatus(RentA_BicycleStatus rentA_BicycleStatus);
	
	void transferRentA_BicycleStatus(String rbs_rp_id, String rbs_bp_id);
}
