package com.liang.rent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.rent.beans.RentA_BicycleStatus;
import com.liang.rent.beans.RentManager;
import com.liang.rent.util.PageModel;

public interface RentA_BicycleStatusMapper {
	
	@Select("select * from rent_a_bicycle_status")
	List<RentA_BicycleStatus> getRentA_BicycleStatuss();
	
	@Select("select * from rent_a_bicycle_status where rbs_bp_id = #{rbs_bp_id}")
	RentA_BicycleStatus getRentA_BicycleStatusById(RentA_BicycleStatus rentA_BicycleStatus);
	
	@Select("<script>"
            + "select * from `rent_a_bicycle_status`"
            + "<where>"
            + "<if test=\"rbs_rp_id != null and rbs_rp_id != '' \">AND rbs_rp_id = #{rbs_rp_id}</if>"
            + "<if test=\"rbs_bp_id != null and rbs_bp_id != '' \">AND rbs_bp_id = #{rbs_bp_id}</if>"
            + "<if test=\"rbs_bicycle_quality_status != null and rbs_bicycle_quality_status != '' \">AND rbs_bicycle_quality_status = #{rbs_bicycle_quality_status}</if>"
            + "<if test=\"rbs_bicycle_hire_status != null and rbs_bicycle_hire_status != '' \">AND rbs_bicycle_hire_status = #{rbs_bicycle_hire_status}</if>"
            + "</where>"
            + "</script>")
	List<RentA_BicycleStatus> getRentA_BicycleStatusByCondition(RentA_BicycleStatus rentA_BicycleStatus);
	
	@Select("select * from rent_a_bicycle_status where rbs_rp_id = #{rbs_rp_id} limit #{start}, #{pageSize}")
	List<RentA_BicycleStatus> getRentA_BicycleStatusByPage(PageModel pageModel);
	
	@Insert("insert into rent_a_bicycle_status values(#{rbs_rp_id}, #{rbs_bp_id}, #{rbs_bicycle_hire_status}, #{rbs_bicycle_quality_status})")
	void addRentA_BicycleStatus(RentA_BicycleStatus rentA_BicycleStatus);
	
	@Update("update rent_a_bicycle_status set rbs_rp_id = #{rbs_rp_id}, rbs_bicycle_hire_status = #{rbs_bicycle_hire_status}, "
			+ "rbs_bicycle_quality_status = #{rbs_bicycle_quality_status} where rbs_bp_id = #{rbs_bp_id}")
	void updateRentA_BicycleStatus(RentA_BicycleStatus rentA_BicycleStatus);
	
	@Delete("delete from rent_a_bicycle_status where rbs_bp_id = #{rbs_bp_id}")
	void deleteRentA_BicycleStatus(RentA_BicycleStatus rentA_BicycleStatus);
	
	//根据账号查询租借点ID
	@Select("select rm_rp_id from rent_manager where rm_account = #{rm_account}")
	String getRentManagerByAccountForRpId(String rm_account);
	
	//查询状态表的车辆ID
	@Select("select rbs_bp_id from rent_a_bicycle_status")
	List<String> getRentA_BicycleStatusBpId();
}
