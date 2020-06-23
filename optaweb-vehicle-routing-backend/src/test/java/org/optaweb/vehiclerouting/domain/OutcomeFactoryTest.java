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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class OutcomeFactoryTest {

    @Test
    void createOutcome() {

        RoutingProblem routingProblem = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), new LocationData(new Coordinates(BigDecimal.valueOf(1 ),BigDecimal.valueOf(1)), "Coordenada do Poço1"), new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));

        long id = 1;
        String name = "Poço1";
        Long potential = 100000L;
        Location location = null;

        Well well = WellFactory.createSimpleWell(id,name,potential,location,routingProblem);
        Outcome outcome = OutcomeFactory.createSimpleOutcome_without_predecessors(id, name, well, routingProblem);
        assertThat(outcome.getId()).isEqualTo(id);
        assertThat(outcome.getOutcomeName()).isEqualTo(name);
        System.out.println("outcome.getPotential()"+outcome.getPotential());
        assertThat(outcome.getPotential()).isEqualTo(potential);

    }

    @Test
    void createOucome_routingProblem_must_not_be_null()  {

        //not necessary because well cant be created without routingProblem.

    }

    @Test
    void create_3_Outcomes_wich_2_have_same_id_must_be_1_Object() {

        long id = 1;
        String name = "Poço1";
        Long potential = 100000L;
        Location location = null;
        RoutingProblem routingProblem = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), new LocationData(new Coordinates(BigDecimal.valueOf(1 ),BigDecimal.valueOf(1)), "Coordenada do Poço1"), new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));
        Well well = WellFactory.createSimpleWell(id,name,potential,location,routingProblem);
        Outcome outcome = OutcomeFactory.createSimpleOutcome_without_predecessors(id, name, well, routingProblem);
        assertThat(outcome.getOutcomeName()).isEqualTo(name);

        long id2 = 2;
        String name2 = "Poço2";
        Long potential2 = 100000L;
        Location location2 = null;
        Well well2 = WellFactory.createSimpleWell(id2,name2,potential2,location2,routingProblem);
        Outcome outcome2 = OutcomeFactory.createSimpleOutcome_without_predecessors(id2, name2, well2, routingProblem);
        assertThat(outcome2.getOutcomeName()).isEqualTo(name2);

        assertThat(routingProblem.getOutcomes().size()).isEqualTo(2);
        assertThat(routingProblem.getOutcomes().stream()
        .filter( distinctByKey(w -> w.getId()))
        .collect( Collectors.toList()).size()).isEqualTo(2);


        long id3_value2 = 2;
        String name3 = "Poço3";
        Long potential3 = 100000L;
        Location location3 = null;
        Well well3 = WellFactory.createSimpleWell(id3_value2,name3,potential3,location3,routingProblem);
        Outcome outcome3 = OutcomeFactory.createSimpleOutcome_without_predecessors(id3_value2, name3, well3, routingProblem);
        //pay attention well3 should be discarted
        assertThat(outcome3.getOutcomeName()).isEqualTo(name2);

        assertThat(routingProblem.getOutcomes().size()).isEqualTo(2);
        assertThat(routingProblem.getOutcomes().stream()
        .filter( distinctByKey(w -> w.getId()))
        .collect( Collectors.toList()).size()).isEqualTo(2);

    }

   public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
