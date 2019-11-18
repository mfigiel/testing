package com.testing.api.builders.person;

import com.testing.api.resource.PersonApi;

public class SamplePersonBuilder implements PersonBuilder {

    private PersonApi personApi;

    public SamplePersonBuilder() {
        this.personApi = new PersonApi();
    }
    @Override
    public void buildName() {
        personApi.setName("Michal");
    }

    @Override
    public void buildSurname() {
        personApi.setSurname("Kowalski");
    }

    public PersonApi getPerson() {
        return this.personApi;
    }
}
