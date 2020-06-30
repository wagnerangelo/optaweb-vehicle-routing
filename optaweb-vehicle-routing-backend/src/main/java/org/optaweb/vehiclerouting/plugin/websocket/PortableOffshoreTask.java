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

package org.optaweb.vehiclerouting.plugin.websocket;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.optaweb.vehiclerouting.domain.OffshoreTask;

public class PortableOffshoreTask {
    @JsonProperty(value = "id")
    private final long id;
    @JsonProperty(value = "portableLocation")
    private final PortableLocation portableLocation;
    @JsonProperty(value = "demand")
    private final int demand;
    @JsonProperty(value = "portableNextOffshoreTaskData")
    private final PortableOffshoreTask portableNextOffshoreTaskData;
    @JsonProperty(value = "portablePreviousOffshoreTaskData")
    private final PortableOffshoreTask portablePreviousOffshoreTaskData;
    @JsonProperty(value = "portableVessel")
    private final PortableVehicle portableVessel;
    @JsonProperty(value = "offshoreOperationType")
    private final String offshoreOperationType;
    @JsonProperty(value = "locked")
    private final boolean locked;
    @JsonProperty(value = "operationName")
    private final String operationName;
    @JsonProperty(value = "portableWell")
    private final PortableWell portableWell;
    @JsonProperty(value = "project")
    private final PortableProject project;
    @JsonProperty(value = "priority")
    private final Long priority; // Unit is Oil production Equivalent bbpd
    @JsonProperty(value = "construtiveEuristicOrder")
    private final int construtiveEuristicOrder;
    @JsonProperty(value = "graphLevel")
    private final Integer graphLevel;
    @JsonProperty(value = "invertedGraphosLevel")
    private final Integer invertedGraphosLevel;
    @JsonProperty(value = "investedRootId")
    private final Long investedRootId;
    @JsonProperty(value = "portableSucessorList")
    private final List<PortableOffshoreTask> portableSucessorList;
    @JsonProperty(value = "portablePredecessorList")
    private final List<PortableOffshoreTask> portablePredecessorList;
    @JsonProperty(value = "portableCronowebVehicle")
    private final PortableVehicle portableCronowebVehicle;
    @JsonProperty(value = "cronowebStartTime")
    private final double cronowebStartTime;
    @JsonProperty(value = "lineType")
    private final String lineType;
    @JsonProperty(value = "portableViableVehicles")
    private final List<PortableVehicle> portableViableVehicles;
    @JsonProperty(value = "portableRecommendedSubPoolVehicles")
    private final List<PortableVehicle> portableRecommendedSubPoolVehicles;
    @JsonProperty(value = "numberOfMapedPredecessors")
    private final int numberOfMapedPredecessors;
    @JsonProperty(value = "nodeMaped")
    private final boolean nodeMaped;

    @JsonCreator
    public PortableOffshoreTask(long id, PortableLocation portableLocation, int demand,
            PortableOffshoreTask portableNextOffshoreTaskData, PortableOffshoreTask portablePreviousOffshoreTaskData,
            PortableVehicle portableVessel, String offshoreOperationType, boolean locked, String operationName,
            PortableWell portableWell, PortableProject project, Long priority, int construtiveEuristicOrder,
            Integer graphLevel, Integer invertedGraphosLevel, Long investedRootId,
            List<PortableOffshoreTask> portableSuccessorList,
            List<PortableOffshoreTask> portablePredecessorList, PortableVehicle portableCronowebVehicle,
            double cronowebStartTime, String lineType, List<PortableVehicle> portableViableVehicles,
            List<PortableVehicle> portableRecommendedSubPoolVehicles, int numberOfMapedPredecessors,
            boolean nodeMaped) {
        this.id = id;
        this.portableLocation = portableLocation;
        this.demand = demand;
        this.portableNextOffshoreTaskData = portableNextOffshoreTaskData;
        this.portablePreviousOffshoreTaskData = portablePreviousOffshoreTaskData;
        this.portableVessel = portableVessel;
        this.offshoreOperationType = offshoreOperationType;
        this.locked = locked;
        this.operationName = operationName;
        this.portableWell = portableWell;
        this.project = project;
        this.priority = priority;
        this.construtiveEuristicOrder = construtiveEuristicOrder;
        this.graphLevel = graphLevel;
        this.invertedGraphosLevel = invertedGraphosLevel;
        this.investedRootId = investedRootId;
        this.portableSucessorList = portableSuccessorList;
        this.portablePredecessorList = portablePredecessorList;
        this.portableCronowebVehicle = portableCronowebVehicle;
        this.cronowebStartTime = cronowebStartTime;
        this.lineType = lineType;
        this.portableViableVehicles = portableViableVehicles;
        this.portableRecommendedSubPoolVehicles = portableRecommendedSubPoolVehicles;
        this.numberOfMapedPredecessors = numberOfMapedPredecessors;
        this.nodeMaped = nodeMaped;
    }

