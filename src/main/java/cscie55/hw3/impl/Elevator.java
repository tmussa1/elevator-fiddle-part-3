package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.exception.ElevatorFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * This is an elevator that moves between floors, picks up and drops off passengers on its way
 * @author Tofik Mussa
 */
public class Elevator {

	private int currentFloor;
	private Floor[] floors;
	public final static int CAPACITY = 10;
	private List<List<Passenger>> passengers = new ArrayList<>();
	private enum direction{ UP, DOWN};
	private direction upOrDown;
	private int incrementFloor = 1;
    private static final Logger LOGGER = LogManager.getLogger(Elevator.class.getName());

	/**
	 * An elevator constructor initializes empty spots for incoming passengers
	 * @param floors
	 */
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

	/**
	 * This methods moves between floors, unloads passengers destined to a specific floor and picks up passengers
	 * waiting to board. It also empties the elevator when completing a full cycle
	 */
	public void move() {
		this.incrementFloor++;
		int fullTrip = 2 * Building.TOTAL_NUM_OF_FLOORS;

		if((this.incrementFloor % fullTrip) < Building.TOTAL_NUM_OF_FLOORS){
			this.upOrDown = direction.UP;
			this.currentFloor = incrementFloor % (Building.TOTAL_NUM_OF_FLOORS + 1);
			unloadPassengers();
			addPassengers(passengers.get(currentFloor - 1), floors[currentFloor - 1].getPassengersGoingUp());
		} else {
			this.upOrDown = direction.DOWN;
			this.currentFloor = Building.TOTAL_NUM_OF_FLOORS - (this.incrementFloor % Building.TOTAL_NUM_OF_FLOORS);
			unloadPassengers();
			addPassengers(passengers.get(currentFloor - 1), floors[currentFloor - 1].getDownwardBound());
		}

		if((fullTrip - incrementFloor) % fullTrip == 1){
			emptyElevators();
			incrementFloor = currentFloor;
		}
	}

	/**
	 * Empties elevator upon full cycle
	 */
	private void emptyElevators() {
		int passengerSize = passengers.size();
		passengers.clear();
		for(int i = 0; i < passengerSize; i++){
			passengers.add(new ArrayList<>());
		}
	}

	/**
	 * Adds passengers waiting to board
	 * @param passengersEachFloor
	 * @param passengersWaiting
	 */
	private void addPassengers(List<Passenger> passengersEachFloor, ArrayDeque<Passenger> passengersWaiting) {

		if(!passengersWaiting.isEmpty() && passengersWaiting.size() > 0){
			while(getNumberPassengers() < CAPACITY && passengersWaiting.size() > 0){
				passengersEachFloor.add(passengersWaiting.poll());
			}
			passengers.set((currentFloor - 1), passengersEachFloor);
		}

	}

	/**
	 * Boards passengers
	 * @param passenger
	 * @throws ElevatorFullException
	 */
	public void boardPassenger(Passenger passenger) throws ElevatorFullException{
		try{
			for(int i = currentFloor - 1; i < passenger.getDestination(); i++){
				passengers.get(i).add(passenger);
				if(passengers.get(i).size() > CAPACITY){
					throw new ElevatorFullException("More than 10 people can not board");
				}
			}
			passenger.setCurrentFloor(Building.UNDEFINED_FLOOR);
		} catch(ElevatorFullException elevatorException){
			LOGGER.error("Error " + elevatorException.getCause());
		}
	}

	/**
	 * Unloads passengers who are trying to get off
	 */
	private void unloadPassengers() {
		List<Passenger> passengersStillInElev = new ArrayList<>();
		List<Passenger> pass = passengers.get(currentFloor - 1);

		for(int i = 0; i < pass.size(); i++){
			if(pass.get(i).getDestination()   != currentFloor){
				passengersStillInElev.add(pass.get(i));
			}
			if(pass.get(i) instanceof Resident) {
				floors[currentFloor - 1].getResidents().add(pass.get(i));
			}
		}
		passengers.remove(pass);
		passengers.add((currentFloor - 1), passengersStillInElev);
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Prints the status of an elevator
	 * @return - status of an elevator
	 */
	public String toString() {
		return "Elevator is going " + upOrDown.toString() +
				" current Floor is "+currentFloor +" : "+ getNumberPassengers()+" passengers";
	}

	/**
	 * Returns number of passengers currently as the elevator moves between floors
	 * @return - number of passengers
	 */
	public int getNumberPassengers() {
		return passengers.get(currentFloor - 1).size();
	}

}
