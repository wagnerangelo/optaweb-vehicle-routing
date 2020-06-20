package org.optaweb.vehiclerouting.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class Well implements WellInterface  {

	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "name")
	private String name;
    @JsonProperty(value = "potential")
	private Long potential;
	@JsonProperty(value = "location")
	private Location location;

	public Well(long id, String name, Long potential, Location location) {
		this.id = id;
		this.name = name;
		this.potential = potential;
		this.location = location;
	}

    @Override
	public String getName() {
        return name;
    }

    @Override
	public void setName(String name) {
        this.name = name;
    }

	@Override
	public Long getPotential() {
		return potential;
	}

	@Override
	public void setPotential(Long potential) {
		this.potential = potential;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}


}