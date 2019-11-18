package com.testing.repository;

import com.testing.dto.PersonDto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface PersonRepository extends JpaRepository<PersonDto, Long> {

    PersonDto findBySurname(String surname);

    @Transactional
    Long removeBySurname(String surname);
}
