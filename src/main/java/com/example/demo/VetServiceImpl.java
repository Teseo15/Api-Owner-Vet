package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



//CLASE DE SERVICIO

@Service
public class VetServiceImpl implements VetService {
	private static final Logger logger = LoggerFactory.getLogger(VetServiceImpl.class);

	@Autowired
	VetRepository vetRepository;


	@Override
	public Veterinario create(Veterinario vete) {
		
		return vetRepository.save(vete);
	}

	@Override
	public Veterinario update(Veterinario vete) {
		
		return vetRepository.save(vete);
	}

	@Override
	public void delete(Long id) throws PetNotFoundException {
		
		Veterinario vete = findById(id);
		vetRepository.delete(vete);
	}

	@Override
	public Veterinario findById(long id) throws PetNotFoundException {
		Optional<Veterinario> vete = vetRepository.findById(id);

		if ( !vete.isPresent())
			throw new PetNotFoundException("Record not found...!");
			
		return vete.get();
	}

	@Override
	public List<Veterinario> findByName(String name) {
		List<Veterinario> vetes = vetRepository.findByName(name);

		vetes.stream().forEach(vet -> logger.info("" + vet));

		return vetes;
	}

	@Override
	public Iterable<Veterinario> findAll() {
		
		return vetRepository.findAll();
	}

	@Override
	public List<Veterinario> findByLastName(String lastname) {
		List<Veterinario> vetes = vetRepository.findByLast(lastname);

		vetes.stream().forEach(vet -> logger.info("" + vet));

		return vetes;
	}

}
