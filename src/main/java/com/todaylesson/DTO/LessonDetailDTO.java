package com.todaylesson.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LessonDetailDTO {

	private int lessondetail_no;
	private int lesson_no;
	private int senior_no;
	private String lessondetail_title;
	private String lessondetail_content;
	private int lessondetail_running_time;
	private int lessondetail_chapter;
	private int lessondetail_comp;
	private String member_id;
	private String lessondetail_thumb;
	
	// ���� �̸�
	private String lesson_title;
	//lessoncomp��ȣ
	private int lessoncomp_no;
	
	
	
	
	
		
}
