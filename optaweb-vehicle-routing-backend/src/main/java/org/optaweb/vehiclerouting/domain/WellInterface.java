package org.optaweb.vehiclerouting.domain;

public interface WellInterface {

	long getId();

	Location getLocation();

	String getName();

	void setName(String name);

	Long getPotential();

	void setPotential(Long potencial);
}