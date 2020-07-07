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
import java.util.Objects;
import java.util.stream.Collectors;

import org.optaweb.vehiclerouting.domain.FlexiblePipe;
import org.optaweb.vehiclerouting.domain.Location;
import org.optaweb.vehiclerouting.domain.Project;
import org.optaweb.vehiclerouting.domain.RoutingProblem;
import org.optaweb.vehiclerouting.domain.RoutingProblemParameters;
import org.optaweb.vehiclerouting.domain.TimeWindowedOffshoreTask;
import org.optaweb.vehiclerouting.domain.Well;

/**
 * Information about a {@link RoutingProblem routing problem instance}.
 */
class RoutingProblemInfo {

    private final String name;
    private final int visits;
    private final RoutingProblemParameters routingProblemParameters;
    private final List<PortableLocation> portableLocations;
    private final List<PortableProject> portableProjects;
    private final List<PortableWell> portableWells;
    private final List<PortableFlexiblePipe> portableFlexiblePipes;

    RoutingProblemInfo(String name, int visits, RoutingProblemParameters routingProblemParameters, List<Location> locations, List<Project> projects, List<Well> wells,  List<FlexiblePipe> flexiblePipes) {
        this.name = Objects.requireNonNull(name);
        this.visits = visits;
        this.routingProblemParameters = routingProblemParameters;
        this.portableLocations = locations.stream().map(location -> new PortableLocation(location.id(), location.coordinates().latitude(), location.coordinates().longitude(), location.description())).collect(Collectors.toList());
        this.portableProjects = projects.stream().map(project -> PortableProject.fromProject(project)).collect(Collectors.toList());
        this.portableWells = wells.stream().map(well -> PortableWell.fromWell(well)).collect(Collectors.toList());
        this.portableFlexiblePipes = flexiblePipes.stream().map(flexiblePipe -> PortableFlexiblePipe.createFrom(flexiblePipe)).collect(Collectors.toList());
       /*  list.stream().map(twOffshoreTask -> PortableTimeWindowedOffshoreTask.createFromTimeWindowdOffshoreTask(
                twOffshoreTask)).collect(Collectors.toList());
 */

    }

    /**
     * Routing problem instance name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Number of visits in the routing problem instance.
     * @return number of visits
     */
    public int getVisits() {
        return visits;
    }

    public RoutingProblemParameters getRoutingProblemParameters() {
        return routingProblemParameters;
    }

     public List<PortableLocation> getPortableLocations() {
        return portableLocations;
    }

    public List<PortableProject> getPortableProjects() {
        return portableProjects;
    }

    public List<PortableWell> getPortableWells() {
        return portableWells;
    }

    public List<PortableFlexiblePipe> getPortableFlexiblePipes() {
        return portableFlexiblePipes;
    }
}
