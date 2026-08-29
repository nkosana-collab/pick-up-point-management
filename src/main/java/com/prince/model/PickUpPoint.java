package com.prince.model;

public class PickUpPoint {

    private final String NAME;
    private final String ADDRESS;
    private int currentUsage;

    public PickUpPoint(String name, String address, int usage) {
        this.NAME = name;
        this.ADDRESS = address;
        this.currentUsage = usage;
    }

    public String getNAME() {
        return NAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public int getCurrentUsage() {
        return currentUsage;
    }

    public boolean addUsage(int amount) {
        if(this.currentUsage + amount <= 100 && amount > 0) {
            this.currentUsage += amount;
            return true;
        }

        return false;
    }
}
