package com.liang.rent.service;

import com.liang.rent.beans.Thority;

public interface IThorityService {
	//根据角色获取角色对应的权限
	Thority getThor(int id);
}