    static PortableOffshoreTask createFromOffshoreTask(OffshoreTask offshoreTask){
        if (offshoreTask != null) {
            List<PortableOffshoreTask> portableSuccessorList = null;
            if (offshoreTask.getSucessorList() != null) {
                portableSuccessorList = offshoreTask.getSucessorList().stream().map(offshoreSucessorTask -> PortableOffshoreTask.createFromOffshoreTask(offshoreSucessorTask)).collect(Collectors.toList());
            }
            List<PortableOffshoreTask> portablePredecessorList = null;
            if (offshoreTask.getPredecessorList() != null) {
                 portablePredecessorList = offshoreTask.getPredecessorList().stream().map(offshorePredecessorTask -> PortableOffshoreTask.createFromOffshoreTask(offshorePredecessorTask)).collect(Collectors.toList());
            }
            List<PortableVehicle> portableViableVehicles = null;
            if (offshoreTask.getViableVehicles() != null ) {
                portableViableVehicles = offshoreTask.getViableVehicles().stream().map(viableVehicle -> PortableVehicle.fromVehicle(viableVehicle)).collect(Collectors.toList());
            }
            List<PortableVehicle> portableRecommendedSubPoolVehicles = null;
            if (offshoreTask.getRecommendedSubPoolVehicles() != null ) {
                portableRecommendedSubPoolVehicles = offshoreTask.getRecommendedSubPoolVehicles().stream().map(recommendedSubPoolVehicles -> PortableVehicle.fromVehicle(recommendedSubPoolVehicles)).collect(Collectors.toList());
            }

            return new PortableOffshoreTask(offshoreTask.getId(), PortableLocation.fromLocation(offshoreTask.getlocation()), offshoreTask.getDemand(), PortableOffshoreTask.createFromOffshoreTask(offshoreTask.getNextOffshoreTaskData()), PortableOffshoreTask.createFromOffshoreTask(offshoreTask.getPreviousOffshoreTaskData()), PortableVehicle.fromVehicle(offshoreTask.getVessel()), offshoreTask.getOffshoreOperationType(), offshoreTask.isLocked(), offshoreTask.getOperationName(), PortableWell.fromWell(offshoreTask.getWell()), PortableProject.fromProject(offshoreTask.getProject()), offshoreTask.getPriority(), offshoreTask.getConstrutiveEuristicOrder(), offshoreTask.getGraphLevel(), offshoreTask.getInvertedGraphosLevel(), offshoreTask.getInvestedRootId(), portableSuccessorList, portablePredecessorList, PortableVehicle.fromVehicle(offshoreTask.getCronowebVehicle()), 0, null, portableViableVehicles, portableRecommendedSubPoolVehicles, 0 , false);
        } else {
            return null;
        }
    }


    public long getId() {
        return id;
    }

    public PortableLocation getPortableLocation() {
        return portableLocation;
    }

    public int getDemand() {
        return demand;
    }

    public PortableOffshoreTask getPortableNextOffshoreTaskData() {
        return portableNextOffshoreTaskData;
    }

    public PortableOffshoreTask getPortablePreviousOffshoreTaskData() {
        return portablePreviousOffshoreTaskData;
    }

    public PortableVehicle getPortableVessel() {
        return portableVessel;
    }

    public String getOffshoreOperationType() {
        return offshoreOperationType;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getOperationName() {
        return operationName;
    }

    public PortableWell getPortableWell() {
        return portableWell;
    }

    public PortableProject getProject() {
        return project;
    }

    public Long getPriority() {
        return priority;
    }

    public int getConstrutiveEuristicOrder() {
        return construtiveEuristicOrder;
    }

    public Integer getGraphLevel() {
        return graphLevel;
    }

    public Integer getInvertedGraphosLevel() {
        return invertedGraphosLevel;
    }

    public Long getInvestedRootId() {
        return investedRootId;
    }

    public List<PortableOffshoreTask> getPortableSucessorList() {
        return portableSucessorList;
    }

    public List<PortableOffshoreTask> getPortablePredecessorList() {
        return portablePredecessorList;
    }

    public PortableVehicle getPortableCronowebVehicle() {
        return portableCronowebVehicle;
    }

    public double getCronowebStartTime() {
        return cronowebStartTime;
    }

    public String getLineType() {
        return lineType;
    }

    public List<PortableVehicle> getPortableViableVehicles() {
        return portableViableVehicles;
    }

    public List<PortableVehicle> getPortableRecommendedSubPoolVehicles() {
        return portableRecommendedSubPoolVehicles;
    }

    public int getNumberOfMapedPredecessors() {
        return numberOfMapedPredecessors;
    }

    public boolean isNodeMaped() {
        return nodeMaped;
    }

}
