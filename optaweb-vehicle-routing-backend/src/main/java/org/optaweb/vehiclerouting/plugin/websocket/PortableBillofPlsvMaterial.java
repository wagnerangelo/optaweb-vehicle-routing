package org.optaweb.vehiclerouting.plugin.websocket;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import static java.util.Collections.singletonList;

import org.optaweb.vehiclerouting.domain.BillofPlsvMaterial;
import org.optaweb.vehiclerouting.domain.FlexiblePipe;

public class PortableBillofPlsvMaterial {
    @JsonProperty(value = "nodeMaped")
    private final List<PortableFlexiblePipe> portableflexiblePipeList;

    @JsonCreator
    public PortableBillofPlsvMaterial(List<PortableFlexiblePipe> portableflexiblePipeList) {
        this.portableflexiblePipeList = portableflexiblePipeList;
    }

    static PortableBillofPlsvMaterial createFrom(BillofPlsvMaterial billofPlsvMaterial) {
        //TODO this just make sense on PLSV context
        //TODO this should not be solved here
        if (billofPlsvMaterial == null) {
            billofPlsvMaterial = new BillofPlsvMaterial();
            List<FlexiblePipe> flexiblePipes= singletonList(new FlexiblePipe(6, 3000L, 99999999L));
            billofPlsvMaterial.setFlexiblePipeList(flexiblePipes);
        }
        return new PortableBillofPlsvMaterial(billofPlsvMaterial.getFlexiblePipeList().stream().map(flexiblePipe -> PortableFlexiblePipe.createFrom(flexiblePipe)).collect(Collectors.toList()));
    }
    public List<PortableFlexiblePipe> getPortableflexiblePipeList() {
        return portableflexiblePipeList;
    }

}