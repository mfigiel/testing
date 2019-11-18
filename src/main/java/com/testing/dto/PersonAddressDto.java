package com.testing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "person_address")
public class PersonAddressDto {

    @Id
    private Long id;
    private Date creationDate = new Date();
    private String city;
    private String street;
    private Integer houseNumber;
    private Integer flatNumber;
    private String zipCode;

    @Override
    public String toString() {
        return "PersonAddressDto{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", flatNumber=" + flatNumber +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
