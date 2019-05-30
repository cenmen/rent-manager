package com.liang.rent.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.rent.beans.Information;
import com.liang.rent.mapper.CommonMapper;
import com.liang.rent.service.IIformationService;

@Service
public class InformationServiceImpl  implements IIformationService{

	@Autowired
	private CommonMapper commonMapper;
	
	public String getRpId() {
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("rm_account");
		String rm_account = String.valueOf(val);
		String rp_id = commonMapper.getRp_idByAccount(rm_account);
		return rp_id;
	}

	@Override
	public List<Information> getInformations() {
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("rm_account");
		String rm_account = String.valueOf(val);
		String rp_id = commonMapper.getRp_idByAccount(rm_account);
		return commonMapper.getAllInformationByInfoTo(rp_id);
	}
	
	@Override
	public void updateInformationStatus(int info_id) {
		Information info = commonMapper.getInformationById(info_id);
		if ("车辆调度".equals(info.getInfo_type())) {
			String rp_id = getRpId();
			System.out.println("rp_id by method : " + rp_id);
			Information information = new Information();
			information.setInfo_from(rp_id);
			information.setInfo_to("SYS");
			information.setInfo_type("车辆调度");
			Date today = new Date( );
		    SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
			information.setInfo_content("租借点" + rp_id + "已完成调度（"  + format.format(today) + "）");
			System.out.println("information : " + information);
			commonMapper.addInformation(information);
		}
		commonMapper.updateInformationStatus(info_id);
	}
}
