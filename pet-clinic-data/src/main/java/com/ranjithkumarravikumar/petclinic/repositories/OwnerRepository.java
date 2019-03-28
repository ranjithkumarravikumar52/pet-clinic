package com.ranjithkumarravikumar.petclinic.repositories;

import com.ranjithkumarravikumar.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
	Owner findByLastName(String lastName);
}
