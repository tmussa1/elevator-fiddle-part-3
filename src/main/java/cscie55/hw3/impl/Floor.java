package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.exception.NoSuchApartmentException;

import java.util.ArrayDeque;
import java.util.Collection;

public class Floor {

    private int id;
    private Apartment[] apartments = new Apartment[4];
    private Collection<Passenger> residents;
    private Collection<Passenger> upwardBound;
    private Collection<Passenger> downwardBound;

    public Floor(int id) {
        this.id = id;
        for (int i = 0; i < apartments.length; i++) {
            apartments[i] = new Apartment(i, id);
        }
        this.residents = getResidents();
        this.upwardBound = getPassengersGoingUp();
        this.downwardBound = getDownwardBound();
    }

    public void callElevator(Passenger p) {
        if(this.id < p.getDestination()){
            this.upwardBound.add(p);
        } else if(this.id > p.getDestination()){
            this.downwardBound.add(p);
        }
    }

    public Apartment getApartment(int apartmentNumber) throws NoSuchApartmentException {
        return apartments[apartmentNumber];
    }

    public ArrayDeque<Passenger> getPassengersGoingUp() {
        return new ArrayDeque<>();
    }

    public ArrayDeque<Passenger> getDownwardBound() {
        return new ArrayDeque<>();
    }

    public ArrayDeque<Passenger> getResidents() {
       return new ArrayDeque<>();
    }

    public int getPassengersWaiting() {
        return this.upwardBound.size() + this.downwardBound.size();
    }
}
