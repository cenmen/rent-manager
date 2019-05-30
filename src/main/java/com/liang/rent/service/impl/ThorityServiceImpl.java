package com.liang.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.rent.beans.Thority;
import com.liang.rent.mapper.ThorityMapper;
import com.liang.rent.service.IThorityService;


@Service
public class ThorityServiceImpl implements IThorityService{
	
	@Autowired
	private ThorityMapper thorityMapper;
	
	@Override
	public Thority getThor(int id) {
		Thority thority = new Thority();
		thority.setId(id);
		return thorityMapper.getThor(thority);
	}
}
