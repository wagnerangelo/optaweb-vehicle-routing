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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates {@link TimeWindowedOffshoreTask} instances.
 */
public class WellFactory {

    private static final Logger logger = LoggerFactory.getLogger(WellFactory.class);

    private WellFactory() {
        throw new AssertionError("Utility class");
    }

    /**
     * Create a new task with the given id, operationname, linetype, potential, duration
     * @param id task's ID
     * @param operationName task's name
     * @param location
     * @param lineType;
     * @param potential
     * @param serviceDuration
     * @param outcome
     * @return new TimeWindowedOffshoreTask
     */
    public static Well createSimpleWell(long id, String name, Long potential, Location location, RoutingProblem routingProblem) {

        if (routingProblem == null) {
            throw new NullPointerException("routingProblem is null, Can't create well, without a routingProblem associated");
        }

        Well wellCandidate = new Well(id, name,potential,  location);

        if (confirmedNewId(wellCandidate,routingProblem)) {
            routingProblem.getWells().add(wellCandidate);
            return wellCandidate;
        } else {
            return getWell(wellCandidate, routingProblem);
        }
    }

    private static boolean confirmedNewId(Well well, RoutingProblem routingProblem) {
        if (routingProblem.getWells().stream().filter(wellObject -> ((Long) wellObject.getId()).equals(well.getId())).findFirst().isPresent()) {
            logger.info("Well with id: {} already on model", well.getId());
            return false;
        } else {
            return true;
        }
    }

    private static Well getWell(Well wellCandidate, RoutingProblem routingProblem) {
        Well wellAlreadyExist = routingProblem.getWells().stream().filter(wellObject -> ((Long) wellObject.getId()).equals(wellCandidate.getId())).findFirst().get();

        if (!wellAlreadyExist.getName().equals(wellCandidate.getName())) {
            logger.warn("Well, with id: {} already on model, have, at least, two diferent names: name 1: " + wellAlreadyExist.getName() + " , name2: " + wellCandidate.getName(), wellCandidate.getId());
        }
        return wellAlreadyExist;
    }
}
