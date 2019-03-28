package com.ranjithkumarravikumar.petclinic.services.map;

import com.ranjithkumarravikumar.petclinic.model.Specialty;
import com.ranjithkumarravikumar.petclinic.services.SpecialtyService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service //DONT FORGET TO ADD SERVICE ANNOTATION
public class SpecialityMapServiceImpl extends AbstractMapService<Specialty, Long> implements SpecialtyService {
	@Override
	public Specialty findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Specialty save(Specialty object) {
		return super.save(object);
	}

	@Override
	public Set<Specialty> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Specialty object) {
		super.delete(object);
	}
}
