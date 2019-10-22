package cscie55.hw3.api;

/**
 * Passenger interface
 * @author Tofik Mussa
 */
public interface Passenger {

	int getDestination();

	void setDestination(int destinationFloor);

	int getCurrentFloor();

	void setCurrentFloor(int currentFloor);

	void arriveOnFloor(int arrivalFloor);

}
