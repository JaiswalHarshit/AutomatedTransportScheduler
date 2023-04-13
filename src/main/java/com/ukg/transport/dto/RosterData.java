package com.ukg.transport.dto;

public class RosterData {

	private String routeId;
	private String employeeId;
	private String name;
	private String contact;
	private String address;
	private int dropNumber;
	private String gender;
	private Integer age;
	private String costCenter;
	private boolean guardRequired;
	private String interest;

	public RosterData() {

	}

	public RosterData(String routeId, String employeeId, String name, String contact, String address, int dropNumber, String gender, Integer age, String costCenter, boolean guardRequired, String interest) {

		this.routeId = routeId;
		this.employeeId = employeeId;
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.dropNumber = dropNumber;
		this.gender = gender;
		this.age = age;
		this.costCenter = costCenter;
		this.guardRequired = guardRequired;
		this.interest = interest;
	}

	public String getRouteId() {

		return routeId;
	}

	public void setRouteId(String routeId) {

		this.routeId = routeId;
	}

	public String getEmployeeId() {

		return employeeId;
	}

	public void setEmployeeId(String employeeId) {

		this.employeeId = employeeId;
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

	public int getDropNumber() {

		return dropNumber;
	}

	public void setDropNumber(int dropNumber) {

		this.dropNumber = dropNumber;
	}

	public String getGender() {

		return gender;
	}

	public void setGender(String gender) {

		this.gender = gender;
	}

	public Integer getAge() {

		return age;
	}

	public void setAge(Integer age) {

		this.age = age;
	}

	public String getCostCenter() {

		return costCenter;
	}

	public void setCostCenter(String costCenter) {

		this.costCenter = costCenter;
	}

	public boolean isGuardRequired() {

		return guardRequired;
	}

	public void setGuardRequired(boolean guardRequired) {

		this.guardRequired = guardRequired;
	}

	public String getInterest() {

		return interest;
	}

	public void setInterest(String interest) {

		this.interest = interest;
	}
}