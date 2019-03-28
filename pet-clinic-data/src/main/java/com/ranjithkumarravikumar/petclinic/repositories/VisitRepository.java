package com.ranjithkumarravikumar.petclinic.repositories;

import com.ranjithkumarravikumar.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
