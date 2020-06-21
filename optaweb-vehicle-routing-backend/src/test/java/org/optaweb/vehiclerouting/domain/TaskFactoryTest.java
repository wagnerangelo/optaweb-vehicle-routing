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

class TaskFactoryTest {

    @Test
    void createTask() {

        RoutingProblem routingProblem = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), new LocationData(new Coordinates(BigDecimal.valueOf(1 ),BigDecimal.valueOf(1)), "Coordenada do Poço1"), new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));

        long id = 1;
        String operationName = "Poço1";
        Long potential = 100000L;
        Location location = null;
        String lineType = "PO";
        String wellName = "Poço1";
        int serviceDuration = 10000; //dias/1000

        TimeWindowedOffshoreTask task = TimeWindowedOffshoreTaskFactory.createSimpleTask( id, operationName,  location,  lineType,  wellName,  potential,  serviceDuration,  routingProblem);


        assertThat(task.getId()).isEqualTo(id);
        assertThat(task.getOperationName()).isEqualTo(operationName);
        assertThat(task.getlocation()).isEqualTo(location);
        assertThat(task.getLineType()).isEqualTo(lineType);
        assertThat(task.getWell().getName()).isEqualTo(wellName);
        assertThat(task.getWell().getPotential()).isEqualTo(potential);
        assertThat(task.getOutcome().getPotencialEntrega()).isEqualTo(potential);
        assertThat(task.getServiceDuration()).isEqualTo(serviceDuration);
    }

    @Test
    void createTask_routingProblem_must_not_be_null()  {


        final RoutingProblem  routingProblem_null = null;

        long id = 1;
        String operationName = "Poço1";
        Long potential = 100000L;
        Location location = null;
        String lineType = "PO";
        String wellName = "Poço1";
        int serviceDuration = 10000; //dias/1000

        assertThatNullPointerException().isThrownBy(() -> TimeWindowedOffshoreTaskFactory.createSimpleTask( id, operationName,  location,  lineType,  wellName,  potential,  serviceDuration,  routingProblem_null));

        //now Creating with routingProblem

        RoutingProblem routingProblem = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), new LocationData(new Coordinates(BigDecimal.valueOf(1 ),BigDecimal.valueOf(1)), "Coordenada do Poço1"), new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));

        TimeWindowedOffshoreTask task = TimeWindowedOffshoreTaskFactory.createSimpleTask( id, operationName,  location,  lineType,  wellName,  potential,  serviceDuration,  routingProblem);


        assertThat(task.getId()).isEqualTo(id);
        assertThat(task.getOperationName()).isEqualTo(operationName);
        assertThat(task.getlocation()).isEqualTo(location);
        assertThat(task.getLineType()).isEqualTo(lineType);
        assertThat(task.getWell().getName()).isEqualTo(wellName);
        assertThat(task.getWell().getPotential()).isEqualTo(potential);
        assertThat(task.getOutcome().getPotencialEntrega()).isEqualTo(potential);
        assertThat(task.getServiceDuration()).isEqualTo(serviceDuration);

    }

    @Test
    void create_3_Tasks_2_wich_have_same_id_must_be_1_Object()  {
        RoutingProblem routingProblem = new RoutingProblem(
            "Test Problem", new ArrayList<VehicleData>(), new LocationData(new Coordinates(BigDecimal.valueOf(1 ),BigDecimal.valueOf(1)), "Coordenada do Poço1"), new ArrayList<LocationData>(), new RoutingProblemParameters("PLSV","Basic"));

        long id = 1;
        String operationName = "Poço1";
        Long potential = 100000L;
        Location location = null;
        String lineType = "PO";
        String wellName = "Poço1";
        int serviceDuration = 10000; //dias/1000
        TimeWindowedOffshoreTask task = TimeWindowedOffshoreTaskFactory.createSimpleTask( id, operationName,  location,  lineType,  wellName,  potential,  serviceDuration,  routingProblem);
        assertThat(task.getId()).isEqualTo(id);
        assertThat(task.getOperationName()).isEqualTo(operationName);

        long id2 = 2;
        String operationName2 = "Poço1";
        Long potential2 = 100000L;
        Location location2 = null;
        String lineType2 = "PO";
        String wellName2 = "Poço1";
        int serviceDuration2 = 10000; //dias/1000
        TimeWindowedOffshoreTask task2 = TimeWindowedOffshoreTaskFactory.createSimpleTask( id2, operationName2,  location2,  lineType2,  wellName2,  potential2,  serviceDuration2,  routingProblem);
        assertThat(task2.getId()).isEqualTo(id2);
        assertThat(task2.getOperationName()).isEqualTo(operationName2);
        assertThat(routingProblem.getOffshoreTasks().stream()
        .filter( distinctByKey(w -> w.getId()))
        .collect( Collectors.toList()).size()).isEqualTo(2);

        long id3_value2 = 2;
        String operationName3 = "Poço1";
        Long potential3 = 100000L;
        Location location3 = null;
        String lineType3 = "PO";
        String wellName3 = "Poço1";
        int serviceDuration3 = 10000; //dias/1000
        TimeWindowedOffshoreTask task3 = TimeWindowedOffshoreTaskFactory.createSimpleTask( id3_value2, operationName3,  location3,  lineType3,  wellName3,  potential3,  serviceDuration3,  routingProblem);
        assertThat(task3.getId()).isEqualTo(id3_value2);
        assertThat(task3.getOperationName()).isEqualTo(operationName2);
        assertThat(routingProblem.getOffshoreTasks().stream()
        .filter( distinctByKey(w -> w.getId()))
        .collect( Collectors.toList()).size()).isEqualTo(2);
    }

   public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
