package com.ranjithkumarravikumar.petclinic.services.map;

import com.ranjithkumarravikumar.petclinic.model.Speciality;
import com.ranjithkumarravikumar.petclinic.services.SpecialtyService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service //DONT FORGET TO ADD SERVICE ANNOTATION
public class SpecialityMapServiceImpl extends AbstractMapService<Speciality, Long> implements SpecialtyService {
	@Override
	public Speciality findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Speciality save(Speciality object) {
		return super.save(object);
	}

	@Override
	public Set<Speciality> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Speciality object) {
		super.delete(object);
	}
}
