package com.ranjithkumarravikumar.petclinic.controllers;

import com.ranjithkumarravikumar.petclinic.model.Owner;
import com.ranjithkumarravikumar.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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


	// removing this functionality as we longer need it
	/*@RequestMapping("/index")
	public String listOwners(Model model){
		model.addAttribute("owners", ownerService.findAll());
		return "owners/index";
	}*/

	@RequestMapping("/find")
	public String findOwners(Model model){
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
	}

	@GetMapping
	public String processFindForm(Owner owner, BindingResult bindingResult, Model model){
		//allowing parameter-less GET request for /owners to return all records
		if(owner.getLastName() == null){
			owner.setLastName("");; //empty string signifies broadest possible search
		}

		//find owners by last name
		List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());

		if(results.isEmpty()){
			//no owners found
			bindingResult.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		}
		else if(results.size() == 1){
			//1 owner found
			owner = results.get(0);
			return "redirect:/owners/" + owner.getId();
		}
		else{
			//multiple owners found
			model.addAttribute("selections", results);
			return "owners/ownersList";
		}

	}

	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
		ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
		modelAndView.addObject(ownerService.findById(ownerId));
		return modelAndView;
	}
}
