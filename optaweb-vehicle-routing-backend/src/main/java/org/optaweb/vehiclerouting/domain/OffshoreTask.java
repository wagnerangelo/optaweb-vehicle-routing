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

package org.optaweb.vehiclerouting.domain;

import java.util.ArrayList;

public class OffshoreTask {
    private long id;
    private Location location;
    private int demand;
    private OffshoreTask nextOffshoreTaskData;
    private OffshoreTask previousOffshoreTaskData;
    private Vehicle vessel;
    private String offshoreOperationType;
    private boolean locked;
    private String operationName;
    private Well well;
    private Project project;
    private Long priority; // Unit is Oil production Equivalent bbpd
    private int construtiveEuristicOrder;
    private Integer graphLevel;
    private Integer invertedGraphosLevel;
    private Long investedRootId;
    private ArrayList<OffshoreTask> sucessorList;
    private ArrayList<OffshoreTask> predecessorList;
    private Vehicle cronowebVehicle;
    private double cronowebStartTime;
    private String lineType;
    private ArrayList<Vehicle> viableVehicles;
    private ArrayList<Vehicle> recommendedSubPoolVehicles;
    private int numberOfMapedPredecessors;
    private boolean nodeMaped;

    public OffshoreTask(long id, Location location, String operationName, Well well, Project project, String lineType) {
        this.id = id;
        this.location = location;
        this.operationName = operationName;
        this.project = project;
        this.lineType = lineType;
        this.well = well;
    }

    public OffshoreTask(long id, Location location, int demand, OffshoreTask nextOffshoreTaskData,
    OffshoreTask previousOffshoreTaskData, Vehicle vessel, String offshoreOperationType, boolean locked,
    String operationName, Well well, Project project, Long priority, int construtiveEuristicOrder,
    Integer graphLevel, Integer invertedGraphosLevel, Long investedRootId, ArrayList<OffshoreTask> sucessorList,
    ArrayList<OffshoreTask> predecessorList, Vehicle cronowebVehicle, double cronowebStartTime, String lineType,
    ArrayList<Vehicle> viableVehicles, ArrayList<Vehicle> recommendedSubPoolVehicles,
    int numberOfMapedPredecessors, boolean nodeMaped) {
        this(id, location, operationName, well, project, lineType);
        this.demand = demand;
        this.nextOffshoreTaskData = nextOffshoreTaskData;
        this.previousOffshoreTaskData = previousOffshoreTaskData;
        this.vessel = vessel;
        this.offshoreOperationType = offshoreOperationType;
        this.locked = locked;
        this.priority = priority;
        this.construtiveEuristicOrder = construtiveEuristicOrder;
        this.graphLevel = graphLevel;
        this.invertedGraphosLevel = invertedGraphosLevel;
        this.investedRootId = investedRootId;
        this.sucessorList = sucessorList;
        this.predecessorList = predecessorList;
        this.cronowebVehicle = cronowebVehicle;
        this.cronowebStartTime = cronowebStartTime;
        this.viableVehicles = viableVehicles;
        this.recommendedSubPoolVehicles = recommendedSubPoolVehicles;
        this.numberOfMapedPredecessors = numberOfMapedPredecessors;
        this.nodeMaped = nodeMaped;
    }

    public Location getlocation() {
        return location;
    }

    public void setlocation(Location location) {
        this.location = location;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public OffshoreTask getNextOffshoreTaskData() {
        return nextOffshoreTaskData;
    }

    public void setNextOffshoreTaskData(OffshoreTask nextOffshoreTaskData) {
        this.nextOffshoreTaskData = nextOffshoreTaskData;
    }

    public OffshoreTask getPreviousOffshoreTaskData() {
        return previousOffshoreTaskData;
    }

    public void setPreviousOffshoreTaskData(OffshoreTask previousOffshoreTaskData) {
        this.previousOffshoreTaskData = previousOffshoreTaskData;
    }

    public Vehicle getVessel() {
        return vessel;
    }

    public void setVessel(Vehicle vessel) {
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

    public Well getWell() {
        return well;
    }

    public void setWell(Well well) {
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

    public ArrayList<OffshoreTask> getSucessorList() {
        return sucessorList;
    }

    public void setSucessorList(ArrayList<OffshoreTask> sucessorList) {
        this.sucessorList = sucessorList;
    }

    public ArrayList<OffshoreTask> getPredecessorList() {
        return predecessorList;
    }

    public void setPredecessorList(ArrayList<OffshoreTask> predecessorList) {
        this.predecessorList = predecessorList;
    }

    public Vehicle getCronowebVehicle() {
        return cronowebVehicle;
    }

    public void setCronowebVehicle(Vehicle cronowebVehicle) {
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

    public ArrayList<Vehicle> getViableVehicles() {
        return viableVehicles;
    }

    public void setViableVehicles(ArrayList<Vehicle> viableVehicles) {
        this.viableVehicles = viableVehicles;
    }

    public ArrayList<Vehicle> getRecommendedSubPoolVehicles() {
        return recommendedSubPoolVehicles;
    }

    public void setRecommendedSubPoolVehicles(ArrayList<Vehicle> recommendedSubPoolVehicles) {
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
