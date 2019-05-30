package com.liang.rent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.rent.beans.Information;


public interface CommonMapper {
	
	//查询所有租借点ID
	@Select("select rp_id from rent_place")
	List<String> getAllRp_idFromRP();
	
	//根据账号查询租借点ID
	@Select("select rm_rp_id from rent_manager where rm_account = #{rm_account}")
	String getRp_idByAccount(String rm_account);
	
	//查询所有车辆ID
	@Select("select bp_id from bicycle_purchase")
	List<String> getAllBp_idFromBP();
	
	//获取最后一条租借点ID
	@Select("select rp_id from rent_place order by rp_id DESC limit 1")
	String getLastRp_idFromRP();
	
	//查询所有消息
	@Select("select * from information")
	List<Information> getAllInformation();
	
	//根据消息ID查询消息
	@Select("select * from information where info_id = #{info_id}")
	Information getInformationById(int info_id);
	
	//查询对应角色账号和未读的消息
	@Select("select * from information where info_to = #{info_to} and info_status = false")
	List<Information> getAllInformationByInfoTo(String info_to);
	
	//增加消息
	@Insert("insert into information values(#{info_id}, #{info_from}, #{info_to}, #{info_type}, #{info_content}, #{info_status})")
	void addInformation(Information information);
	
	//修改消息状态
	@Update("update information set info_status = true where info_id = #{info_id}")
	void updateInformationStatus(int info_id);
}
