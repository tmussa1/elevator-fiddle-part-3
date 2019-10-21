package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.api.Person;

public class DeliveryPerson extends Person implements Passenger{

    private int currentFloor;
    private int destinationFloor;
    private Address address;
    private static int firstFloor = 1;


    public DeliveryPerson(String firstName, String lastName, Address address){
        super(firstName, lastName, address);
        this.currentFloor = 1;
        this.destinationFloor = Building.UNDEFINED_FLOOR;
        this.address = address;
    }
    @Override
    public int getDestination() {
        return destinationFloor;
    }

    @Override
    public void setDestination(int destinationFloor) {
      this.destinationFloor = destinationFloor;
    }

    @Override
    public int getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public void setCurrentFloor(int currentFloor) {
       this.currentFloor = currentFloor;
    }

    @Override
    public void arriveOnFloor(int arrivalFloor) {
        this.currentFloor = arrivalFloor;
        this.destinationFloor = firstFloor;
    }

    public String ringApartment(Apartment apt){
        return "Ringing floor: "+apt.getAddress().getFloorId()+", apt: "+apt.getAddress().getApartmentId();
    }

    public int getDoorKey() {
        return address.hashCode();
    }

}
