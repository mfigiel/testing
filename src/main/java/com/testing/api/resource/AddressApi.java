package com.testing.api.resource;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressApi {

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

}
