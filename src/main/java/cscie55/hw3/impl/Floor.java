package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.exception.NoSuchApartmentException;

import java.util.ArrayDeque;

public class Floor {

    private int id;
    private Apartment[] apartments = new Apartment[4];
    private int passengersWaiting = 0;
    //TODO: declate and initialize 3 ArrayDeques, one for residents, upwardBound and downwardBound


    /**
     * Constructor
     * @param id the id of this Floor. Note that the Building has a max of 7 FLoors
     * @see Building, Building.TOTAL_NUM_OF_FLOORS
     */
    public Floor(int id) {
        this.id = id;
        // Creates 4 Apartments for this floor (that is, each floor will have 4 Apartments)
        for (int i = 0; i < apartments.length; i++) {
            apartments[i] = new Apartment(i, id);
        }
    }

    /*
        There is no limit on the number of passengers who can wait
 */
    public void callElevator(Passenger p) {
        // TODO: implement operations that place the Passengers who want to get on the Elevator on this floor
        // TODO: use this method to decide if the passenger should be put into upwardBound or downwardBound ArrayDeque.

    }

    public int getPassengersWaiting() {
        // TODO: implement a method that returns the total number of people who have called the Elevator
    }


    public Apartment getApartment(int apartmentNumber) throws NoSuchApartmentException {
        return apartments[apartmentNumber];
    }

    public ArrayDeque<Passenger> getPassengersGoingUp() {
        // TODO: implement
    }

    public ArrayDeque<Passenger> getDownwardBound() {
        // TODO: implement
    }

    public ArrayDeque<Passenger> getResidents() {
        // TODO: implement
    }

}
