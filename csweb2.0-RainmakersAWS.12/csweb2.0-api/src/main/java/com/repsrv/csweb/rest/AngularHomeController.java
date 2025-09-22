package com.repsrv.csweb.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AngularHomeController {
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView visitHome() {
		ModelAndView model = new ModelAndView("forward:/index.html");
        return model;
	}
}
