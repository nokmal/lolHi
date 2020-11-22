package com.sbs.example.lolHi.controller.usr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//브라우저의 요청을 처리할 수 있음

@Controller
public class HomeController {
	@RequestMapping("/")
	public String showMain_() {
		return "redirect:/usr/home/main";
	}
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "/usr/home/main";
	}
	
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return "잘가";
	}
	
	// /usr/home/plus?a=10&b=15
	@RequestMapping("/usr/home/plus")
	@ResponseBody
	public int showPlus(int a, int b) {
		return a+b;
	}
	@RequestMapping("/usr/home/minus")
	@ResponseBody
	public int showMinus(int a, int b) {
		return a-b;
	}
	@RequestMapping("/usr/home/multi")
	@ResponseBody
	public int showMulti(int a, int b) {
		return a*b;
	}
	@RequestMapping("/usr/home/divid")
	@ResponseBody
	public int showDivid(int a, int b) {
		return a/b;
	}
}