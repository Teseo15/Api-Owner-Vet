package com.example.demo.owner;

import com.example.demo.vet.PetNotFoundException;


import java.util.List;

public interface OwnerService {
    Owner create(Owner owner);
    Owner update(Owner owner);

    void delete(Long id) throws PetNotFoundException;


    Owner findById(long id) throws PetNotFoundException;

    List<Owner> findOwnerByFirstname(String name);


    Iterable<Owner> findAll();
    
}
