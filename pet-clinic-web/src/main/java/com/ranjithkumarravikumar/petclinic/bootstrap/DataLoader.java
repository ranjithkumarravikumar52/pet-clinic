package com.ranjithkumarravikumar.petclinic.bootstrap;

import com.ranjithkumarravikumar.petclinic.model.Owner;
import com.ranjithkumarravikumar.petclinic.model.Vet;
import com.ranjithkumarravikumar.petclinic.services.OwnerService;
import com.ranjithkumarravikumar.petclinic.services.VetService;
import com.ranjithkumarravikumar.petclinic.services.map.OwnerServiceMapImpl;
import com.ranjithkumarravikumar.petclinic.services.map.VetServiceMapImpl;
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

	//TODO spring DI here
	public DataLoader(){
		ownerService = new OwnerServiceMapImpl();
		vetService = new VetServiceMapImpl();
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Micheal");
		owner1.setLastName("Weston");

		ownerService.save(owner1);



		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");

		ownerService.save(owner2);


		System.out.println("Loaded Owners....");

		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");

		vetService.save(vet1);


		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");

		vetService.save(vet2); //John actually put vet1 here but I am sure that was a mistake he didnt catch

		System.out.println("Loaded Vets....");
	}
}
