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

class LocationFactoryTest {

    @Test
    void createLocation() {

        LocationData locationData = new LocationData(new Coordinates(BigDecimal.valueOf(1),BigDecimal.valueOf(1)), "Coordenada do Poço1");

        RoutingProblem routingProblem = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), locationData, new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));

        long taskId = 1;
        String wellName = "Poço1";
        Location location = LocationFactory.createSimpleLocation(taskId, wellName, locationData, routingProblem);

        assertThat(location.id()).isNotNull();
        assertThat(location.description()).isEqualTo("Location: "+ wellName);
        if (locationData != null && locationData.coordinates() != null) {
                assertThat(location.coordinates()).isEqualTo(locationData.coordinates());
        } else {
            assertThat(location.coordinates()).isEqualTo(new Coordinates(BigDecimal.valueOf(-22.62),BigDecimal.valueOf(-40.17)));
        }

    }

    @Test
    void createWell_routingProblem_must_not_be_null()  {


        LocationData locationData = new LocationData(new Coordinates(BigDecimal.valueOf(1),BigDecimal.valueOf(1)), "Coordenada do Poço1");

        RoutingProblem routingProblem = null;
        long taskId = 1;
        String wellName = "Poço1";

        assertThatNullPointerException().isThrownBy(() -> LocationFactory.createSimpleLocation(taskId, wellName, locationData, routingProblem));

        //now Creating with routingProblem

        RoutingProblem routingProblem2 = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), locationData, new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));

        Location location2 = LocationFactory.createSimpleLocation(taskId, wellName, locationData, routingProblem2);

        assertThat(location2.id()).isNotNull();
        assertThat(location2.description()).isEqualTo("Location: "+ wellName);
        assertThat(location2.coordinates()).isEqualTo(locationData.coordinates());

    }

    @Test
    void create_3_Well_2_wich_have_same_id_must_be_1_Object() {

        LocationData locationData1 = new LocationData(new Coordinates(BigDecimal.valueOf(1),BigDecimal.valueOf(1)), "Coordenada do Poço1");
        RoutingProblem routingProblem = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), locationData1, new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));
        long taskId1 = 1;
        String wellName1 = "Poço1";
        Location location1 = LocationFactory.createSimpleLocation(taskId1, wellName1, locationData1, routingProblem);
        assertThat(location1.id()).isNotNull();

        LocationData locationData2 = new LocationData(new Coordinates(BigDecimal.valueOf(1),BigDecimal.valueOf(1)), "Coordenada do Poço2");
        long taskId2 = 2;
        String wellName2 = "Poço2";
        Location location2 = LocationFactory.createSimpleLocation(taskId2, wellName2, locationData2, routingProblem);
        assertThat(location2.id()).isNotNull();

        LocationData locationData3_value2 = new LocationData(new Coordinates(BigDecimal.valueOf(1),BigDecimal.valueOf(1)), "Coordenada do Poço2");
        long taskId3_value2 = 2;
        String wellName3 = "Poço3";
        Location location3 = LocationFactory.createSimpleLocation(taskId3_value2, wellName3, locationData3_value2, routingProblem);
        assertThat(location3.id()).isNotNull();

    }

   public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
