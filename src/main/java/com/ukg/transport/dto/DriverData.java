package com.ukg.transport.dto;

public class DriverData {

	private String driverId;
	private String name;
	private String contact;
	private String address;
	private String pincode;
	private String locationId;
	private String zoneId;
	private String latitude;
	private String longitude;

	public DriverData(String driverId, String name, String contact, String address, String pincode, String locationId, String zoneId, String latitude, String longitude) {

		this.driverId = driverId;
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.pincode = pincode;
		this.locationId = locationId;
		this.zoneId = zoneId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getDriverId() {

		return driverId;
	}

	public void setDriverId(String driverId) {

		this.driverId = driverId;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getContact() {

		return contact;
	}

	public void setContact(String contact) {

		this.contact = contact;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getPincode() {

		return pincode;
	}

	public void setPincode(String pincode) {

		this.pincode = pincode;
	}

	public String getLocationId() {

		return locationId;
	}

	public void setLocationId(String locationId) {

		this.locationId = locationId;
	}

	public String getZoneId() {

		return zoneId;
	}

	public void setZoneId(String zoneId) {

		this.zoneId = zoneId;
	}

	public String getLatitude() {

		return latitude;
	}

	public void setLatitude(String latitude) {

		this.latitude = latitude;
	}

	public String getLongitude() {

		return longitude;
	}

	public void setLongitude(String longitude) {

		this.longitude = longitude;
	}
}
