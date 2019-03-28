package com.ranjithkumarravikumar.petclinic.repositories;

import com.ranjithkumarravikumar.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
