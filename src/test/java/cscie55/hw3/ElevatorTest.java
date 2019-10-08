package cscie55.hw3;


import cscie55.hw3.api.Passenger;
import cscie55.hw3.exception.ElevatorFullException;
import cscie55.hw3.exception.KeyDoesNotFitException;
import cscie55.hw3.exception.NoSuchApartmentException;
import cscie55.hw3.impl.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ElevatorTest {

    Building building;
    Resident resident1;
    Resident resident2;
    Address a;

    private static final Logger LOGGER = LogManager.getLogger(ElevatorTest.class.getName());

    @Before
    public void setUp() throws Exception {
        building = new Building();
        a = new Address(Building.ID, 2, 4);
        resident1 = new Resident("Lucy", "MacGillicuty", a);
        resident2 = new Resident("Rickie", "Ricardo", a);
        ((Passenger) resident1).setDestination(resident1.getAddress().getFloorId());
        ((Passenger) resident2).setDestination(resident2.getAddress().getFloorId());
    }

    @Test
    public void testElevatorCreation() {
        Elevator elevator = building.getElevator();
        assertEquals(0, elevator.getNumberPassengers());
    }

    // Don't board any passengers. Just check that the elevator moves up and down correctly.
    @Test
    public void testElevatorMotion() {
        //Building building = new Building();
        Elevator elevator = building.getElevator();
        int expectedFloorNumber = 1;
        // Go to the top
        while (expectedFloorNumber < Building.TOTAL_NUM_OF_FLOORS) {
            checkElevator(elevator, expectedFloorNumber, 0);
            elevator.move();
            expectedFloorNumber++;
            checkElevator(elevator, expectedFloorNumber, 0);
        }
        assertEquals(Building.TOTAL_NUM_OF_FLOORS, expectedFloorNumber);
        // Go back to the bottom
        while (expectedFloorNumber > 1) {
            checkElevator(elevator, expectedFloorNumber, 0);
            elevator.move();
            expectedFloorNumber--;
            checkElevator(elevator, expectedFloorNumber, 0);
        }
        assertEquals(1, expectedFloorNumber);
    }

    // Check that passengers get on and off correctly.
    @Test
    public void testLoadUnload() throws ElevatorFullException {
        //  Building building = new Building();
        Elevator elevator = building.getElevator();
        checkElevator(elevator, 1, 0);
        // Add passengers and disembark them on the way up
        List<Passenger> passngrs = new ArrayList<>();//Resident();
        int i = 6;
        while (i > 0) {
            Address a = new Address(Building.ID, i, i % 3);
            Resident r = new Resident("F" + i, "L" + i, a);
            switch (i) {
                case 1:
                    r.setDestination(3);
                    break;
                case 2:
                    r.setDestination(4);
                    break;
                case 3:
                    r.setDestination(4);
                    break;
                case 4:
                    r.setDestination(6);
                    break;
                case 5:
                    r.setDestination(6);
                    break;
                case 6:
                    r.setDestination(6);
                    break;
                default:
                    break;
            }
            passngrs.add(r);
            i--;
        }
        elevator.boardPassenger(passngrs.get(0));
        elevator.boardPassenger(passngrs.get(1));
        elevator.boardPassenger(passngrs.get(2));
        elevator.boardPassenger(passngrs.get(3));
        elevator.boardPassenger(passngrs.get(4));
        elevator.boardPassenger(passngrs.get(5));
        checkElevator(elevator, 1, 6);
        elevator.move();
        checkElevator(elevator, 2, 6);
        elevator.move();
        checkElevator(elevator, 3, 5);
        elevator.move();
        checkElevator(elevator, 4, 3);
        elevator.move();
        checkElevator(elevator, 5, 3);
        elevator.move();
        checkElevator(elevator, 6, 0);
    }

    @Test
    public void elevatorFull() {
        Building building = new Building();
        Elevator elevator = building.getElevator();
        // Have enough people waiting on the 4th floor to exceed elevator capacity by 50%
        int waiting = (int) (Elevator.CAPACITY * 1.5);
        for (int i = 0; i < waiting; i++) {
            Address addr = new Address(1, 5, 2);
            Passenger p = new Resident("Joan_" + i, "Number_" + i, addr);
            p.setDestination(5);
            building.getFloor(3).callElevator(p);
        }
        // Move to 4, checking state
        elevator.move();
        checkElevator(elevator, 2, 0);
        elevator.move();
        checkElevator(elevator, 3, 0);
        elevator.move();
        // Should have filled the elevator, leaving 5 people on floor 3
        checkElevator(elevator, 4, Elevator.CAPACITY);
        elevator.move();
        checkElevator(elevator, 5, 0);
        assertEquals(waiting - Elevator.CAPACITY, building.getFloor(3).getPassengersWaiting());
        waiting = waiting - 10;
        // Get to the ground floor
        while (elevator.getCurrentFloor() != 1) {
            elevator.move();
            if (elevator.getCurrentFloor() == 1) {
                checkElevator(elevator, elevator.getCurrentFloor(), 0);
            }
            else {
                //TODO: Because passengers carry dest of floor 5, the elevator is empty after that floor
                checkElevator(elevator, elevator.getCurrentFloor(), 0);//Elevator.CAPACITY);
            }
        }
        // Go back to 3
        while (elevator.getCurrentFloor() != 5) {
            elevator.move();
            if (elevator.getCurrentFloor() == 4) {
                // Check to see that the 5 remaining passengers boarded
                checkElevator(elevator, elevator.getCurrentFloor(), 5);
                assertEquals(0, building.getFloor(4).getPassengersWaiting());
            }
            else {
                checkElevator(elevator, elevator.getCurrentFloor(), 0);
            }
        }
    }

    @Test
    public void testFloorCreation() {
        Floor[] floors = building.getFloors();
        assert (floors.length == 7);
    }

    @Test
    public void testApartmentCreation() {
        Floor[] floors = building.getFloors();
        try {
            Apartment apartment = floors[1].getApartment(0);
            assert (apartment.getId() == 0);
            assert (apartment.getResidents().size() == 0);
        }
        catch (NoSuchApartmentException nsa) {
            LOGGER.error("["+nsa.getClass().getName()+"] The requested Apartment cannot be found.");
        }
    }


    @Test
    public void testElevatorBoardPassenger() {
        Elevator elevator = building.getElevator();
        try {
            elevator.boardPassenger((Passenger) resident1);
            elevator.boardPassenger((Passenger) resident2);
        } catch (ElevatorFullException efe) {
            LOGGER.error("["+efe.getClass().getName()+"] Cannot board passenger.");
        }
        assertEquals(2, elevator.getNumberPassengers());
    }

    @Test
    public void testArriveOnFloor() {
        Address addr = new Address(1, 6, 0);
        Passenger p = new Resident("Louis", "Armstrong", addr);
        p.setCurrrentFloor(1);
        p.setDestination(6);
        assertEquals(p.getCurrentFloor(),1);
        assertEquals(p.getDestination(),6);
        p.arriveOnFloor(6);
        assertEquals(p.getCurrentFloor(),6);
        assertEquals(p.getDestination(),-1);
    }

    @Test
    public void testEnterApartment() {
        Address addr = new Address(1, 5, 2);
        Passenger p = new Resident("Joan", "d'Arc", addr);
        int keyCode = ((Resident) p).getDoorKey();
        Floor f = building.getFloor(5);
        Apartment apt = null;
        try {
            apt = f.getApartment(2);
        }
        catch (NoSuchApartmentException nsa) {
            LOGGER.error("["+nsa.getClass().getName()+"] The requested Apartment cannot be found.");
        }
        if (apt != null) {
            try {
                assertEquals(apt.getResidents().size(), 0);
                ((Resident) p).enterApartment(apt, keyCode);
            }
            catch (KeyDoesNotFitException knf) {
                LOGGER.error("["+knf.getClass().getName()+"] Incorrect Apartment Key. Entry prohibited.");
            }
        }
        assertEquals(apt.getResidents().size(), 1);
        assertEquals(p.getCurrentFloor(), 5);
    }

    @Test
    public void testDeliveryPerson(){
        Address addr = new Address(4, 5, 2);
        DeliveryPerson dp = new DeliveryPerson("Jamie","Gottlieb", addr);
        dp.setCurrrentFloor(5);
        String ringMsg ="";

        Floor f = building.getFloor(5);
        Apartment apt = null;
        try {
            apt = f.getApartment(2);
            ringMsg = ((DeliveryPerson) dp).ringApartment(apt);
        }
        catch (NoSuchApartmentException nsa) {
            LOGGER.error("["+nsa.getClass().getName()+"] The requested Apartment cannot be found.");
        }
        assertEquals(ringMsg,"Ringing floor: 5, apt: 2");

    }

    //utility method just checks the results
    private void checkElevator(Elevator elevator, int floorNumber, int passengers) {
        assertEquals(floorNumber, elevator.getCurrentFloor());
        assertEquals(passengers, elevator.getNumberPassengers());
    }
}