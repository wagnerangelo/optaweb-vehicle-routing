package org.optaweb.vehiclerouting.plugin.websocket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.optaweb.vehiclerouting.domain.DockingBerth;

public class PortableDockingBerth {

    @JsonProperty(value = "nroBerço")
    private final int nroBerço;

    @JsonCreator
    public PortableDockingBerth(int nroBerço) {
        this.nroBerço = nroBerço;
    }

    static PortableDockingBerth createFrom(DockingBerth dockingBerth) {
        if (dockingBerth == null) {
            return null;
        } else {
            return new PortableDockingBerth(dockingBerth.getNroBerço());
        }
    }

    public int getNroBerço() {
        return nroBerço;
    }

}