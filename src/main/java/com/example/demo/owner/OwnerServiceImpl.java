package com.example.demo.owner;

import java.util.List;
import java.util.Optional;

import com.example.demo.vet.PetNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService{
    private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

    @Autowired
   OwnerRepository ownerRepository;

    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Long id) throws PetNotFoundException {
        Owner owner=findById(id);
        ownerRepository.delete(owner);
    }

    @Override
    public Owner findById(long id) throws PetNotFoundException {
       Optional<Owner> owner=ownerRepository.findById(id);
        if ( !owner.isPresent())
            throw new PetNotFoundException("Record not found...!");
        return owner.get();
    }

    @Override
    public List<Owner> findOwnerByFirstname(String name) {
        List<Owner> owners=ownerRepository.findOwnerByFirstname(name);
        owners.stream().forEach(owner -> logger.info(""+owner));
        return owners;
    }

    @Override
    public Iterable<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
