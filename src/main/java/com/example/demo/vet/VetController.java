package com.example.demo.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VetController {
    @Autowired VetService service;

    @GetMapping("/vets/all")
    public Iterable<Veterinario> getVets() {
        //
        return service.findAll();
    }
    @PostMapping("/vets/add")
    @ResponseStatus(HttpStatus.CREATED)
    Veterinario create(@RequestBody Veterinario veterinario){
        return service.create(veterinario);
    }
    @GetMapping("/vets/{id}")
    ResponseEntity<Veterinario> findOne(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (PetNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/vets/update/{id}")
    Veterinario saveOrUpdate(@RequestBody Veterinario newPet, @PathVariable Long id) {
        Veterinario pet = null;
        try {
            pet = service.findById(id);
            pet.setName(newPet.getName());
            pet.setLast(newPet.getLast());

            service.update(pet);
        } catch (PetNotFoundException e) {
            pet = service.create(newPet);
        }
        return pet;
    }
    @DeleteMapping("/vets/delete/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {

        try {
            service.delete(id);
            return new ResponseEntity<>("" + id, HttpStatus.OK);
        } catch (PetNotFoundException e) {
            // TODO Auto-generated catch block
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
