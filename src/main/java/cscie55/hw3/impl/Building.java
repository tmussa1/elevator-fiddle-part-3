package cscie55.hw3.impl;


public class Building {

	public static final int ID = 1;
	public static final int TOTAL_NUM_OF_FLOORS= 7;
	public static int UNDEFINED_FLOOR = -1;
	private Elevator elevator;

	private static Floor[] floors = new Floor[TOTAL_NUM_OF_FLOORS];

	public Building() {
		createFloors();
		elevator = new Elevator(floors);
	}

	public Elevator getElevator() {
		return elevator;
	}

	private void createFloors(){
		for(int i = 0; i < TOTAL_NUM_OF_FLOORS; i++){
			floors[i] = new Floor(i);
		}
	}

	public Floor getFloor(int floorNumber) {
		return floors[floorNumber];
	}
	public Floor[] getFloors(){
		return floors;
	}

}
