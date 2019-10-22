package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.exception.NoSuchApartmentException;

import java.util.ArrayDeque;

/**
 * Floor class
 * @author Tofik Mussa
 */
public class Floor {

    private int id;
    private Apartment[] apartments = new Apartment[4];
    private ArrayDeque<Passenger> residents;
    private ArrayDeque<Passenger> upwardBound;
    private ArrayDeque<Passenger> downwardBound;

    /**
     * initializes apartments and collections to be used for tracking passengers
     * @param id
     */
    public Floor(int id) {
        this.id = id;
        for (int i = 0; i < apartments.length; i++) {
            apartments[i] = new Apartment(i, id);
        }
        this.residents = new ArrayDeque<>();
        this.upwardBound = new ArrayDeque<>();
        this.downwardBound = new ArrayDeque<>();
    }

    /**
     * A passenger requests to go to a destination using this method
     * @param p
     */
    public void callElevator(Passenger p) {
        if(this.id <= p.getDestination()){
            this.upwardBound.add(p);
        } else {
            this.downwardBound.add(p);
        }
    }

    /**
     * Returns apartment by its number
     * @param apartmentNumber
     * @return
     * @throws NoSuchApartmentException
     */
    public Apartment getApartment(int apartmentNumber) throws NoSuchApartmentException {
        return apartments[apartmentNumber];
    }

    public ArrayDeque<Passenger> getPassengersGoingUp() {
        return upwardBound;
    }

    public ArrayDeque<Passenger> getDownwardBound() {
        return downwardBound;
    }

    public ArrayDeque<Passenger> getResidents() {
        return residents;
    }

    /**
     * Returns passengers going in both directions
     * @return - passengers going in both directions
     */
    public int getPassengersWaiting() {
        return this.upwardBound.size() + this.downwardBound.size();
    }
}
