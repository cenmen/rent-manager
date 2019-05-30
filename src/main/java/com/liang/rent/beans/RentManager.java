package com.liang.rent.beans;

import java.sql.Date;
import lombok.Data;

@Data
public class RentManager {
	private String rm_id;
	private int rm_role_id;
	private String rm_account;
	private String rm_password;
	private String rm_username;
	private String rm_contact;
	private Date rm_create_time;
	private Date rm_last_time;
	private boolean rm_status;
	private String rm_rp_id;
	private String rm_mark;
}
