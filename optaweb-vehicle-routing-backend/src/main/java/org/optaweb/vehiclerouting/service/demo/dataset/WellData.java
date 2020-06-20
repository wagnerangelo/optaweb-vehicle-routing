package org.optaweb.vehiclerouting.service.demo.dataset;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class WellData implements WellInterfaceData  {

	@JsonProperty(value = "name")
	private String name;
    @JsonProperty(value = "potencial")
	private Long potencial;

    @Override
	public String getName() {
        return name;
    }

    @Override
	public void setName(String name) {
        this.name = name;
    }

	@Override
	public Long getPotencial() {
		return potencial;
	}

	@Override
	public void setPotencial(Long potencial) {
		this.potencial = potencial;
	}

}