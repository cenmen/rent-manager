package com.liang.rent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.rent.beans.RentManager;
import com.liang.rent.util.PageModel;



public interface RentManagerMapper {
	
	@Select("select * from rent_manager")
	List<RentManager> getRentManagers();
	
	@Select("select * from rent_manager where rm_id = #{rm_id}")
	RentManager getRentManagerById(RentManager rentManager);
	
	@Select("select * from rent_manager where rm_account = #{rm_account}")
	RentManager getRentManagerByAccountForUpdate(RentManager rentManager);
	
	@Select("select * from rent_manager where rm_account = #{rm_account} and rm_password = #{rm_password}")
	RentManager getRentManagerByAccount(RentManager rentManager);
	
	@Select("<script>"
            + "select * from `rent_manager`"
            + "<where>"
            + "<if test=\"rm_id != null and rm_id != '' \">AND rm_id = #{rm_id}</if>"
            + "<if test=\"rm_account != null and rm_account != '' \">AND rm_account = #{rm_account}</if>"
            + "<if test=\"rm_username != null and rm_username != '' \">AND rm_username = #{rm_username}</if>"
            + "<if test=\"rm_contact != null and rm_contact != '' \">AND rm_contact = #{rm_contact}</if>"
            + "</where>"
            + "</script>")
	List<RentManager> getRentManagerByCondition(RentManager rentManager);
	
	@Select("select rm_status from rent_manager where rm_account = #{rm_account}")
	boolean getRentManagerIsstatus(String rm_account);
	
	@Select("select * from rent_manager limit #{start}, #{pageSize}")
	List<RentManager> getRentManagerByPage(PageModel pageModel);
	
	@Update("update rent_manager set  rm_username = #{rm_username}, rm_contact = #{rm_contact} where rm_account = #{rm_account}")
	void updateRentManager(RentManager rentManager);
	
	@Update("update rent_manager set  rm_password = #{rm_password} where rm_account = #{rm_account}")
	void updateRentManagerPwd(RentManager rentManager);
}
