package com.todaylesson.DTO;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDTO {

	private int sales_no;
	private int orderlist_no;
	private int sales_status;
	private int sales_comm;
	private int sales_surtax;
}
