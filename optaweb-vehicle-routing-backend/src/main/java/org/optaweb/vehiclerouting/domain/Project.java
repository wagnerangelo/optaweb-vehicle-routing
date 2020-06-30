package org.optaweb.vehiclerouting.domain;


public class Project implements ProjectInterface {

	private String name;

	@Override

	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
