package com.prince;

import com.prince.model.Volunteer;
import com.prince.service.Processor;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class UserInterface
{
    private static Scanner scanner;

    public static void main( String[] args ) {
        scanner = new Scanner(System.in);

        System.out.println("WELCOME TO SERVING\n");
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("ID: ");
        String id = scanner.next();

        Processor processor = new Processor(new Volunteer(name,id));

        do {
            System.out.println("\nCommand: ");
        } while(processor.process(scanner.next()));

        System.out.println("Goodbye, and thank you for your service.");

        scanner.close();

    }
}
