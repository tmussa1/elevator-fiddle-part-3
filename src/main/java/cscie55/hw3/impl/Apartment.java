package cscie55.hw3.impl;

import cscie55.hw3.exception.TooManyResidentsException;

import java.util.ArrayList;
import java.util.List;

public class Apartment {

	private final int id;

	private final int floorId;

	private final int bldgId = Building.ID;

	private final Address address;

	public static final int MAX_RESIDENTS = 5;

	private List<Resident> residents = new ArrayList<>();

	public List<Resident> getResidents() {
		return residents;
	}

	/**
	 * Constructor
	 * @param id  the id of the apartment
	 * @param floorId the id of the Floor where this apartment is located.
	 * @see Floor
	 */
	public Apartment(int id, int floorId){
		this.id = id;
		this.floorId = floorId;
		this.address = new Address(Building.ID,floorId,id);
	}

	public void addResident(Resident resident) throws TooManyResidentsException {
		if(residents.size() < MAX_RESIDENTS){
			residents.add(resident);
		}
		else{
			throw new TooManyResidentsException();
		}
	}

	public Address getAddress(){
		return  this.address;
	}

	public int getId() {
		return id;
	}

	public int getFloorId() {
		return floorId;
	}

	public int getKey(){
		//TODO: implement by returning the value of hashCode()
	}
	@Override public int hashCode(){
		//TODO: Implement hashCode()
	}
}
