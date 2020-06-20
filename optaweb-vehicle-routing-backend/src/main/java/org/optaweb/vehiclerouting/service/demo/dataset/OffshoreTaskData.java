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

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffshoreTaskData {
    @JsonProperty(value = "id")
	private long id;
    @JsonProperty(value = "dataSetlocation")
	private DataSetLocation dataSetlocation;
    @JsonProperty(value = "demand")
    private int demand;
    @JsonProperty(value = "nextOffshoreTaskData")
    private OffshoreTaskData nextOffshoreTaskData;
    @JsonProperty(value = "previousOffshoreTaskData")
    private OffshoreTaskData previousOffshoreTaskData;
    @JsonProperty(value = "vessel")
    protected DataSetVehicle vessel;
    @JsonProperty(value = "offshoreOperationType")
    private String offshoreOperationType;
    @JsonProperty(value = "locked")
    private boolean locked;
    @JsonProperty(value = "operationName")
    private String operationName;
    @JsonProperty(value = "well")
    private WellData well;
    @JsonProperty(value = "project")
    private Project project;
    @JsonProperty(value = "priority")
	private Long priority;  //Unit is Oil production Equivalent bbpd
    @JsonProperty(value = "construtiveEuristicOrder")
	private int construtiveEuristicOrder;
    @JsonProperty(value = "graphLevel")
	private Integer graphLevel;
    @JsonProperty(value = "invertedGraphosLevel")
	private Integer invertedGraphosLevel;
    @JsonProperty(value = "investedRootId")
	private Long investedRootId;
    @JsonProperty(value = "sucessorList")
	private ArrayList<OffshoreTaskData> sucessorList;
    @JsonProperty(value = "predecessorList")
	private ArrayList<OffshoreTaskData> predecessorList;
    @JsonProperty(value = "cronowebVehicle")
	private DataSetVehicle cronowebVehicle;
    @JsonProperty(value = "cronowebStartTime")
	private double cronowebStartTime;
    @JsonProperty(value = "lineType")
	private String lineType;
    @JsonProperty(value = "viableVehicles")
	private ArrayList<DataSetVehicle> viableVehicles;
    @JsonProperty(value = "recommendedSubPoolVehicles")
	private ArrayList<DataSetVehicle> recommendedSubPoolVehicles;
    @JsonProperty(value = "numberOfMapedPredecessors")
	private int numberOfMapedPredecessors;
    @JsonProperty(value = "nodeMaped")
	private boolean nodeMaped;

    public DataSetLocation getDataSetlocation() {
        return dataSetlocation;
    }

    public void setDataSetlocation(DataSetLocation dataSetlocation) {
        this.dataSetlocation = dataSetlocation;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public OffshoreTaskData getNextOffshoreTaskData() {
        return nextOffshoreTaskData;
    }

    public void setNextOffshoreTaskData(OffshoreTaskData nextOffshoreTaskData) {
        this.nextOffshoreTaskData = nextOffshoreTaskData;
    }

    public OffshoreTaskData getPreviousOffshoreTaskData() {
        return previousOffshoreTaskData;
    }

    public void setPreviousOffshoreTaskData(OffshoreTaskData previousOffshoreTaskData) {
        this.previousOffshoreTaskData = previousOffshoreTaskData;
    }

    public DataSetVehicle getVessel() {
        return vessel;
    }

    public void setVessel(DataSetVehicle vessel) {
        this.vessel = vessel;
    }

    public String getOffshoreOperationType() {
        return offshoreOperationType;
    }

    public void setOffshoreOperationType(String offshoreOperationType) {
        this.offshoreOperationType = offshoreOperationType;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public WellData getWell() {
        return well;
    }

    public void setWell(WellData well) {
        this.well = well;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public int getConstrutiveEuristicOrder() {
        return construtiveEuristicOrder;
    }

    public void setConstrutiveEuristicOrder(int construtiveEuristicOrder) {
        this.construtiveEuristicOrder = construtiveEuristicOrder;
    }

    public Integer getGraphLevel() {
        return graphLevel;
    }

    public void setGraphLevel(Integer graphLevel) {
        this.graphLevel = graphLevel;
    }

    public Integer getInvertedGraphosLevel() {
        return invertedGraphosLevel;
    }

    public void setInvertedGraphosLevel(Integer invertedGraphosLevel) {
        this.invertedGraphosLevel = invertedGraphosLevel;
    }

    public Long getInvestedRootId() {
        return investedRootId;
    }

    public void setInvestedRootId(Long investedRootId) {
        this.investedRootId = investedRootId;
    }

    public ArrayList<OffshoreTaskData> getSucessorList() {
        return sucessorList;
    }

    public void setSucessorList(ArrayList<OffshoreTaskData> sucessorList) {
        this.sucessorList = sucessorList;
    }

    public ArrayList<OffshoreTaskData> getPredecessorList() {
        return predecessorList;
    }

    public void setPredecessorList(ArrayList<OffshoreTaskData> predecessorList) {
        this.predecessorList = predecessorList;
    }

    public DataSetVehicle getCronowebVehicle() {
        return cronowebVehicle;
    }

    public void setCronowebVehicle(DataSetVehicle cronowebVehicle) {
        this.cronowebVehicle = cronowebVehicle;
    }

    public double getCronowebStartTime() {
        return cronowebStartTime;
    }

    public void setCronowebStartTime(double cronowebStartTime) {
        this.cronowebStartTime = cronowebStartTime;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public ArrayList<DataSetVehicle> getViableVehicles() {
        return viableVehicles;
    }

    public void setViableVehicles(ArrayList<DataSetVehicle> viableVehicles) {
        this.viableVehicles = viableVehicles;
    }

    public ArrayList<DataSetVehicle> getRecommendedSubPoolVehicles() {
        return recommendedSubPoolVehicles;
    }

    public void setRecommendedSubPoolVehicles(ArrayList<DataSetVehicle> recommendedSubPoolVehicles) {
        this.recommendedSubPoolVehicles = recommendedSubPoolVehicles;
    }

    public int getNumberOfMapedPredecessors() {
        return numberOfMapedPredecessors;
    }

    public void setNumberOfMapedPredecessors(int numberOfMapedPredecessors) {
        this.numberOfMapedPredecessors = numberOfMapedPredecessors;
    }

    public boolean isNodeMaped() {
        return nodeMaped;
    }

    public void setNodeMaped(boolean nodeMaped) {
        this.nodeMaped = nodeMaped;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
