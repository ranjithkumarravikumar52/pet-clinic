package com.ranjithkumarravikumar.petclinic.bootstrap;

import com.ranjithkumarravikumar.petclinic.model.Owner;
import com.ranjithkumarravikumar.petclinic.model.Pet;
import com.ranjithkumarravikumar.petclinic.model.PetType;
import com.ranjithkumarravikumar.petclinic.model.Vet;
import com.ranjithkumarravikumar.petclinic.services.OwnerService;
import com.ranjithkumarravikumar.petclinic.services.PetTypeService;
import com.ranjithkumarravikumar.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
		owner1.setAddress("123 Brickerel");
		owner1.setCity("Miami");
		owner1.setTelephone("1234567890");
		//adding pet to mike
		Pet mikesPet = new Pet();
		mikesPet.setName("Rosco");
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(owner1);
		mikesPet.setBirthDate(LocalDate.now());
		owner1.getPets().add(mikesPet);
		ownerService.save(owner1);



		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");
		owner2.setAddress("124 Brickerel");
		owner2.setCity("Miami");
		owner2.setTelephone("1234567890");
		//get fiona a cat
		Pet fionasCat = new Pet();
		fionasCat.setName("Just Cat");
		fionasCat.setOwner(owner2);
		fionasCat.setBirthDate(LocalDate.now());
		fionasCat.setPetType(savedCatPetType);
		owner2.getPets().add(fionasCat);
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
