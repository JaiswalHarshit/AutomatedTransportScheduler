package com.ukg.transport.dto;

public class EmployeeData {

	private String employeeID;
	private String  name;
	private String  contact;
	private String  gender;
	private String  locationId;
	private String  address;
	private String  pincode;
	private String  zoneId;
	private String  latitude;
	private String  longitude;
	private String  costCenter;
	private Integer age;
	private String interest;

	public EmployeeData(String employeeID, String name, String contact, String gender, String locationId, String address, String pincode, String zoneId, String latitude, String longitude, String costCenter, Integer age, String interest) {

		this.employeeID = employeeID;
		this.name = name;
		this.contact = contact;
		this.gender = gender;
		this.locationId = locationId;
		this.address = address;
		this.pincode = pincode;
		this.zoneId = zoneId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.costCenter = costCenter;
		this.age = age;
		this.interest = interest;
	}

	public String getEmployeeID() {

		return employeeID;
	}

	public void setEmployeeID(String employeeID) {

		this.employeeID = employeeID;
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

	public String getGender() {

		return gender;
	}

	public void setGender(String gender) {

		this.gender = gender;
	}

	public String getLocationId() {

		return locationId;
	}

	public void setLocationId(String locationId) {

		this.locationId = locationId;
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

	public String getCostCenter() {

		return costCenter;
	}

	public void setCostCenter(String costCenter) {

		this.costCenter = costCenter;
	}

	public Integer getAge() {

		return age;
	}

	public void setAge(Integer age) {

		this.age = age;
	}

	public String getInterest() {

		return interest;
	}

	public void setInterest(String interest) {

		this.interest = interest;
	}

	@Override
	public String toString() {

		return "Employee [emp_id = " + employeeID + ", name = " + name + ", contact = " + contact + ", gender = " + gender + ",empLocation=" + locationId
				+ ", empAddress = " + address + ", empPincode = " + pincode + ", empZoneID = " + zoneId + ", empLatitude = " + latitude + ", empLongitude = " + longitude + "]";
	}
}