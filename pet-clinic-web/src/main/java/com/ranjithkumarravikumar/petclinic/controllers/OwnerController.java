package com.ranjithkumarravikumar.petclinic.controllers;

import com.ranjithkumarravikumar.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/owners")
public class OwnerController {

	private final OwnerService ownerService;

	@Autowired //optional
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	/**
	 * How we handle binding on Java Objects. We don't want to allow id fields to displayed to public.
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder){
		dataBinder.setDisallowedFields("id");
	}


	@RequestMapping("/index")
	public String listOwners(Model model){
		model.addAttribute("owners", ownerService.findAll());
		return "owners/index";
	}

	@RequestMapping("/find")
	public String findOwners(){
		return "nothingimplemented";
	}

	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
		ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
		modelAndView.addObject(ownerService.findById(ownerId));
		return modelAndView;
	}
}
