package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

;

import com.example.demo.vet.PetNotFoundException;
import com.example.demo.vet.VetService;
import com.example.demo.vet.Veterinario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class VetServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(VetServiceTest.class);

	@Autowired
	private VetService vetService;
	
	
	//BUSCAR POR ID
	@Test
	public void testFindVetById() {

		long ID = 1;
		String NAME = "James";
		Veterinario vet = null;
		
		try {
			
			vet = vetService.findById(ID);
			
		} catch (PetNotFoundException e) {
			fail(e.getMessage());
		}
		logger.info("" + vet);

		assertEquals(NAME, vet.getName());

	}
	//BUSCAR POR NOMBRE
	@Test
	public void testFindvetByName() {

		String NAME = "James";
		int SIZE_EXPECTED = 1;

		List<Veterinario> vets = vetService.findByName(NAME);

		assertEquals(SIZE_EXPECTED, vets.size());
	}
	
	
	//CREAR VETERINARIO
	@Test
	public void testCreateVet() {

		String NAME = "Lana";
		String LAST = "Lang";
		
		Veterinario vet = new Veterinario(NAME,LAST);
		
		vet = vetService.create(vet);
		logger.info("" + vet);

		assertThat(vet.getId()).isNotNull();
		assertEquals(NAME, vet.getName());
		assertEquals(LAST, vet.getLast());
		

	}
	
	//ACTUALIZAR
	@Test
	public void testUpdatePet() {

		String NAME = "Sharon10";
		String LAST = "Jenkins10";
		long create_id = -1;

		String UPNAME = "Sharon20";
		String UPLAST = "Jenkins20";
		Veterinario vet = new Veterinario(NAME,LAST);

		logger.info(">" + vet);
		Veterinario readVet = vetService.create(vet);
		logger.info(">>" + readVet);

		create_id = readVet.getId();

		// Prepare data for update
		readVet.setName(UPNAME);
		readVet.setLast(UPLAST);
		// Execute update
		Veterinario upgradeVet = vetService.update(readVet);
		logger.info(">>>>" + upgradeVet);

		assertThat(create_id).isNotNull();
		assertEquals(create_id, upgradeVet.getId());
		assertEquals(UPNAME, upgradeVet.getName());
		assertEquals(UPLAST, upgradeVet.getLast());
		
	}
	
	
	//ElIMINAR 
	@Test
	public void testDeleteVet() {

		String NAME = "Sharon2";
		String LAST = "Jenkins2";

		Veterinario vet = new Veterinario(NAME,LAST);


		vet = vetService.create(vet);
		logger.info("" + vet);

		try {
			vetService.delete(vet.getId());
		} catch (PetNotFoundException e) {
			fail(e.getMessage());
		}

		try {
			vetService.findById(vet.getId());
			assertTrue(false);
		} catch (PetNotFoundException e) {
			assertTrue(true);
		}

	}

	}



