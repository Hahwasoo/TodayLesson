package com.todaylesson.oreo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todaylesson.DTO.LessonDTO;
import com.todaylesson.service.JY_US_LessonService;


@Controller
public class JY_US_LessonController {

	
	@Resource(name="lessonservice")
	private JY_US_LessonService lessonservice;

	
	
	@RequestMapping("/lesson_list")
	public String list(Model model){
		List<LessonDTO> list = lessonservice.list();
		model.addAttribute("list",list);
		return "TodayLesson_SeniorPage/jy_sn_lesson_list";
	}
	
	
	
	@RequestMapping("/lesson_write")
	public String write() {
		return "TodayLesson_SeniorPage/jy_sn_lesson_write";
	}
	
	@RequestMapping("/jusoPopup")
	public String jusoPopup() {
		return "TodayLesson_SeniorPage/jy_sn_jusoPopup";
	}	
	
	
	
	
	@RequestMapping("/insert_result")
	public String insertresult(LessonDTO dto, Model model) {
		
	
		System.out.println("                     " +dto.toString());
		
		int result =lessonservice.insertLesson(dto);
		
		model.addAttribute("result",result);
		
		return "TodayLesson_SeniorPage/jy_sn_insertresult";
	}
	
	

	

	
	
	
	
}
