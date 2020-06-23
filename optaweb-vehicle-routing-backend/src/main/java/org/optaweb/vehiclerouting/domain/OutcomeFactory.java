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
public class OutcomeFactory {

    private static final Logger logger = LoggerFactory.getLogger(WellFactory.class);

    private OutcomeFactory() {
        throw new AssertionError("Utility class");
    }

    /**
     * Create a new outcome with the given id, operationname, linetype, potential, duration
     * @param id outcome's ID
     * @param name outcome's name
     * @param well
     */
    public static Outcome createSimpleOutcome_without_predecessors(long id, String name, Well well, RoutingProblem routingProblem) {

        if (routingProblem == null) {
            throw new NullPointerException("routingProblem is null, Can't create well, without a routingProblem associated");
        }

        Outcome outcomeCandidate = new Outcome( id, name,well);
        //Here we choose, at simpleOutocome define the potential of well equal to potential of outcome
        System.out.println("outcomeCandidate. well.getPotential()): ");
        System.out.println(well.getPotential());
        outcomeCandidate.setPotential(well.getPotential());
        System.out.println("outcomeCandidate.getPotencialEntrega(): ");
        System.out.println(outcomeCandidate.getPotential());

        if (confirmedNewId(outcomeCandidate,routingProblem)) {
            routingProblem.getOutcomes().add(outcomeCandidate);
            return outcomeCandidate;
        } else {
            return getOutcome(outcomeCandidate, routingProblem);
        }
    }

    private static boolean confirmedNewId(Outcome outcome, RoutingProblem routingProblem) {
        if (routingProblem.getOutcomes().stream().filter(outcomeObject -> ((Long) outcomeObject.getId()).equals(outcome.getId())).findFirst().isPresent()) {
            logger.info("Well with id: {} already on model", outcome.getId());
            return false;
        } else {
            return true;
        }
    }

    private static Outcome getOutcome(Outcome outcomeCandidate, RoutingProblem routingProblem) {
        Outcome outcomeAlreadyExist = routingProblem.getOutcomes().stream().filter(outcomeObject -> ((Long) outcomeObject.getId()).equals(outcomeCandidate.getId())).findFirst().get();

        if (!outcomeAlreadyExist.getOutcomeName().equals(outcomeCandidate.getOutcomeName())) {
            logger.warn("Outcome, with id: {} already on model, have, at least, two diferent names: name 1: " + outcomeAlreadyExist.getOutcomeName() + " , name2: " + outcomeCandidate.getOutcomeName(), outcomeCandidate.getId());
        }
        return outcomeAlreadyExist;
    }
}
