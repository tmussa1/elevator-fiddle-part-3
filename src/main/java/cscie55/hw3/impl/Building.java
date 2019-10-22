package cscie55.hw3.impl;

/**
 * This building object is a container for floors and elevators
 * @author Tofik Mussa
 */
public class Building {

	public static final int ID = 1;
	public static final int TOTAL_NUM_OF_FLOORS= 7;
	public static int UNDEFINED_FLOOR = -1;
	private Elevator elevator;

	private static Floor[] floors = new Floor[TOTAL_NUM_OF_FLOORS];

	/**
	 * Creates floors and an elevator upon initialization
	 */
	public Building() {
		createFloors();
		elevator = new Elevator(floors);
	}

	public Elevator getElevator() {
		return elevator;
	}

	/**
	 * Creates floors
	 */
	private void createFloors(){
		for(int i = 0; i < TOTAL_NUM_OF_FLOORS; i++){
			floors[i] = new Floor(i);
		}
	}

	/**
	 * Get a floor by its id
	 * @param floorNumber
	 * @return - a floor object
	 */
	public Floor getFloor(int floorNumber) {
		return floors[floorNumber];
	}

	/**
	 * Gets all floors in the building
	 * @return - all floors in the building
	 */
	public Floor[] getFloors(){
		return floors;
	}

}
