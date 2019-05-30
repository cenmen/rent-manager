package com.liang.rent.beans;

import lombok.Data;

@Data
public class Information {
	private int info_id;
	private String info_from;
	private String info_to;
	private String info_type;
	private String info_content;
	private boolean info_status;
}
