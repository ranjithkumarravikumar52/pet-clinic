package com.ranjithkumarravikumar.petclinic.services;

import com.ranjithkumarravikumar.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
	Owner findByLastName(String lastName);
}
