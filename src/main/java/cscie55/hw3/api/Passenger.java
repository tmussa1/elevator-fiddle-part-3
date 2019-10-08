package cscie55.hw3.api;

public interface Passenger {

	int getDestination();

	void setDestination(int destinationFloor);

	int getCurrentFloor();

	void setCurrentFloor(int currentFloor);

	void arriveOnFloor(int arrivalFloor);

}
