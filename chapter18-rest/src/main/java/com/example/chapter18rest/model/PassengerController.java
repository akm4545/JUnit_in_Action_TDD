package com.example.chapter18rest.model;

import com.example.chapter18rest.exceptions.PassengerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PassengerController {

    @Autowired
    private PassengerRepository repository;

    @Autowired
    private Map<String, Country> countriesMap;

    @GetMapping("/passengers")
    @ResponseStatus(HttpStatus.CREATED)
    Passenger createPassenger(@RequestBody Passenger passenger){
        return repository.save(passenger);
    }

    @GetMapping("/passengers/{id}")
    Passenger findPassenger(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @PatchMapping("/passengers/{id}")
    Passenger patchPassenger(@RequestBody Map<String, String> updates, @PathVariable Long id){
        return repository.findById(id)
                .map(passenger -> {
                    String name = updates.get("name");

                    if(null != name){
                        passenger.setName(name);
                    }

                    Country country = countriesMap.get(updates.get("country"));

                    if(null != country){
                        passenger.setCountry(country);
                    }

                    String isRegistered = updates.get("isRegistered");

                    if(null != isRegistered){
                        passenger.setIsRegistered(isRegistered.equalsIgnoreCase("true") ? true : false);
                    }

                    return repository.save(passenger);
                })
                .orElseGet(() -> {
                    throw new PassengerNotFoundException(id);
                });
    }

    @DeleteMapping("/passengers/{id}")
    void deletePassenger(@PathVariable Long id){
        repository.deleteById(id);
    }
}
