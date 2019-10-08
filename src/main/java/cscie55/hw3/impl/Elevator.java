package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.exception.ElevatorFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Elevator {

	private int currentFloor = 1;
	private Floor[] floors;
	public final static int CAPACITY = 10;
	private List<List<Passenger>> passengers = new ArrayList<>();

    private static final Logger LOGGER = LogManager.getLogger(Elevator.class.getName());

	/**
	 * Constructor
	 * This method populates the new List<List<Passenger>> object to track passengers onboard
	 *
	 * @param floors floors is the array of Floor objects, as in HW 2.
	 */
	public Elevator(Floor[] floors){
		currentFloor = 1;;
		this.floors = floors;
		int n = 0;
		while (n < Building.TOTAL_NUM_OF_FLOORS){
			this.passengers.add(new ArrayList<Passenger>());
			n++;
		}
	}

		// TODO: as in HW 1 & 2, the elevator moves up, dropping passengers along the way
		/**
		 * moves the elevator
		 */
		public void move() {

		}

	public void boardPassenger(Passenger passenger) throws ElevatorFullException {
		//TODO: get destination of passenger and use it to add to passengers traveling to that floor
		// throw Exception if > 10 try to board
		// Use the LOGGER.error() method to report your error to the log file

	}

	private void unloadPassengers() {
		// TODO: remove passengers destined for the floor at this stop. Add Residents to the residents collection.

	}

	public int getCurrentFloor() {
			//TODO: implement
	}

	private void setCurrentFloor(int floorNum) {
		// TODO: Optional. might be handy. Delete if you don't want it
		currentFloor = floorNum;
	}

	public String toString() {
		// TODO: impl toString()
		return "Floor "+currentFloor +": "+ getNumberPassengers()+" passengers";
	}

	public int getNumberPassengers() {
		//TODO: calculate and return the number of passengers on board the elevator

	}

}
