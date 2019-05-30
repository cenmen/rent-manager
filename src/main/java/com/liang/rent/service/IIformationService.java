package com.liang.rent.service;

import java.util.List;

import com.liang.rent.beans.Information;

public interface IIformationService {
	
	List<Information> getInformations();
	
	void updateInformationStatus(int info_id);
}
