package com.testing.repository;

import com.testing.dto.PersonAddressDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<PersonAddressDto, Long> {

}
