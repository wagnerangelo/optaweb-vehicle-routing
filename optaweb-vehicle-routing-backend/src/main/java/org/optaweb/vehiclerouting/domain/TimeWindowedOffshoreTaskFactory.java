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


    public static TimeWindowedOffshoreTask createSimpleTask(long id, String operationName, Location location, String lineType, String wellName, long potential, long serviceDuration, RoutingProblem routingProblem) {

        if (routingProblem == null) {
            throw new NullPointerException("routingProblem is null, Can't create task, without a routingProblem associated");
        }
        logger.info("wellId = id*100000 + hashcode/100000");
        logger.info("Part of wellId = id*100000 :"+ id*1000);
        logger.info(" hashcode/100000:"+ wellName.hashCode()/10000);
        Well well = WellFactory.createSimpleWell(id*1000 + wellName.hashCode()/10000,wellName,potential,location,routingProblem);

        String outcomeName = "Outcome of "+ wellName;
        logger.info("oucomeId = id*100000 + hashcode/100000");
        logger.info("Part of outocmeId = id*100000 :"+ id*1000);
        logger.info(" hashcode/100000:"+ outcomeName.hashCode()/10000);
        Outcome outcome_without_predecessors = OutcomeFactory.createSimpleOutcome_without_predecessors(id*1000 + outcomeName.hashCode()/10000, outcomeName,  well,  routingProblem);

        TimeWindowedOffshoreTask taskCandidate =  new TimeWindowedOffshoreTask(id, location,  outcomeName,  well, null, lineType, 0, serviceDuration, null, null, 0, 0,  outcome_without_predecessors, 0, 0, 0, null,null,null,null,null);


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

}
