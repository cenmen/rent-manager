package com.liang.rent.mapper;

import org.apache.ibatis.annotations.Select;

import com.liang.rent.beans.Thority;

public interface ThorityMapper {
	@Select("select * from thority where id = #{id}")
	Thority getThor(Thority thority);
}
