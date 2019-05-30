package com.liang.rent.beans;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RentA_BicycleManager {
	private int rbm_id;
	private String rbm_bp_id;
	private String rbm_hirer_name;
	private String rbm_hirer_contact;
	private String rbm_hire_location;
	private String rbm_persent_location;
	private Timestamp rbm_hire_time;
	private Timestamp rbm_retrieve_time;
	private String rbm_hirer_ID;
	private boolean rbm_hirer_deposit;
	private boolean rbm_ischarge;
	private String rbm_hirer_pay;
	private boolean rbm_istrouble;
	private String rbm_damage;
	private String rbm_mark;
}
