package com.ranjithkumarravikumar.petclinic.services.map;

import com.ranjithkumarravikumar.petclinic.model.Speciality;
import com.ranjithkumarravikumar.petclinic.model.Vet;
import com.ranjithkumarravikumar.petclinic.services.SpecialtyService;
import com.ranjithkumarravikumar.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMapImpl extends AbstractMapService<Vet, Long> implements VetService {
	private final SpecialtyService specialtyService;

	public VetServiceMapImpl(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Vet save(Vet object) {
		if(object.getSpecialities().size() > 0){
			object.getSpecialities().forEach(speciality -> {
				if(speciality.getId() == null){
					Speciality savedSpeciality = specialtyService.save(speciality);
					speciality.setId(savedSpeciality.getId());
				}
			});
		}
		return super.save(object);
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Vet object) {
		super.delete(object);
	}
}
