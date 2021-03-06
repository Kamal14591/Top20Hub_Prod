package com.kj.top20hub.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	
	@RequestMapping(value={"/","/home","/index"})
	public ModelAndView index()
	{
		
		ModelAndView mv = new ModelAndView("page");
		return mv;
		
	}
	
	@RequestMapping("/contactPage")
	public String ContactPage()
	{
		return "contact_page";
	}
	
	@RequestMapping("/AdminHome")
	public String AdminHome()
	{
		return "AdminHome";
	}

	@RequestMapping("/topicAdminPage")
	public String topicAdminPage()
	{
		return "topicAdminPage";
	}

	@RequestMapping("/contentAdminPage")
	public String contentAdminPage()
	{
		return "contentAdminPage";
	}

	@RequestMapping("/field")
	public String Field()
	{
		return "field";
	}
	
	@RequestMapping("/topic")
	public String Topic()
	{
		return "topic";
	}
	
	@RequestMapping("/topicSorted")
	public String TopicSorted()
	{
		return "topicSorted";
	}
}
