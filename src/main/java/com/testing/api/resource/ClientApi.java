package com.testing.api.resource;

import com.testing.api.validation.AddressValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientApi {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String Surname;
    @AddressValidation
    private AddressApi address;

}
