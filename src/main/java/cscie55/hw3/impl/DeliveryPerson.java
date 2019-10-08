package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.api.Person;


// TODO: add fields to DeliveryPerson class similar to Resident class

public class DeliveryPerson extends Person implements Passenger{

    public DeliveryPerson(String firstName, String lastName, Address address){
        super(firstName, lastName, address);
        // TODO: add initializers to constructorOOR;
    }
    @Override
    public int getDestination() {
    // TODO: implement
        return 0;
    }

    @Override
    public void setDestination(int destinationFloor) {
     // TODO: implement
    }

    @Override
    public int getCurrentFloor() {
        // TODO: implement
        return 0;
    }

    @Override
    public void setCurrentFloor(int currentFloor) {
    // TODO: implement
    }

    @Override
    public void arriveOnFloor(int arrivalFloor) {
     // TODO: implement. Set the state of the DeliveryPerson. Their currentFloor is the arrival flor.
        // TODO: but the delivery person must return to floor 1 so set destination accordingly.
    }

    public String ringApartment(Apartment apt){
        return "Ringing floor: "+apt.getAddress().getFloorId()+", apt: "+apt.getAddress().getApartmentId();
    }

    public int getDoorKey() {
        // TODO: implement
        return 0;
    }
}
