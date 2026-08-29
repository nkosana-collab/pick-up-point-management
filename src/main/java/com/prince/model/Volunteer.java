package com.prince.model;

import com.prince.database.DataBaseConnector;

public class Volunteer extends Person{

    private final DataBaseConnector DATABASE;

    public Volunteer(String name, String idNumber) {
        super(name, idNumber);
        this.DATABASE = new DataBaseConnector();
    }

    public boolean addPickUpPoint(PickUpPoint store) {
        try {
            DATABASE.addStork(store.getName(), store.getAddress());
            return true;

        } catch (IllegalArgumentException){
            return false;
        }
    }

    public boolean addCrates(String pickUpPointName, int crates) {
        try {
            DATABASE.addStork(pickUpPointName, crates);
            return true;

        } catch (IllegalArgumentException){
            return false;
        }
    }

    public void viewCapacities() {

    }

}
