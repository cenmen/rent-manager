package com.liang.rent.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.rent.beans.Information;
import com.liang.rent.beans.RentA_BicycleStatus;
import com.liang.rent.mapper.CommonMapper;
import com.liang.rent.mapper.RentA_BicycleStatusMapper;
import com.liang.rent.service.IRentA_BicycleStatusService;
import com.liang.rent.util.PageModel;

@Service
public class RentA_BicycleStatusServiceImpl implements IRentA_BicycleStatusService{
	@Autowired
	private RentA_BicycleStatusMapper rentA_BicycleStatusMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	public String getRp_id() {
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("rm_account");
		String rm_account = String.valueOf(val);
		String rp_id = rentA_BicycleStatusMapper.getRentManagerByAccountForRpId(rm_account);
		return rp_id;
	}
	
	@Override
	public List<RentA_BicycleStatus> getAllRentA_BicycleStatus() {
		return rentA_BicycleStatusMapper.getRentA_BicycleStatuss();
	}
	
	@Override
	public RentA_BicycleStatus getRentA_BicycleStatusById(String rbs_bp_id) {
		RentA_BicycleStatus rentA_BicycleStatus = new RentA_BicycleStatus();
		rentA_BicycleStatus.setRbs_bp_id(rbs_bp_id);
		return rentA_BicycleStatusMapper.getRentA_BicycleStatusById(rentA_BicycleStatus);
	}
	
	@Override
	public List<RentA_BicycleStatus> getRentA_BicycleStatusByCondition(RentA_BicycleStatus rentA_BicycleStatus) {
		String rp_id = getRp_id();
		rentA_BicycleStatus.setRbs_rp_id(rp_id);
		return rentA_BicycleStatusMapper.getRentA_BicycleStatusByCondition(rentA_BicycleStatus);
	}
	
	@Override
	public List<RentA_BicycleStatus> getRentA_BicycleStatusByPage(int pageNum) {
		String rp_id = getRp_id();
		//计算开始查询的位置
		int start = (pageNum - 1) * 12;
		PageModel pageModel =new PageModel();
		pageModel.setStart(start);
		pageModel.setPageSize(12);
		pageModel.setRbs_rp_id(rp_id);
		return rentA_BicycleStatusMapper.getRentA_BicycleStatusByPage(pageModel);
	}
	
	@Override
	public boolean addRentA_BicycleStatus(RentA_BicycleStatus rentA_BicycleStatus) {
		boolean isSuccess = true;
		String rp_id = getRp_id();
		rentA_BicycleStatus.setRbs_rp_id(rp_id);
		List<String> list_bp_id = rentA_BicycleStatusMapper.getRentA_BicycleStatusBpId();
		String rbs_bp_id = rentA_BicycleStatus.getRbs_bp_id();
		for (String str: list_bp_id) {
			if (str.equals(rbs_bp_id)) {
				isSuccess = false;
			}
		}
		
		if (isSuccess) {
			rentA_BicycleStatusMapper.addRentA_BicycleStatus(rentA_BicycleStatus);
		}
		
		return isSuccess;
	}
	
	@Override
	public void updateRentA_BicycleStatus(RentA_BicycleStatus rentA_BicycleStatus) {
		String rbs_bicycle_quality_status = rentA_BicycleStatus.getRbs_bicycle_quality_status();
		if (!rbs_bicycle_quality_status.equals("正常")) {
			//增加消息
			Information information = new Information();
			information.setInfo_from(getRp_id());
			information.setInfo_to("SYS");
			information.setInfo_type("车辆质量");
			String str = "租借点（"+ getRp_id() +"）车辆（" + rentA_BicycleStatus.getRbs_bp_id() + "）已（" + rentA_BicycleStatus.getRbs_bicycle_quality_status() + "）请系统处理";
			information.setInfo_content(str);
			System.out.println("information : " + information);
			commonMapper.addInformation(information);
		}
		rentA_BicycleStatus.setRbs_rp_id(getRp_id());
		rentA_BicycleStatusMapper.updateRentA_BicycleStatus(rentA_BicycleStatus);
	}
	
	@Override
	public void transferRentA_BicycleStatus(String rbs_rp_id, String rbs_bp_id) {
		RentA_BicycleStatus rentA_BicycleStatus = new RentA_BicycleStatus();
		rentA_BicycleStatus.setRbs_rp_id(rbs_rp_id);
		rentA_BicycleStatus.setRbs_bp_id(rbs_bp_id);
		rentA_BicycleStatus.setRbs_bicycle_hire_status(false);
		rentA_BicycleStatus.setRbs_bicycle_quality_status("正常");
		rentA_BicycleStatusMapper.updateRentA_BicycleStatus(rentA_BicycleStatus);
		
		String rp_id = getRp_id();
		//增加消息
		Information information = new Information();
		information.setInfo_from(rp_id);
		information.setInfo_to(rbs_rp_id);
		information.setInfo_type("车辆转移");
		String str = "车辆（" + rbs_bp_id + "）已转移到你的租借点（" + rbs_rp_id + "）";
		information.setInfo_content(str);
		System.out.println("information : " + information);
		commonMapper.addInformation(information);
	}
}
