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
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class WellFactoryTest {

    @Test
    void createWell() {

        RoutingProblem routingProblem = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), new LocationData(new Coordinates(BigDecimal.valueOf(1 ),BigDecimal.valueOf(1)), "Coordenada do Poço1"), new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));

        long id = 1;
        String name = "Poço1";
        Long potential = 100000L;
        Location location = null;

        Well well = WellFactory.createSimpleWell(id,name,potential,location,routingProblem);


        assertThat(well.getId()).isEqualTo(id);
        assertThat(well.getName()).isEqualTo(name);
        assertThat(well.getPotential()).isEqualTo(potential);

    }

    @Test
    void createWell_routingProblem_must_not_be_null()  {


        RoutingProblem routingProblem = null;

        long id = 1;
        String name = "Poço1";
        Long potential = 100000L;
        Location location = null;

        assertThatNullPointerException().isThrownBy(() -> WellFactory.createSimpleWell(id,name,potential,location,routingProblem));

        //now Creating with routingProblem
        RoutingProblem routingProblem2 = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), new LocationData(new Coordinates(BigDecimal.valueOf(1 ),BigDecimal.valueOf(1)), "Coordenada do Poço1"), new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));

        Well well = WellFactory.createSimpleWell(id,name,potential,location,routingProblem2);


        assertThat(well.getId()).isEqualTo(id);
        assertThat(well.getName()).isEqualTo(name);
        assertThat(well.getPotential()).isEqualTo(potential);


    }

    @Test
    void create_2_Wells_same_id_must_be_1_Object()  {

        long id = 1;
        String name = "Poço1";
        Long potential = 100000L;
        Location location = null;
        RoutingProblem routingProblem = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), new LocationData(new Coordinates(BigDecimal.valueOf(1 ),BigDecimal.valueOf(1)), "Coordenada do Poço1"), new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));
        Well well = WellFactory.createSimpleWell(id,name,potential,location,routingProblem);
        assertThat(well.getName()).isEqualTo(name);

        long id2 = 2;
        String name2 = "Poço2";
        Long potential2 = 100000L;
        Location location2 = null;
        Well well2 = WellFactory.createSimpleWell(id2,name2,potential2,location2,routingProblem);
        assertThat(well2.getName()).isEqualTo(name2);

        assertThat(routingProblem.getWells().size()).isEqualTo(2);
        assertThat(routingProblem.getWells().stream()
        .filter( distinctByKey(w -> w.getId()))
        .collect( Collectors.toList()).size()).isEqualTo(2);


        long id3_value2 = 2;
        String name3 = "Poço3";
        Long potential3 = 100000L;
        Location location3 = null;
        Well well3 = WellFactory.createSimpleWell(id3_value2,name3,potential3,location3,routingProblem);
        //pay attention well3 should be discarted
        assertThat(well3.getName()).isEqualTo(name2);

        assertThat(routingProblem.getWells().size()).isEqualTo(2);
        assertThat(routingProblem.getWells().stream()
        .filter( distinctByKey(w -> w.getId()))
        .collect( Collectors.toList()).size()).isEqualTo(2);

    }

   public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
