package com.ranjithkumarravikumar.petclinic.bootstrap;

import com.ranjithkumarravikumar.petclinic.model.Owner;
import com.ranjithkumarravikumar.petclinic.model.PetType;
import com.ranjithkumarravikumar.petclinic.model.Vet;
import com.ranjithkumarravikumar.petclinic.services.OwnerService;
import com.ranjithkumarravikumar.petclinic.services.PetTypeService;
import com.ranjithkumarravikumar.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class for loading sample data at the start
 *
 * Spring boot specific method
 */
@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;

	@Autowired //optional
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {

		//adding pettype objects
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("cat");
		PetType savedCatPetType = petTypeService.save(cat);


		Owner owner1 = new Owner();
		owner1.setFirstName("Micheal");
		owner1.setLastName("Weston");

		ownerService.save(owner1);



		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");

		ownerService.save(owner2);


		System.out.println("Loaded Owners....");

		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");

		vetService.save(vet1);


		Vet vet2 = new Vet();
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");

		vetService.save(vet2); //John actually put vet1 here but I am sure that was a mistake he didnt catch

		System.out.println("Loaded Vets....");
	}
}
