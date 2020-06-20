package org.optaweb.vehiclerouting.service.demo.dataset;

import java.util.List;

public class BillofPlsvMaterialData implements BillofPlsvMaterialDataInterface {

    private List<FlexiblePipeData> flexiblePipeList;

    public List<FlexiblePipeData> getFlexiblePipeList() {
        return flexiblePipeList;
    }

    public void setFlexiblePipeList(List<FlexiblePipeData> flexiblePipeList) {
        this.flexiblePipeList = flexiblePipeList;
    }

}