package com.testing.api.resource;

import com.testing.api.validation.AddressValidation;
import com.testing.logging.Loggable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonApi {

    @Loggable
    @NonNull
    private String name;
    @Loggable
    @NonNull
    private String surname;
    @AddressValidation
    private AddressApi address;

    @Override
    public String toString() {
        return "PersonApi{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address +
                '}';
    }
}
