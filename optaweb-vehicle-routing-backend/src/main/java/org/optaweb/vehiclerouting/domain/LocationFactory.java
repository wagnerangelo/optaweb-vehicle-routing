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

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates {@link TimeWindowedOffshoreTask} instances.
 */
public class LocationFactory {

    private static final Logger logger = LoggerFactory.getLogger(WellFactory.class);

    private LocationFactory() {
        throw new AssertionError("Utility class");
    }

    /**
     * Create a new task with the given id, operationname, linetype, potential, duration
     * @param id task's ID
     * @param operationName task's name
     * @param locationData
     * @param lineType;
     * @param potential
     * @param serviceDuration
     * @param outcome
     * @return new TimeWindowedOffshoreTask
     */
    public static Location createSimpleLocation(long taskId, String wellName,  LocationData locationData, RoutingProblem routingProblem) {

        if (routingProblem == null) {
            throw new NullPointerException("routingProblem is null, Can't create location, without a routingProblem associated");
        }

        String locationName = createLocationName(wellName);
        Long locationId = createLocationId(taskId, locationName);
        if (locationData == null) {
            locationData = new LocationData(createDefautCoordinates(), wellName);
        }
        Coordinates coordinates = locationData.coordinates();
        if (coordinates == null) {
            coordinates = createDefautCoordinates();

        }
        Location locationCandidate = new Location(locationId, coordinates, locationName);

        if (confirmedNewId( wellName, locationCandidate,routingProblem)) {
            routingProblem.visits().add(locationCandidate);
            return locationCandidate;
        } else {
            return getLocation(wellName, locationCandidate, routingProblem);
        }
    }

    private static Coordinates createDefautCoordinates() {
        return new Coordinates(BigDecimal.valueOf(-22.62),BigDecimal.valueOf(-40.17));
    }

    private static String createLocationName(String wellName) {
        return "Location: "+ wellName;
    };

    private static long createLocationId(Long taskId, String locationName) {
        long locationId = taskId*10000000 + hashCode_Limited_999999(locationName);
        logger.info("locationId = (id*10000000 + + hashCode_Limited_999999(locationName)): "+locationId);
        return locationId;
    };


    private static boolean confirmedNewId( String wellName, Location locationCandidate, RoutingProblem routingProblem) {
        if (routingProblem.getLocations().stream().filter(location -> ((Long) locationCandidate.id()).equals(location.id())).findFirst().isPresent()) {
            logger.info("Location (visit) with id: {} already on model", locationCandidate.id());
            return false;
        } else {
            return true;
        }
    }

    private static Location getLocation( String wellName, Location locationCandidate, RoutingProblem routingProblem) {
        Location locationAlreadyExist = routingProblem.getLocations().stream().filter(location -> ((Long) locationCandidate.id()).equals(location.id())).findFirst().get();

        if (!locationAlreadyExist.description().equals(locationCandidate.description())) {
            logger.warn("Location, with id: {} already on model, have, at least, two diferent names: name 1: " + locationAlreadyExist.description() + " , name2: " + locationCandidate.description(), locationCandidate.id());
        }
        return locationAlreadyExist;
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
