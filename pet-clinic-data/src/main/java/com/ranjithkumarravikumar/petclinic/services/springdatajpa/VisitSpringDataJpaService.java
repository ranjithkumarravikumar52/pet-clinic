package com.ranjithkumarravikumar.petclinic.services.springdatajpa;

import com.ranjithkumarravikumar.petclinic.model.Visit;
import com.ranjithkumarravikumar.petclinic.repositories.VisitRepository;
import com.ranjithkumarravikumar.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("springdatajpa")
public class VisitSpringDataJpaService implements VisitService {

	private final VisitRepository visitRepository;

	public VisitSpringDataJpaService(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public Visit findById(Long aLong) {
		return visitRepository.findById(aLong).orElse(null);
	}

	@Override
	public Visit save(Visit object) {
		return visitRepository.save(object);
	}

	@Override
	public Set<Visit> findAll() {
		Set<Visit> set = new HashSet<>();
		visitRepository.findAll().forEach(set :: add);
		return set;
	}

	@Override
	public void delete(Visit object) {
		visitRepository.delete(object);
	}

	@Override
	public void deleteById(Long aLong) {
		visitRepository.deleteById(aLong);
	}
}
