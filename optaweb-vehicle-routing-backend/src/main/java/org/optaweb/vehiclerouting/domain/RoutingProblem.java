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
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Definition of the vehicle routing problem instance.
 */
public class RoutingProblem {

    private final String name;
    private final List<VehicleData> vehicles;
    private final LocationData depot;
    private final List<LocationData> visits;
    private final RoutingProblemParameters routingProblemParameters;
    private List<TimeWindowedOffshoreTask> offshoreTasks;
    private List<Well> wells;
    private List<Project> projects;
    private List<FlexiblePipe> flexiblePipeList;
    private List<Outcome> outcomes;

    /**
     * Create routing problem instance.
     * @param name the instance name
     * @param vehicles list of vehicles (not null)
     * @param depot the depot (may be {@code null} if there is no depot)
     * @param visits list of visits (not null)
     */

    public RoutingProblem(
            String name,
            List<? extends VehicleData> vehicles,
            LocationData depot,
            List<? extends LocationData> visits,
            RoutingProblemParameters routingProblemParameters
    ) {
        this.name = Objects.requireNonNull(name);
        this.vehicles = new ArrayList<>(Objects.requireNonNull(vehicles));
        this.depot = depot;
        this.visits = new ArrayList<>(Objects.requireNonNull(visits));
        this.routingProblemParameters = routingProblemParameters;
        this.offshoreTasks = new  ArrayList<TimeWindowedOffshoreTask>();
        this.wells = new ArrayList<Well>();
        this.projects = new ArrayList<Project>();
        this.flexiblePipeList = new ArrayList<FlexiblePipe>();
        this.outcomes = new ArrayList<Outcome>();
    }

    /**
     * Get routing problem instance name.
     * @return routing problem instance name
     */
    public String name() {
        return name;
    }

    /**
     * Get the depot.
     * @return depot (never {@code null})
     */
    public Optional<LocationData> depot() {
        return Optional.ofNullable(depot);
    }

    /**
     * Get locations that should be visited.
     * @return visits
     */
    public List<LocationData> visits() {
        return visits;
    }

    public RoutingProblemParameters getRoutingProblemParameters() {
        return routingProblemParameters;
    }

    /**
     * Vehicles that are part of the problem definition.
     * @return vehicles
     */
    public List<VehicleData> vehicles() {
        return vehicles;
    }

    public List<TimeWindowedOffshoreTask> getOffshoreTasks() {
        return offshoreTasks;
    }

    public void setOffshoreTasks(List<TimeWindowedOffshoreTask> offshoreTasks) {
        this.offshoreTasks = offshoreTasks;
    }

    public List<Well> getWells() {
        return wells;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setWells(List<Well> wells) {
        this.wells = wells;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<FlexiblePipe> getFlexiblePipeList() {
        return flexiblePipeList;
    }

    public void setFlexiblePipeList(List<FlexiblePipe> flexiblePipeList) {
        this.flexiblePipeList = flexiblePipeList;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }
}
