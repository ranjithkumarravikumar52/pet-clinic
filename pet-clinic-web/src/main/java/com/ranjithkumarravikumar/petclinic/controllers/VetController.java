package com.ranjithkumarravikumar.petclinic.controllers;

import com.ranjithkumarravikumar.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class VetController {

	private final VetService vetService;

	@Autowired //optional
	public VetController(VetService vetService) {
		this.vetService = vetService;
	}

	@RequestMapping("/index")
	public String listVets(Model model){
		model.addAttribute("vets", vetService.findAll());
		return "vets/index";
	}
}
