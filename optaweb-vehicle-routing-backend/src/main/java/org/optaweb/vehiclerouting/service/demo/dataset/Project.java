package org.optaweb.vehiclerouting.service.demo.dataset;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class Project implements ProjectInterface {

	@JsonProperty(value = "name")
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
