package com.testing.api.resource;

import com.testing.api.validation.AddressValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientApi {

    private Long id;
    private String name;
    private String Surname;
    @AddressValidation
    private AddressApi address;

}
