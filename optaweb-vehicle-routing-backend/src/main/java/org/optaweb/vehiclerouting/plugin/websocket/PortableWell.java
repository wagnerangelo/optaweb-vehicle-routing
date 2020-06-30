package org.optaweb.vehiclerouting.plugin.websocket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.optaweb.vehiclerouting.domain.Well;

@JsonIgnoreProperties(ignoreUnknown = true)
class PortableWell   {

	@JsonProperty(value = "id")
	private final long id;
	@JsonProperty(value = "name")
	private final String name;
    @JsonProperty(value = "potential")
	private final Long potential;
	@JsonProperty(value = "location")
	private final PortableLocation portableLocation;




	@JsonCreator
	PortableWell(@JsonProperty(value = "id") long id, @JsonProperty(value = "name") String name,  @JsonProperty(value = "potential") Long potential, @JsonProperty(value = "location")PortableLocation portableLocation) {
		this.id = id;
		this.name = name;
		this.potential = potential;
		this.portableLocation = portableLocation;
	}

	static PortableWell fromWell(Well well) {
		if (well != null) {
			return new PortableWell(well.getId(), well.getName(), well.getPotential(),PortableLocation.fromLocation(well.getLocation()));
		} else {
			return null;
		}
    }

	public String getName() {
        return name;
    }

	public Long getPotential() {
		return potential;
	}

	public long getId() {
		return id;
	}

	public PortableLocation getLocation() {
		return portableLocation;
	}
}