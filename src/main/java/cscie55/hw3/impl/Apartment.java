package cscie55.hw3.impl;

import cscie55.hw3.exception.TooManyResidentsException;

import java.util.ArrayList;
import java.util.List;

/**
 * Floors consist of apartments and an elevator
 * @author Tofik Mussa
 */
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
	 *
	 * @param id
	 * @param floorId
	 */
	public Apartment(int id, int floorId){
		this.id = id;
		this.floorId = floorId;
		this.address = new Address(Building.ID,floorId,id);
	}

	/**
	 * Adds residents to apartment
	 * @param resident
	 * @throws TooManyResidentsException
	 */
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
		return hashCode();
	}

	/**
	 * Returns the unique identifier(hash) used to access the apartment
	 * @return
	 */
	@Override
	public int hashCode(){
		return address.hashCode();
	}

}
