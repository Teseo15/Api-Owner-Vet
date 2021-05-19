package com.example.demo;

import java.util.List;





public interface VetService {

	Veterinario create(Veterinario vete);
	
	Veterinario update(Veterinario vete);

	void delete(Long id) throws PetNotFoundException;


	Veterinario findById(long id) throws PetNotFoundException;

	List<Veterinario> findByName(String name);
	List<Veterinario> findByLastName(String lastname);

	Iterable<Veterinario> findAll();
}
