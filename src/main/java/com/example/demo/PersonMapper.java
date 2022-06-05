package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class PersonMapper {

    private final ModelMapper modelMapper;

    public PersonMapper (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonDTO toPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person toEntity(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }
}
