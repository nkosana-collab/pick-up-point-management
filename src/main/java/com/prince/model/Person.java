package com.prince.model;

public abstract class Person {
    private final String NAME;
    private final String ID;

    public Person(String name, String idNumber) {
        this.NAME = name;
        this.ID = idNumber;
    }

    public String getName() {
        return NAME;
    }

    public String getInNumber() {
        return ID;
    }
}
