package cscie55.hw3.impl;

/**
 * An address or location of person, resident and delivery person entities
 * @author Tofik Mussa
 */
public class Address {

	private int buildingId;
	private int floorId;
	private int apartmentId;

	/**
	 *
	 * @param buildingId
	 * @param floorId
	 * @param apartmentId
	 */
	public Address(int buildingId, int floorId, int apartmentId){
		this.buildingId = buildingId;
		this.floorId = floorId;
		this.apartmentId = apartmentId;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public int getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(int apartmentId) {
		this.apartmentId = apartmentId;
	}

	/**
	 * Unique identifier(hash) used for limiting access to entering apartment
	 * @return a hash
	 */
	@Override
	public int hashCode(){
		int prime = 31;
		int result = 1;
		result = prime * result + apartmentId;
		result = prime * result + floorId;
		result = prime * result + buildingId;
		return result;
	}
}
