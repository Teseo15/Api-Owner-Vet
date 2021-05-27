package com.example.demo.owner;

import com.example.demo.vet.PetNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    OwnerService service;
    @GetMapping("/all")
    public Iterable<Owner> getVets() {
        //
        return service.findAll();
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    Owner create(@RequestBody Owner veterinario){
        return service.create(veterinario);
    }
    @GetMapping("/{id}")
    ResponseEntity<Owner> findOne(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (PetNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    Owner saveOrUpdate(@RequestBody Owner newPet, @PathVariable long id) {
        Owner pet = null;
        try {
            pet = service.findById(id);
            pet.setFirstname(newPet.getFirstname());
            pet.setLastname(newPet.getLastname());
            pet.setAddress(newPet.getAddress());
            pet.setCity(newPet.getCity());
            pet.setTelephone(newPet.getTelephone());

            service.update(pet);
        } catch (PetNotFoundException e) {
            pet = service.create(newPet);
        }
        return pet;
    }
    @DeleteMapping("/delete/{id}")
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
