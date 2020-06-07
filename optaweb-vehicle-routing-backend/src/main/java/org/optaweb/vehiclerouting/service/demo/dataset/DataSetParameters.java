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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

  /**
     * Create dataSet demo parameters.
     * @param demoContext business context , ie type of vehicle (PSLV, RSV)
     * @param democomplexity business complexity case, if is basic, advanced, multicriteria
     * @param demoHorizon horizon consider to calculate schedule quality parameters (days)
     * @param demoInitialDate initial date of schedule problem (string) ("dd/mm/yy")
     * @param demoElucidation explanation of business case
     * @param isloadingVesselCase used when study case consider loading vessel on port - pickup on porte before delivery
     * @param isTravelTimeCase used when study case consider travel time sailing between locations
     * @param barrelPrice unit US$ per barrel to calculate de montary value of outcome
     * @param checkEarlyDate used to confirm early Date of operation. Sometimes userData don't consider early date based on complex predecessors tree
     */
@JsonIgnoreProperties(ignoreUnknown = true)
class DataSetParameters {

    @JsonProperty(value = "context")
    private String demoContext;
    @JsonProperty(value = "complexity")
    private String demoComplexity;
    @JsonProperty(value = "horizon")
    private int demoHorizon;
    @JsonProperty(value = "initialdate")
    private String demoInitialDate;
    @JsonProperty(value = "elucidation")
    private String demoElucidation;
    @JsonProperty(value = "isloadingVesselCase")
    private Boolean isloadingVesselCase;
    @JsonProperty(value = "isTravelTimeCase")
    private Boolean isTravelTimeCase;
    @JsonProperty(value = "barrelPrice")
    private Double barrelPrice;
    @JsonProperty(value = "checkEarlyDate")
    private Boolean checkEarlyDate;
    @JsonProperty(value = "updateIncludingOrder")
    private Boolean updateIncludingOrder;
    @JsonProperty(value = "bestSolutionKnowed")
    private String bestSolutionKnowed;

    private DataSetParameters() {
        // for unmarshalling
    }

    DataSetParameters(final String demoContext, final String demoComplexity) {
        this.demoContext = demoContext;
        this.demoComplexity = demoComplexity;
    }

    /**
     * demoContext.
     * @return demoContext
     */
    public String getDemoContext() {
        return demoContext;
    }

    public void setDemoContext(final String demoContext) {
        this.demoContext = demoContext;
    }

    /**
     * demoComplexity.
     * @return demoComplexity
     */
    public String getDemoComplexity() {
        return demoComplexity;
    }

    public void setDemoComplexity(final String demoComplexity) {
        this.demoComplexity = demoComplexity;
    }

     /**
     * demoHorizon (days)
     * @return demoHorizon
     */
    public int getDemoHorizon() {
        return demoHorizon;
    }

    public void setDemoHorizon(final int demoHorizon) {
        this.demoHorizon = demoHorizon;
    }

    /**
     * initial date.
     * @return String
     */
    public String getDemoInitialDate() {
        return demoInitialDate;
    }

    public void setDemoInitialDate(final String demoInitialDate) {
        this.demoInitialDate = demoInitialDate;
    }

    public void setDemoElucidation(String demoElucidation) {
        this.demoElucidation = demoElucidation;
    }

    /**
     * multiline text clarification about case.
     * @return String
     */

    public String getDemoElucidation() {
        return demoElucidation;
    }

    @Override
    public String toString() {
        return "DataParameters{" +
                "demoContext" + demoContext + '\'' +
                ", demoComplexity=" + demoComplexity;
    }

    public Boolean getIsloadingVesselCase() {
        return isloadingVesselCase;
    }

    public void setIsloadingVesselCase(Boolean isloadingVesselCase) {
        this.isloadingVesselCase = isloadingVesselCase;
    }

    public Boolean getIsTravelTimeCase() {
        return isTravelTimeCase;
    }

    public void setIsTravelTimeCase(Boolean isTravelTimeCase) {
        this.isTravelTimeCase = isTravelTimeCase;
    }

    public Double getBarrelPrice() {
        return barrelPrice;
    }

    public void setBarrelPrice(Double barrelPrice) {
        this.barrelPrice = barrelPrice;
    }

    public Boolean getCheckEarlyDate() {
        return checkEarlyDate;
    }

    public void setCheckEarlyDate(Boolean checkEarlyDate) {
        this.checkEarlyDate = checkEarlyDate;
    }

    public Boolean getUpdateIncludingOrder() {
        return updateIncludingOrder;
    }

    public void setUpdateIncludingOrder(Boolean updateIncludingOrder) {
        this.updateIncludingOrder = updateIncludingOrder;
    }

    public String getBestSolutionKnowed() {
        return bestSolutionKnowed;
    }

    public void setBestSolutionKnowed(String bestSolutionKnowed) {
        this.bestSolutionKnowed = bestSolutionKnowed;
    }
}
