package com.prince.service;

import com.prince.model.Volunteer;

public class Processor {

    private Volunteer volunteer;

    public Processor(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public boolean process(String argument) {

        String[] arguments = argument.split(" ");

        try {
            switch (arguments[0].toLowerCase()) {
                case "view":
                    volunteer.viewCapacities();
                    break;
                case "addcrate":
                    return volunteer.addCrates(arguments[1],Integer.parseInt(arguments[2]));
                case "addpickuppoint":
                    return volunteer.addPickUpPoint(arguments[0], arguments[1]);
                case "quit":
                    return false;
                default:
                    help();
            }
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            help();
        }

        return true;
    }

    public void help(){
        System.out.println("USER GUIDE: \n" +
                            "To add PickUp Point: 'addPickUpPoint [STORENAME] [STOREADDRESS]'\n" +
                            "To add a crate: 'addCrate [STORENAME] [AMOUNT]'\n" +
                            "To view state: 'View'\n" +
                            "To exit: 'Quit'");
    }
}
