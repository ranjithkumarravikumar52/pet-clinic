package com.ranjithkumarravikumar.petclinic.services.map;

import com.ranjithkumarravikumar.petclinic.model.Owner;
import com.ranjithkumarravikumar.petclinic.services.CrudService;

import java.util.Set;

public class OwnerServiceMapImpl extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {
	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Owner save(Owner object) {
		return super.save(object.getId(), object);
	}

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}

	@Override
	public void delete(Owner object) {
		super.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
}
