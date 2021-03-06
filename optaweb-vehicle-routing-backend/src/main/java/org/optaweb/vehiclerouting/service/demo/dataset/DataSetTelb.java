/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaweb.vehiclerouting.service.demo.dataset;

import java.util.List;

/**
 * Data set representation used for marshalling and unmarshalling to Telb Cases.
 */
class DataSetTelb extends DataSet {

    private DataSetParameters telbParameters;
    private List<TimeWindowedOffshoreTaskData> offshoreTasksData;

    /**
     * Data set TelbParameters (parameters of study case).
     * @return data set parameters
     */
    public DataSetParameters getTelbParameters() {
        return telbParameters;
    }

    public void setTelbParameters(final DataSetParameters telbParameters) {
        this.telbParameters = telbParameters;
    }

    public List<TimeWindowedOffshoreTaskData> getOffshoreTasksData() {
        return offshoreTasksData;
    }

    public void setOffshoreTasksData(final List<TimeWindowedOffshoreTaskData> offshoreTasksData) {
        this.offshoreTasksData = offshoreTasksData;
    }
}
