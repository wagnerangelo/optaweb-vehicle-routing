package org.optaweb.vehiclerouting.plugin.websocket;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.optaweb.vehiclerouting.domain.BillofPlsvMaterial;

public class PortableBillofPlsvMaterial {
    @JsonProperty(value = "nodeMaped")
    private final List<PortableFlexiblePipe> portableflexiblePipeList;

    @JsonCreator
    public PortableBillofPlsvMaterial(List<PortableFlexiblePipe> portableflexiblePipeList) {
        this.portableflexiblePipeList = portableflexiblePipeList;
    }

    static PortableBillofPlsvMaterial createFrom(BillofPlsvMaterial billofPlsvMaterial) {
        return new PortableBillofPlsvMaterial(billofPlsvMaterial.getFlexiblePipeList().stream().map(flexiblePipe -> PortableFlexiblePipe.createFrom(flexiblePipe)).collect(Collectors.toList()));
    }
    public List<PortableFlexiblePipe> getPortableflexiblePipeList() {
        return portableflexiblePipeList;
    }

}