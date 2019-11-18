package com.testing.api.resource;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonAddressApi {

    @NotNull
    @Size(min=2, max=40)
    private String city;
    @NotNull
    @Size(min=2, max=40)
    private String street;
    private Integer houseNumber;
    private Integer flatNumber;
    @NonNull
    private String zipCode;

    @Override
    public String toString() {
        return "PersonAddressApi{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", flatNumber=" + flatNumber +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
