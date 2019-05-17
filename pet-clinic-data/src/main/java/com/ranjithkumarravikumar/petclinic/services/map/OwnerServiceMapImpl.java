package com.ranjithkumarravikumar.petclinic.services.map;

import com.ranjithkumarravikumar.petclinic.model.Owner;
import com.ranjithkumarravikumar.petclinic.model.Pet;
import com.ranjithkumarravikumar.petclinic.services.OwnerService;
import com.ranjithkumarravikumar.petclinic.services.PetService;
import com.ranjithkumarravikumar.petclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMapImpl extends AbstractMapService<Owner, Long> implements OwnerService {
	private final PetTypeService petTypeService;
	private final PetService petService;

	@Autowired //optional.
	public OwnerServiceMapImpl(PetTypeService petTypeService, PetService petService) {
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Owner save(Owner object) {

		if(object != null){
			if(object.getPets() != null){
				object.getPets().forEach(pet -> {
					//iterate each pet
					if(pet.getPetType() != null){
						if(pet.getPetType().getId() == null){
							//if id is null then save
							pet.setPetType(petTypeService.save(pet.getPetType()));
						}
					}else{
						throw new RuntimeException("Pet Type is required");
					}
					if(pet.getId() == null){
						Pet savedPet = petService.save(pet);
						pet.setId(savedPet.getId());
					}
				});
			}
			return super.save(object);
		}else {
			return null;
		}

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

	@Override
	public Owner findByLastName(String lastName) {
		return this.findAll()
				.stream()
				.filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Owner> findAllByLastNameLike(String lastName) {
		//todo - impl
		return null;
	}
}
