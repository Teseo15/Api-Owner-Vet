package com.example.demo.vet;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VetRepository  extends CrudRepository<Veterinario,Long>{
	List<Veterinario> findByName(String name);
	List<Veterinario> findByLast(String last_name);
}
