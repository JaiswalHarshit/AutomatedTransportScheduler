package com.ukg.transport.dto;

public class DistanceMatrix {

	private int sourceLocationId;
	private int destinationLocationId;
	private int distance;

	public int getSourceLocationId() {

		return sourceLocationId;
	}

	public void setSourceLocationId(int sourceLocationId) {

		this.sourceLocationId = sourceLocationId;
	}

	public int getDestinationLocationId() {

		return destinationLocationId;
	}

	public void setDestinationLocationId(int destinationLocationId) {

		this.destinationLocationId = destinationLocationId;
	}

	public int getDistance() {

		return distance;
	}

	public void setDistance(int distance) {

		this.distance = distance;
	}
}