package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
@AllArgsConstructor
public class PersonService {
    private final BiFunction<PersonRepository, Person, Mono<Person>> validateBeforeInsert
            = (repo, person) -> repo.findByName(person.getName());
    @Autowired
    private PersonRepository repository;
    private PersonMapper personMapper;

    public Flux<PersonDTO> listAll() {
        return repository.findAll().map(person -> personMapper.toPersonDTO(person));
    }

    public Mono<Void> insert(Mono<Person> personMono) {
        return personMono
                .flatMap(person -> validateBeforeInsert.apply(repository, person))
                .switchIfEmpty(Mono.defer(() -> personMono.doOnNext(repository::save)))
                .then();
    }

    public Mono<PersonDTO> getPerson(String id){
        return repository.findById(id).map(person -> personMapper.toPersonDTO(person));
    }
//private final PersonRepository repository;
//
//public Flux<Person> listAll(){
//    return repository.findAll();
//  }
}
