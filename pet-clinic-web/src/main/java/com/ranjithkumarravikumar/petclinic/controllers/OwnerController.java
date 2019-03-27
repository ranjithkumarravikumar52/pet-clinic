package com.ranjithkumarravikumar.petclinic.controllers;

import com.ranjithkumarravikumar.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {

	private final OwnerService ownerService;

	@Autowired //optional
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@RequestMapping("/index")
	public String listOwners(Model model){
		model.addAttribute("owners", ownerService.findAll());
		return "owners/index";
	}
}