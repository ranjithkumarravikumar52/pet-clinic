package com.ranjithkumarravikumar.petclinic.bootstrap;

import com.ranjithkumarravikumar.petclinic.model.*;
import com.ranjithkumarravikumar.petclinic.services.OwnerService;
import com.ranjithkumarravikumar.petclinic.services.PetTypeService;
import com.ranjithkumarravikumar.petclinic.services.SpecialtyService;
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
	private final SpecialtyService specialtyService;

	@Autowired //optional
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
	}

	@Override
	public void run(String... args) throws Exception {
		int count = petTypeService.findAll().size();
		if(count == 0){
			loadData();
		}
	}

	private void loadData() {
		//adding petType objects
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("cat");
		PetType savedCatPetType = petTypeService.save(cat);

		//add specialties - Radiology, Surgery, Dentistry
		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		Speciality savedRadiology = specialtyService.save(radiology);

		Speciality surgery = new Speciality();
		surgery.setDescription("Surgery");
		Speciality savedSurgery = specialtyService.save(surgery);

		Speciality dentistry = new Speciality();
		dentistry.setDescription("Dentistry");
		Speciality savedDentistry = specialtyService.save(dentistry);


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
		vet1.getSpecialities().add(savedRadiology);
		vetService.save(vet1);


		Vet vet2 = new Vet();
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		vet2.getSpecialities().add(savedSurgery);
		vetService.save(vet2);

		System.out.println("Loaded Vets....");
	}
}
