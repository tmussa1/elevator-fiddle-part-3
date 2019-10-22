package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.api.Person;

/**
 * A delivery person can enter a building but needs to ring alarm to get into an apartment
 * @author Tofik Mussa
 */
public class DeliveryPerson extends Person implements Passenger{

    private int currentFloor;
    private int destinationFloor;
    private Address address;
    private static int firstFloor = 1;

    /**
     *
     * @param firstName
     * @param lastName
     * @param address
     */
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

    /**
     * When a delivery person arrives on floor, the current floor gets updated. He has to go back to the first floor
     * after completing business so that will be his destination
     * @param arrivalFloor
     */
    @Override
    public void arriveOnFloor(int arrivalFloor) {
        this.currentFloor = arrivalFloor;
        this.destinationFloor = firstFloor;
    }

    /**
     * A delivery person rings alarm to get into apartment
     * @param apt
     * @return - ringing of alarm(a stub for now)
     */
    public String ringApartment(Apartment apt){
        return "Ringing floor: "+apt.getAddress().getFloorId()+", apt: "+apt.getAddress().getApartmentId();
    }

    /**
     * A delivery person has a door key of his own because he likely doesn't live in the same building
     * @return - door key
     */
    public int getDoorKey() {
        return address.hashCode();
    }

}
