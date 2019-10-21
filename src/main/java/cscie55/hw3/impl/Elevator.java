package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.exception.ElevatorFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Elevator {

	private int currentFloor;
	private Floor[] floors;
	public final static int CAPACITY = 10;
	private List<List<Passenger>> passengers = new ArrayList<>();
	private enum direction{ UP, DOWN};
	private direction upOrDown;
	private int incrementFloor = 1;
    private static final Logger LOGGER = LogManager.getLogger(Elevator.class.getName());

	public Elevator(Floor[] floors){
		currentFloor = 1;
		this.floors = floors;
		int n = 0;
		while (n < Building.TOTAL_NUM_OF_FLOORS){
			this.passengers.add(new ArrayList<Passenger>());
			n++;
		}
		this.upOrDown = direction.UP;
	}

	public void move() {
		this.incrementFloor++;

		if((this.incrementFloor % (2 * Building.TOTAL_NUM_OF_FLOORS)) < Building.TOTAL_NUM_OF_FLOORS){
			this.upOrDown = direction.UP;
			this.currentFloor = incrementFloor % (Building.TOTAL_NUM_OF_FLOORS + 1);
			addPassengers(passengers.get(currentFloor - 1), floors[currentFloor - 1].getPassengersGoingUp());
		} else {
			this.upOrDown = direction.DOWN;
			this.currentFloor = Building.TOTAL_NUM_OF_FLOORS - (this.incrementFloor % Building.TOTAL_NUM_OF_FLOORS);
			addPassengers(passengers.get(currentFloor - 1), floors[currentFloor - 1].getDownwardBound());
		}

		unloadPassengers();
	}

	private void addPassengers(List<Passenger> passengersEachFloor, ArrayDeque<Passenger> passengersWaiting) {

		if(!passengersWaiting.isEmpty() && passengersWaiting.size() > 0){
			while(getNumberPassengers() < CAPACITY){
				passengersEachFloor.add(passengersWaiting.poll());
			}
			passengers.set((currentFloor - 1), passengersEachFloor);
		}

	}

	public void boardPassenger(Passenger passenger) throws ElevatorFullException{
		try{
			passengers.get(passenger.getDestination() - 1).add(passenger);
			if(passengers.get(passenger.getDestination() - 1).size() > CAPACITY){
				throw new ElevatorFullException("More than 10 people can not board");
			}
			passenger.setCurrentFloor(Building.UNDEFINED_FLOOR);
		} catch(ElevatorFullException elevatorException){
			LOGGER.error("Error " + elevatorException.getCause());
		}
	}

	private void unloadPassengers() {

		List<Passenger> passengersElev = new ArrayList<>();

		for(Passenger passenger : passengers.get(currentFloor - 1)){

			if(passenger instanceof Resident) {
				floors[currentFloor - 1].getResidents().add(passenger);
			}
			if(passenger.getDestination()  == currentFloor){
				passengersElev = new ArrayList<>();
				break;
			} else {
				passengersElev = passengersStillInElevator(passengersElev, passenger);
			}
		}

		passengers.set(passengers.indexOf(passengers.get(currentFloor - 1)), passengersElev);
	}

	private List<Passenger> passengersStillInElevator(List<Passenger> passengersElev, Passenger passenger){
		passengersElev.add(passenger);
		return passengersElev;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public String toString() {
		return "Elevator is going " + upOrDown.toString() +
				" current Floor is "+currentFloor +" : "+ getNumberPassengers()+" passengers";
	}

	public int getNumberPassengers() {
		int passengerCount = 0;
		for(List<Passenger> passengersEachFloor : passengers){
			passengerCount += passengersEachFloor.size();
		}
		return passengerCount;
	}

}
