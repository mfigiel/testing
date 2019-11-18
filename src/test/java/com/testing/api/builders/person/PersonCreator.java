package com.testing.api.builders.person;

import com.testing.api.resource.PersonApi;

public class PersonCreator {

    private PersonBuilder personBuilder;

    public PersonCreator(PersonBuilder personBuilder) {
        this.personBuilder = personBuilder;
    }

    public PersonApi getPerson() {
        this.personBuilder.buildName();
        this.personBuilder.buildSurname();
        return this.personBuilder.getPerson();
    }
}
