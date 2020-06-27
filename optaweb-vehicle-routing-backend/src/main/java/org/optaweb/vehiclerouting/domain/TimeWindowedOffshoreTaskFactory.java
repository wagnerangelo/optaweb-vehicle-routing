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
public class TimeWindowedOffshoreTaskFactory {

    private static final Logger logger = LoggerFactory.getLogger(TimeWindowedOffshoreTaskFactory.class);

    private TimeWindowedOffshoreTaskFactory() {
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
    public static TimeWindowedOffshoreTask createSimpleTask(long id, String operationName, LocationData locationData, String lineType, String wellName, long potential, long serviceDuration, RoutingProblem routingProblem) {


        if (routingProblem == null) {
            throw new NullPointerException("routingProblem is null, Can't create task, without a routingProblem associated");
        }
        String locationName = "Location: "+ wellName;
        long locationId = id*10000000 + hashCode_Limited_999999(locationName);
        logger.info("locationId = (id*10000000 + + hashCode_Limited_999999(locationName)): "+locationId);
        Location location = LocationFactory.createSimpleLocation(id, wellName,  locationData, routingProblem);

        long wellId = id*10000000 + hashCode_Limited_999999(wellName);
        logger.info("wellId = (id*10000000 + + hashCode_Limited_999999(wellName)): "+wellId);
        Well well = WellFactory.createSimpleWell(wellId,wellName,potential,location,routingProblem);


        String outcomeName = "Outcome of "+ wellName;
        long outcomeId = id*10000000 + hashCode_Limited_999999(outcomeName);
        logger.info("oucomeId = (id*10000000 + hashCode_Limited_999999(outcomeName)): "+outcomeId);
        Outcome outcome_without_predecessors = OutcomeFactory.createSimpleOutcome_without_predecessors(outcomeId, outcomeName,  well,  routingProblem);

        TimeWindowedOffshoreTask taskCandidate =  new TimeWindowedOffshoreTask(id, location,  operationName,  well, null, lineType, 0, serviceDuration, null, null, 0, 0,  outcome_without_predecessors, 0, 0, 0, null,null,null,null,null);


        if (confirmedNewId(taskCandidate,routingProblem)) {
            routingProblem.getOffshoreTasks().add(taskCandidate);
            return taskCandidate;
        } else {
            return getTask(taskCandidate, routingProblem);
        }
    }

    private static boolean confirmedNewId(TimeWindowedOffshoreTask task, RoutingProblem routingProblem) {
        if (routingProblem.getOffshoreTasks().stream().filter(taskObject -> ((Long) taskObject.getId()).equals(task.getId())).findFirst().isPresent()) {
            logger.info("Task with id: {} already on model", task.getId());
            return false;
        } else {
            return true;
        }
    }

    private static TimeWindowedOffshoreTask getTask(TimeWindowedOffshoreTask taskCandidate, RoutingProblem routingProblem) {
        TimeWindowedOffshoreTask taskAlreadyExist = routingProblem.getOffshoreTasks().stream().filter(taskObject -> ((Long) taskObject.getId()).equals(taskCandidate.getId())).findFirst().get();

        if (!taskAlreadyExist.getOperationName().equals(taskCandidate.getOperationName())) {
            logger.warn("Task, with id: {} already on model, have, at least, two diferent names: name 1: " + taskAlreadyExist.getOperationName() + " , name2: " + taskCandidate.getOperationName(), taskCandidate.getId());
        }
        return taskAlreadyExist;
    }

    private static long hashCode_Limited_999999(String string) {
        int n = string.hashCode();
        logger.info("string: "+string);
        logger.info("string.hashCode: "+string.hashCode());
        while (n > 999999) {
            logger.info("n=" + n);
            n = n % (int) Math.pow(10, (int) Math.log10(n));
        }
        logger.info(" hashCode_Limited_999999: "+n);

        return Integer.valueOf(n).longValue();

    }
}
