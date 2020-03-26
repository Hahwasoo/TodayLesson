package com.todaylesson.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
	private int order_detail_no;
	private int orderlist_no;
	private int product_no;
	private int lesson_no;
	private int order_count;
	private String product_name;
	private String lesson_name;

}
