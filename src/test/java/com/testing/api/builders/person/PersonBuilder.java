package com.testing.api.builders.person;

import com.testing.api.resource.PersonApi;

public interface PersonBuilder {
    void buildName();
    void buildSurname();
    PersonApi getPerson();
}
