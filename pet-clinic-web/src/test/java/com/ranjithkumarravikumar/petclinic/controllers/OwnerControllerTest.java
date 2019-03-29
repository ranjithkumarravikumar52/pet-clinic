package com.ranjithkumarravikumar.petclinic.controllers;

import com.ranjithkumarravikumar.petclinic.model.Owner;
import com.ranjithkumarravikumar.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
/**
 * Testing controller as a light weight IT. Mocking the ownerService while testing the controller
 */
class OwnerControllerTest {

	@Mock
	OwnerService ownerService;

	@InjectMocks
	OwnerController controller;

	Set<Owner> owners;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());

		//initialize spring mvc
		mockMvc = MockMvcBuilders
				.standaloneSetup(controller) //standalone is a lightweight environment - which means it doesn't pull up a real server
				.build();
	}

	/**
	 * Can use this method to test for any alternate mappings
	 */
	@Test
	void listOwners() throws Exception{
		when(ownerService.findAll()).thenReturn(owners);

		//checking http status
		mockMvc.perform(get("/owners/index"))
				.andExpect(status().isOk()) //a path exists
				.andExpect(view().name("owners/index")) //a valid mapping
				.andExpect(model().attribute("owners", hasSize(2))); //model attribute is owners and has size 2
	}

	@Test
	void findOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
				.andExpect(status().isOk())
				.andExpect(view().name("nothingimplemented"));

		//zero interaction with mock
		verifyZeroInteractions(ownerService);
	}

	@Test
	void displayOwner() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
		mockMvc.perform(get("/owners/123"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/ownerDetails"))
				.andExpect(model().attribute("owner", hasProperty("id", is(1L)))); //model attribute is owners and has size 2;
	}
}