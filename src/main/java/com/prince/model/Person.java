package com.prince.model;

public abstract class Person {
    private String name;
    private String inNumber;

    public Person(String name, String idNumber) {
        this.name = name;
        this.inNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public String getInNumber() {
        return inNumber;
    }
}
