package com.ukg.transport.dto;

public class TransportRoute {

	private Integer drop1;
	private Integer drop2;
	private Integer drop3;
	private Integer total;

	public Integer getDrop1() {

		return drop1;
	}

	public void setDrop1(Integer drop1) {

		this.drop1 = drop1;
	}

	public Integer getDrop2() {

		return drop2;
	}

	public void setDrop2(Integer drop2) {

		this.drop2 = drop2;
	}

	public Integer getDrop3() {

		return drop3;
	}

	public void setDrop3(Integer drop3) {

		this.drop3 = drop3;
	}

	public Integer getTotal() {

		return total;
	}

	public void setTotal(Integer total) {

		this.total = total;
	}

	@Override
	public String toString() {

		return "TransportRoute {" +
				"drop1=" + drop1 +
				", drop2=" + drop2 +
				", drop3=" + drop3 +
				", total=" + total +
				"} \n";
	}
}
