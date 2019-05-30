package com.liang.rent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liang.rent.beans.Information;
import com.liang.rent.service.IIformationService;

@RestController
@RequestMapping("/InformationCon")
public class InformationController {
	
	@Autowired
	private IIformationService informationService;
	
	@RequestMapping(path="/getInformations.do")
	public List<Information> getInformations() {
		return informationService.getInformations();
	}
	
	@RequestMapping(path="/updateInformationStatus.do")
	public void updateInformationStatus(int info_id) {
		informationService.updateInformationStatus(info_id);
	}
}
