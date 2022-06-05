package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:8080/")
@RequestMapping("/personexcersice")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public Mono<Void> post(@RequestBody Mono<Person> personMono) {
        //return personService.insert(personMono);
        return Mono.empty();
    }

    @GetMapping("/get/person/{id}")
    public Mono<PersonDTO> getPerson(@PathVariable("id") String id) {
        return personService.getPerson(id);
    }

    @PutMapping
    public Mono<Void> update(@RequestBody Mono<Person> personMono) {
        return Mono.empty();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return Mono.empty();
    }

    @GetMapping("/get/persons")
    public Flux<PersonDTO> list() {
        return personService.listAll();
    }

}
