package com.prince.model;

import com.prince.database.DataBaseConnector;

import java.util.ArrayList;


public class Volunteer extends Person{

    private final DataBaseConnector DATABASE;

    public Volunteer(String name, String idNumber) {
        super(name, idNumber);
        this.DATABASE = new DataBaseConnector();
    }

    public boolean addPickUpPoint(String name, String address) {

        try {
           DATABASE.addPickUpPoint(new PickUpPoint(name, address, 0));
           return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    public boolean addCrates(String pickUpPointName, int crates) {

        PickUpPoint pickUpPoint = DATABASE.getPickUpPoint(pickUpPointName);
        if(pickUpPoint.addUsage(crates)) {
            DATABASE.updatePickUpPoint(pickUpPoint);
        }
        return false;
    }

    public void viewCapacities() {
        ArrayList<PickUpPoint> stores = DATABASE.getPickUpPoints();

        for(PickUpPoint store: stores) {
            System.out.println(store.getNAME() + " | " + store.getADDRESS() + " | " +store.getCurrentUsage() + "%");
        }

    }

}
