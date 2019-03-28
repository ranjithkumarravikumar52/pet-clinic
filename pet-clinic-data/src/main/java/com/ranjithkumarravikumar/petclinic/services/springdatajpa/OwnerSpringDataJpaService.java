package com.ranjithkumarravikumar.petclinic.services.springdatajpa;

import com.ranjithkumarravikumar.petclinic.model.Owner;
import com.ranjithkumarravikumar.petclinic.repositories.OwnerRepository;
import com.ranjithkumarravikumar.petclinic.repositories.PetRepository;
import com.ranjithkumarravikumar.petclinic.repositories.PetTypeRepository;
import com.ranjithkumarravikumar.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSpringDataJpaService implements OwnerService {

	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;

	@Autowired //optional
	public OwnerSpringDataJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
	                                 PetTypeRepository petTypeRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}


	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public Owner findById(Long aLong) {
		//return null if object is not present
		return ownerRepository.findById(aLong).orElse(null);
	}

	@Override
	public Owner save(Owner object) {
		return ownerRepository.save(object);
	}

	@Override
	public Set<Owner> findAll() {
		HashSet<Owner> owners = new HashSet<>();
		ownerRepository.findAll().forEach(owners :: add);
		return owners;
	}

	@Override
	public void delete(Owner object) {
		ownerRepository.delete(object);
	}

	@Override
	public void deleteById(Long aLong) {
		ownerRepository.deleteById(aLong);
	}
}
