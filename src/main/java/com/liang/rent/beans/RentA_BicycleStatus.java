package com.liang.rent.beans;

import lombok.Data;

@Data
public class RentA_BicycleStatus {
	private String rbs_rp_id;
	private String rbs_bp_id;
	private boolean rbs_bicycle_hire_status;
	private String rbs_bicycle_quality_status;
}
