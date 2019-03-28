package com.ranjithkumarravikumar.petclinic.services.springdatajpa;

import com.ranjithkumarravikumar.petclinic.model.Specialty;
import com.ranjithkumarravikumar.petclinic.repositories.SpecialtyRepository;
import com.ranjithkumarravikumar.petclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialtySpringDataJpaService implements SpecialtyService {

	private final SpecialtyRepository specialtyRepository;

	public SpecialtySpringDataJpaService(SpecialtyRepository specialtyRepository) {
		this.specialtyRepository = specialtyRepository;
	}

	@Override
	public Specialty findById(Long aLong) {
		return specialtyRepository.findById(aLong).orElse(null);
	}

	@Override
	public Specialty save(Specialty object) {
		return specialtyRepository.save(object);
	}

	@Override
	public Set<Specialty> findAll() {
		Set<Specialty> specialties = new HashSet<>();
		specialtyRepository.findAll().forEach(specialties::add);
		return specialties;
	}

	@Override
	public void delete(Specialty object) {
		specialtyRepository.delete(object);
	}

	@Override
	public void deleteById(Long aLong) {
		specialtyRepository.deleteById(aLong);
	}
}
