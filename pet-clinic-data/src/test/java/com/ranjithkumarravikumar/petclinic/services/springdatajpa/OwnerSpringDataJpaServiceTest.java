package com.ranjithkumarravikumar.petclinic.services.springdatajpa;

import com.ranjithkumarravikumar.petclinic.model.Owner;
import com.ranjithkumarravikumar.petclinic.repositories.OwnerRepository;
import com.ranjithkumarravikumar.petclinic.repositories.PetRepository;
import com.ranjithkumarravikumar.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Learning Mockito.
 * The higher level idea of this class is to mock the ownerRepository(DAO layer), so that we can test the ownerSpringDataJpaService as light-weight integration tests
 * <br>
 *     Service Flow<br>
 *         OwnerService(DI with ownerSpringDataJpaService)<->OwnerRepository(mocked!)
 */
@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest {

	final String LAST_NAME = "Smith";
	@Mock
	OwnerRepository ownerRepository;
	@Mock
	PetRepository petRepository;//not needed
	@Mock
	PetTypeRepository petTypeRepository; //not needed
	@InjectMocks
	OwnerSpringDataJpaService ownerSpringDataJpaService;
	Owner returnOwner;

	/**
	 * Build a owner called returnOwner which we will be using before each tests.
	 * Resets before each method. Even though its redundant its good practice to have.
	 */
	@BeforeEach
	void setUp() {
		returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
	}

	@Test
	void findByLastName() {

		//here we are mocking ownerRepository service, which will give us returnOwner
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

		Owner smith = ownerSpringDataJpaService.findByLastName(LAST_NAME);
		assertEquals(LAST_NAME, smith.getLastName());

		//verify that findByLastName() is called once
		verify(ownerRepository).findByLastName(any());
	}

	@Test
	void findByIdFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
		Owner owner = ownerSpringDataJpaService.findById(1L);
		assertNotNull(owner); //check null
	}

	@Test
	void findByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		Owner owner = ownerSpringDataJpaService.findById(1L);
		assertNull(owner); //check null
	}

	@Test
	void saveNotNull() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		//when we call mock it should returnOwner
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		Owner savedOwner = ownerSpringDataJpaService.save(ownerToSave);
		assertNotNull(savedOwner); //check not null
		//to assert that mock method is called
		verify(ownerRepository).save(any());
	}

	@Test
	void saveCheckId() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		//when we call mock it should returnOwner
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		Owner savedOwner = ownerSpringDataJpaService.save(ownerToSave);

		Long expectedId = 1L;
		assertEquals(expectedId, savedOwner.getId());//id should match
		//to assert that mock method is called
		verify(ownerRepository).save(any());
	}

	@Test
	void findAll() {
		//build 2 owner objects
		Set<Owner> returnOwnerSet = new HashSet<>();
		returnOwnerSet.add(Owner.builder().id(1L).build());
		returnOwnerSet.add(Owner.builder().id(2L).build());

		//call ownerRepository mock service
		when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

		//call the ownerSpringDataJpaService which will call the ownerRepository(mocked!) to give us the above two objects
		Set<Owner> ownerSet = ownerSpringDataJpaService.findAll();
		assertNotNull(ownerSet); //should be not null
		assertEquals(2, ownerSet.size());
	}

	/**
	 * Testing methods of void return type is tricky!
	 * So we use mockito to check if method delete() on ownerSpringDataJpaService in turn calls ownerRepository only ONCE.
	 * Default is 1 time.
	 */
	@Test
	void deleteById() {
		ownerSpringDataJpaService.deleteById(1L);
		verify(ownerRepository).deleteById(anyLong());
	}

	@Test
	void delete() {
		ownerSpringDataJpaService.delete(returnOwner);
		verify(ownerRepository).delete(any());
	}
}