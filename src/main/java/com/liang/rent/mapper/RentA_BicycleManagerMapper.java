package com.liang.rent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.rent.beans.RentA_BicycleManager;
import com.liang.rent.beans.RentA_BicycleStatus;
import com.liang.rent.util.PageModel;

public interface RentA_BicycleManagerMapper {
	@Select("select * from rent_a_bicycle_manager")
	List<RentA_BicycleManager> getRentA_BicycleManagers();
	
	@Select("select * from rent_a_bicycle_manager where rbm_id = #{rbm_id}")
	RentA_BicycleManager getRentA_BicycleManagerById(int rbm_id);
	
	@Select("<script>"
            + "select * from `rent_a_bicycle_manager`"
            + "<where>"
            + "<if test=\"rbm_bp_id != null and rbm_bp_id != '' \">AND rbm_bp_id = #{rbm_bp_id}</if>"
            + "<if test=\"rbm_hirer_name != null and rbm_hirer_name != '' \">AND rbm_hirer_name = #{rbm_hirer_name}</if>"
            + "<if test=\"rbm_hirer_contact != null and rbm_hirer_contact != '' \">AND rbm_hirer_contact = #{rbm_hirer_contact}</if>"
            + "<if test=\"rbm_hirer_ID != null and rbm_hirer_ID != '' \">AND rbm_hirer_ID = #{rbm_hirer_ID}</if>"
            + "</where>"
            + "</script>")
	List<RentA_BicycleManager> getRentA_BicycleManagerByCondition(RentA_BicycleManager rentA_BicycleManager);
	
	@Select("select * from rent_a_bicycle_manager limit #{start}, #{pageSize}")
	List<RentA_BicycleManager> getRentA_BicycleManagerByPage(PageModel pageModel);
	
	@Insert("insert into rent_a_bicycle_manager values(#{rbm_id}, #{rbm_bp_id}, #{rbm_hirer_name}, #{rbm_hirer_contact}, #{rbm_hire_location}, "
			+ "#{rbm_persent_location}, #{rbm_hire_time}, #{rbm_retrieve_time}, #{rbm_hirer_ID}, #{rbm_hirer_deposit}, #{rbm_ischarge}, #{rbm_hirer_pay}, #{rbm_istrouble}, #{rbm_mark}, #{rbm_damage})")
	void addRentA_BicycleManager(RentA_BicycleManager rentA_BicycleManager);
	
	@Update("update rent_a_bicycle_manager set rbm_persent_location = #{rbm_persent_location} , rbm_retrieve_time = #{rbm_retrieve_time} , "
			+ "rbm_ischarge = #{rbm_ischarge} , rbm_hirer_pay = #{rbm_hirer_pay} , rbm_istrouble = #{rbm_istrouble} , rbm_damage = #{rbm_damage} where rbm_id = #{rbm_id}")
	void updateRentA_BicycleManager(RentA_BicycleManager rentA_BicycleManager);
	
	@Delete("delete from rent_a_bicycle_manager where rbm_id = #{rbm_id}")
	void deleteRentA_BicycleManager(RentA_BicycleManager rentA_BicycleManager);
	
	//获取表最后一行ID
	@Select("select rbm_id from rent_a_bicycle_manager order by rbm_id desc limit 1")
	int getRentA_BicycleManagerLast();
	
	//根据账号查询租借点ID
	@Select("select rm_rp_id from rent_manager where rm_account = #{rm_account}")
	String getRentManagerByAccountForRpId(String rm_account);
	
	//根据租借点ID查询租借点所有车辆
	@Select("select rbs_bp_id from rent_a_bicycle_status where rbs_rp_id = #{rbs_rp_id} and rbs_bicycle_quality_status = '正常' ")
	List<String> getRentManagerByAccountForRpBicycle(String rbs_rp_id);
	
	//根据车辆ID查询当前车辆是否已被租借
	@Select("select rbs_bicycle_hire_status from rent_a_bicycle_status where  rbs_bp_id = #{rbs_bp_id}")
	boolean getRentManagerByBp_idForIsStatus(String rbs_bp_id);
	
	//根据车辆ID修改车辆质量状态
	@Update("update rent_a_bicycle_status set rbs_bicycle_quality_status = '损坏' where rbs_bp_id = #{rbs_bp_id}")
	void updateRentA_BicycleStatusForIsTrouble(String rbs_bp_id);
}
