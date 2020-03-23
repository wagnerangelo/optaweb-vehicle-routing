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

package org.optaweb.vehiclerouting.plugin.planner.weight;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.optaweb.vehiclerouting.domain.Coordinates;
import org.optaweb.vehiclerouting.domain.Location;
import org.optaweb.vehiclerouting.plugin.planner.DistanceMapImpl;
import org.optaweb.vehiclerouting.plugin.planner.domain.PlanningDepot;
import org.optaweb.vehiclerouting.plugin.planner.domain.PlanningLocation;
import org.optaweb.vehiclerouting.plugin.planner.domain.PlanningLocationFactory;
import org.optaweb.vehiclerouting.plugin.planner.domain.PlanningVisit;
import org.optaweb.vehiclerouting.plugin.planner.domain.PlanningVisitFactory;
import org.optaweb.vehiclerouting.plugin.planner.domain.SolutionFactory;
import org.optaweb.vehiclerouting.plugin.planner.domain.VehicleRoutingSolution;
import org.optaweb.vehiclerouting.plugin.planner.weight.DepotAngleVisitDifficultyWeightFactory.DepotAngleVisitDifficultyWeight;

import static org.assertj.core.api.Assertions.assertThat;

class DepotAngleVisitDifficultyWeightFactoryTest {

    private final double depotY = 3.0;
    private final double depotX = -50.0;

    private final PlanningLocation depot = location(0, depotY, depotX);
    private final Map<Long, Long> depotDistanceMap = new HashMap<>();
    private final VehicleRoutingSolution solution = SolutionFactory.emptySolution();
    private final DepotAngleVisitDifficultyWeightFactory weightFactory = new DepotAngleVisitDifficultyWeightFactory();

    DepotAngleVisitDifficultyWeightFactoryTest() {
        solution.getDepotList().add(new PlanningDepot(depot));
        depot.setTravelDistanceMap(new DistanceMapImpl(depot, depotDistanceMap));
    }

    private static PlanningLocation location(long id, double latitude, double longitude) {
        return PlanningLocationFactory.fromDomain(new Location(id, Coordinates.valueOf(latitude, longitude)));
    }

    private void setDistanceToDepot(PlanningLocation location, long symmetricalDistance) {
        setDistanceToDepot(location, symmetricalDistance, symmetricalDistance);
    }

    private void setDistanceToDepot(PlanningLocation location, long depotToLocation, long locationToDepot) {
        Map<Long, Long> locationDistanceMap = new HashMap<>();
        locationDistanceMap.put(depot.getId(), locationToDepot);
        depotDistanceMap.put(location.getId(), depotToLocation);
        location.setTravelDistanceMap(new DistanceMapImpl(location, locationDistanceMap));
    }

    private DepotAngleVisitDifficultyWeight weight(PlanningLocation location) {
        return weightFactory.createSorterWeight(solution, PlanningVisitFactory.fromLocation(location));
    }

    @Test
    void createSorterWeight_close_customer_should_have_smaller_weight() {
        // angle 0 (same as west) distance or ID will decide
        PlanningLocation center1 = location(1, depotY, depotX);
        PlanningLocation center2 = location(2, depotY, depotX);
        PlanningLocation west = location(3, depotY, depotX - 100);
        setDistanceToDepot(center1, 0);
        setDistanceToDepot(center2, 0);
        setDistanceToDepot(west, 1);

        // both east (same angle), distance will decide
        PlanningLocation east1 = location(10, depotY, depotX + 37);
        PlanningLocation east2 = location(20, depotY, depotX + 110.011);
        // east1 is closer to depot than east2
        setDistanceToDepot(east1, 100);
        setDistanceToDepot(east2, 200);

        // both north (same angle), distance will decide
        PlanningLocation north1 = location(30, depotY + 30.0, depotX);
        PlanningLocation north2 = location(40, depotY + 60.0, depotX);
        // north1 is closer to depot than north2
        setDistanceToDepot(north1, -1);
        setDistanceToDepot(north2, 0);

        // all different angle, distance doesn't matter
        PlanningLocation sw1 = location(50, depotY - 100, depotX - 100);
        PlanningLocation south1 = location(60, depotY - 100, depotX);
        PlanningLocation se1 = location(70, depotY - 100, depotX + 100);
        setDistanceToDepot(sw1, -1);
        setDistanceToDepot(south1, -1);
        setDistanceToDepot(se1, -1);

        // E < NE < N < NW < W < SW < S < SE < E (-π → π)
        assertThat(Stream.of(north1, north2, center1, center2, west, sw1, south1, se1, east1, east2)
                .map(this::weight)
                .collect(Collectors.toList())
        ).isSorted();

        assertThat(weight(north1)).isLessThan(weight(north2));
        assertThat(weight(north2)).isGreaterThan(weight(north1));

        assertThat(weight(center1)).isLessThan(weight(center2));
        assertThat(weight(center2)).isGreaterThan(weight(center1));
        assertThat(weight(center2)).isEqualByComparingTo(weight(center2));
    }

    @Test
    void locations_with_asymmetrical_distances_should_be_sorted_by_round_trip_time() {
        // coordinates only affect angle, distance is stored in the distance map
        PlanningLocation a = location(101, depotY, depotX);
        PlanningLocation b = location(102, depotY, depotX);
        PlanningLocation c = location(103, depotY, depotX);
        setDistanceToDepot(a, 101, 90); // round-trip: 191 (a < b, although depot→a > depot→b)
        setDistanceToDepot(b, 100, 100); // round-trip: 200
        setDistanceToDepot(c, 200, 50); // round-trip: 250 (c > b, although c→depot < b→depot)

        assertThat(weight(a)).isLessThan(weight(b));
        assertThat(weight(b)).isLessThan(weight(c));
    }

    @Test
    void equals() {
        long id = 3;
        double angle = Math.PI;
        long distance = 1000;
        PlanningVisit visit = PlanningVisitFactory.fromLocation(location(id, 0.0, 0.0));
        DepotAngleVisitDifficultyWeight weight = new DepotAngleVisitDifficultyWeight(visit, angle, distance);

        assertThat(weight).isNotEqualTo(null);
        assertThat(weight).isNotEqualTo(this);
        assertThat(weight).isNotEqualTo(new DepotAngleVisitDifficultyWeight(
                PlanningVisitFactory.fromLocation(location(id + 1, 0.0, 0.0)), angle, distance));
        assertThat(weight).isNotEqualTo(new DepotAngleVisitDifficultyWeight(
                PlanningVisitFactory.fromLocation(location(id, 0.0, 0.0)), -angle, distance));
        assertThat(weight).isNotEqualTo(new DepotAngleVisitDifficultyWeight(
                PlanningVisitFactory.fromLocation(location(id, 0.0, 0.0)), angle, distance - 1));

        assertThat(weight).isEqualTo(weight);
        assertThat(weight).isEqualTo(new DepotAngleVisitDifficultyWeight(visit, angle, distance));
    }
}
