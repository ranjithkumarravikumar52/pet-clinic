package com.ranjithkumarravikumar.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/**
	 * list of request mappings.
	 */
	@RequestMapping({"", "/", "index", "index.html"})
	public String index(){
		return "index";
	}
}
