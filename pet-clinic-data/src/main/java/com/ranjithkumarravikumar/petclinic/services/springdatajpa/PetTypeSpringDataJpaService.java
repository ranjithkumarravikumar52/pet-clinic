package com.ranjithkumarravikumar.petclinic.services.springdatajpa;

import com.ranjithkumarravikumar.petclinic.model.PetType;
import com.ranjithkumarravikumar.petclinic.repositories.PetTypeRepository;
import com.ranjithkumarravikumar.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSpringDataJpaService implements PetTypeService {

	private final PetTypeRepository petTypeRepository;

	public PetTypeSpringDataJpaService(PetTypeRepository petTypeRepository) {
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public PetType findById(Long aLong) {
		return petTypeRepository.findById(aLong).orElse(null);
	}

	@Override
	public PetType save(PetType object) {
		return petTypeRepository.save(object);
	}

	@Override
	public Set<PetType> findAll() {
		Set<PetType> set = new HashSet<>();
		petTypeRepository.findAll().forEach(set::add);
		return set;
	}

	@Override
	public void delete(PetType object) {
		petTypeRepository.delete(object);
	}

	@Override
	public void deleteById(Long aLong) {
		petTypeRepository.deleteById(aLong);
	}
}
