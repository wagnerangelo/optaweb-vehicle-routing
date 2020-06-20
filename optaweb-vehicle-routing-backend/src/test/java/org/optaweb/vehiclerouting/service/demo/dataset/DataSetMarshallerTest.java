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

package org.optaweb.vehiclerouting.service.demo.dataset;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.optaweb.vehiclerouting.domain.Coordinates;
import org.optaweb.vehiclerouting.domain.LocationData;
import org.optaweb.vehiclerouting.domain.RoutingProblem;
import org.optaweb.vehiclerouting.domain.VehicleData;
import org.optaweb.vehiclerouting.domain.VehicleFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.optaweb.vehiclerouting.service.demo.dataset.DataSetMarshaller.toDataSet;
import static org.optaweb.vehiclerouting.service.demo.dataset.DataSetMarshaller.toDomain;

class DataSetMarshallerTest {

    @Test
    void unmarshal_data_set_no_parameters() throws IOException {
        DataSet dataSet = null;
        try (InputStream inputStream = DataSetMarshallerTest.class.getResourceAsStream("test-belgium.yaml")) {
            dataSet = new DataSetMarshaller()
                    .unmarshalToDataSet(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        }
        assertThat(dataSet).isNotNull();
        assertThat(dataSet.getName()).isEqualTo("Belgium test");
        assertThat(dataSet.getDepot()).isNotNull();
        assertThat(dataSet.getDepot().getLabel()).isEqualTo("Brussels");
        assertThat(dataSet.getDepot().getLatitude()).isEqualTo(50.85);
        assertThat(dataSet.getDepot().getLongitude()).isEqualTo(4.35);

        assertThat(dataSet.getVisits()).extracting("label").containsExactlyInAnyOrder("Aalst", "Châtelet",
                "La Louvière", "Sint-Niklaas", "Ypres");
    }

    @Test
    void unmarshal_data_set_with_parameters() throws IOException {
        DataSet dataSet = null;
        try (InputStream inputStream = DataSetMarshallerTest.class.getResourceAsStream("test-belgium-plus-parameters.yaml")) {
            dataSet = new DataSetMarshaller()
                    .unmarshalToDataSet(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        }
        assertThat(dataSet).isNotNull();
        assertThat(dataSet.getName()).isEqualTo("Telb Belgium test with parameters");
        assertThat(dataSet.getDepot()).isNotNull();
        assertThat(dataSet.getDepot().getLabel()).isEqualTo("Brussels");
        assertThat(dataSet.getDepot().getLatitude()).isEqualTo(50.85);
        assertThat(dataSet.getDepot().getLongitude()).isEqualTo(4.35);
        assertThat(dataSet.getVisits()).extracting("label").containsExactlyInAnyOrder("Aalst", "Châtelet",
                "La Louvière", "Sint-Niklaas", "Ypres");
        assertThat(dataSet.getName().substring(0, 4)).isEqualTo("Telb");
        assertThat(((DataSetTelb) dataSet).getTelbParameters().getDemoContext()).isEqualTo("PLSV");
        assertThat(((DataSetTelb) dataSet).getTelbParameters().getDemoComplexity()).isEqualTo("Basic");
    }

    @Test
    void unmarshal_data_set_with_parameters_1_1_1_1() throws IOException {
        DataSet dataSet = null;
        try (InputStream inputStream = DataSetMarshallerTest.class.getResourceAsStream("test-telb-1-1-1-1 2P6O no travel Prod.yaml")) {
            dataSet = new DataSetMarshaller()
                    .unmarshalToDataSet(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        }
        assertThat(dataSet).isNotNull();
        assertThat(dataSet.getName()).isEqualTo("Telb 1.1.1.1 2 Poços, sem carregamento, sem navegação, sem data mais cedo");
        assertThat(dataSet.getDepot()).isNotNull();
        assertThat(dataSet.getDepot().getLabel()).isEqualTo("Brussels");
        assertThat(dataSet.getDepot().getLatitude()).isEqualTo(50.85);
        assertThat(dataSet.getDepot().getLongitude()).isEqualTo(4.35);
        assertThat(dataSet.getVisits()).extracting("label").containsExactlyInAnyOrder("Aalst", "Châtelet",
                "La Louvière", "Sint-Niklaas", "Ypres");
        assertThat(dataSet.getName().substring(0, 4)).isEqualTo("Telb");
        assertThat(((DataSetTelb) dataSet).getTelbParameters().getDemoContext()).isEqualTo("PLSV");
        assertThat(((DataSetTelb) dataSet).getTelbParameters().getDemoComplexity()).isEqualTo("Basic");
        assertThat(((DataSetTelb) dataSet).getTelbParameters().getDemoInitialDate()).isEqualTo("01/01/2021");
        System.out.println(((DataSetTelb) dataSet).getTelbParameters().getDemoElucidation());

        assertThat(dataSet.getVisits())
                .extracting("label")
                .containsExactlyInAnyOrder("Aalst", "Châtelet", "La Louvière", "Sint-Niklaas", "Ypres");
        assertThat(dataSet.getVehicles())
                .extracting(dataSetVehicle -> dataSetVehicle.name, dataSetVehicle -> dataSetVehicle.capacity)
                .containsExactlyInAnyOrder(
                        tuple("vehicle 1", 10),
                        tuple("vehicle 2", 12),
                        tuple("vehicle 3", 1_000_000)
                );

    }

    @Test
    void unmarshal_data_set_with_OffshoreTaksData() throws IOException {
        DataSet dataSet = null;
        try (InputStream inputStream = DataSetMarshallerTest.class.getResourceAsStream("test-telb-1-1-1-1 2P6O no travel Prod REVC.yaml")) {
            dataSet = new DataSetMarshaller()
                    .unmarshalToDataSet(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        }
        assertThat(dataSet).isNotNull();
        assertThat(dataSet.getName()).isEqualTo("Telb 1.1.1.1 2 Poços, sem carregamento, sem navegação, sem data mais cedo REV C");

        assertThat(dataSet.getVisits())
                .extracting("label")
                .containsExactlyInAnyOrder("Aalst", "Châtelet", "La Louvière", "Sint-Niklaas", "Ypres");
        assertThat(dataSet.getVehicles())
                .extracting(dataSetVehicle -> dataSetVehicle.name, dataSetVehicle -> dataSetVehicle.capacity)
                .containsExactlyInAnyOrder(
                        tuple("PLSV 1", 12000),
                        tuple("PLSV 2", 12000)
                );

    }

    @Test
    void marshal_data_set() {
        DataSet dataSet = new DataSet();
        String name = "Test data set";
        dataSet.setName(name);
        DataSetLocation depot = new DataSetLocation("Depot", -1.1, -9.9);
        DataSetLocation location1 = new DataSetLocation("Location 1", 1.0, 0.1);
        DataSetLocation location2 = new DataSetLocation("Location 2", 2.0, 0.2);
        dataSet.setDepot(depot);
        dataSet.setVisits(Arrays.asList(location1, location2));
        DataSetVehicle vehicle1 = new DataSetVehicle("Vehicle 1", 123);
        DataSetVehicle vehicle2 = new DataSetVehicle("Vehicle 2", 222);
        dataSet.setVehicles(Arrays.asList(vehicle1, vehicle2));

        String yaml = new DataSetMarshaller().marshal(dataSet);

        assertThat(yaml)
                .contains("name: \"" + name)
                .contains(
                        depot.getLabel(),
                        location1.getLabel(),
                        location2.getLabel(),
                        vehicle1.name,
                        vehicle2.name,
                        String.valueOf(vehicle1.capacity),
                        String.valueOf(vehicle2.capacity)
                );

    }

    //TODO marshal_dataSetTelb()

    @Test
    void should_rethrow_exception_from_object_mapper() throws IOException {
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        when(objectMapper.readValue(any(Reader.class), eq(DataSetTelb.class))).thenThrow(IOException.class);
        assertThatIllegalStateException()
                .isThrownBy(() -> new DataSetMarshaller(objectMapper).unmarshalToDataSet(mock(Reader.class)))
                .withRootCauseExactlyInstanceOf(IOException.class);

        when(objectMapper.writeValueAsString(any(DataSetTelb.class))).thenThrow(JsonProcessingException.class);
        assertThatIllegalStateException().isThrownBy(() -> new DataSetMarshaller(objectMapper).marshal(new DataSetTelb()))
                .withRootCauseExactlyInstanceOf(JsonProcessingException.class);
    }

    @Test
    void location_conversion() {
        double lat = -1.0;
        double lng = 50.2;
        String description = "some location";

        // domain -> data set
        DataSetLocation dataSetLocation = toDataSet(new LocationData(Coordinates.valueOf(lat, lng), description));
        assertThat(dataSetLocation.getLatitude()).isEqualTo(lat);
        assertThat(dataSetLocation.getLongitude()).isEqualTo(lng);
        assertThat(dataSetLocation.getLabel()).isEqualTo(description);

        // data set -> domain
        LocationData location = toDomain(dataSetLocation);
        assertThat(location).isEqualTo(new LocationData(Coordinates.valueOf(lat, lng), description));
    }

    @Test
    void routing_problem_conversion() {
        VehicleData vehicle = VehicleFactory.vehicleData("vehicle", 10);
        List<VehicleData> vehicles = Arrays.asList(vehicle);
        LocationData depot = new LocationData(Coordinates.valueOf(60.1, 5.78), "Depot");
        LocationData visit = new LocationData(Coordinates.valueOf(1.06, 8.75), "Visit");
        List<LocationData> visits = Arrays.asList(visit);
        String name = "some data set";

        // domain -> data set

        DataSetTelb dataSet = toDataSet(new RoutingProblem(name, vehicles, depot, visits, null));
        assertThat(dataSet.getName()).isEqualTo(name);
        assertThat(dataSet.getVehicles()).hasSameSizeAs(vehicles);
        assertThat(toDomain(dataSet.getVehicles().get(0))).isEqualTo(vehicle);
        assertThat(toDomain(dataSet.getDepot())).isEqualTo(depot);
        assertThat(dataSet.getVisits()).hasSameSizeAs(visits);
        assertThat(toDomain(dataSet.getVisits().get(0))).isEqualTo(visit);

        // data set -> domain
        RoutingProblem routingProblem = toDomain(dataSet);
        assertThat(routingProblem.name()).isEqualTo(name);
        assertThat(routingProblem.vehicles()).containsExactly(vehicle);
        assertThat(routingProblem.depot()).contains(depot);
        assertThat(routingProblem.visits()).containsExactly(visit);
    }

    @Test
    void should_convert_empty_data_set_correctly() {
        DataSetTelb emptyDataSet = new DataSetTelb();
        RoutingProblem routingProblem = toDomain(emptyDataSet);
        assertThat(routingProblem.name()).isEmpty();
        assertThat(routingProblem.depot()).isEmpty();
        assertThat(routingProblem.vehicles()).isEmpty();
        assertThat(routingProblem.visits()).isEmpty();
    }
}
