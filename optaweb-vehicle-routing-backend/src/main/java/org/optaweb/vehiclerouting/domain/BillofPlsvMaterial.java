package org.optaweb.vehiclerouting.domain;

import java.util.List;

public class BillofPlsvMaterial implements BillofPlsvMaterialInterface {

    private List<FlexiblePipe> flexiblePipeList;

    public List<FlexiblePipe> getFlexiblePipeList() {
        return flexiblePipeList;
    }

    public void setFlexiblePipeList(List<FlexiblePipe> flexiblePipeList) {
        this.flexiblePipeList = flexiblePipeList;
    }

}