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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.Optional;
import java.util.stream.Collectors;
import org.optaweb.vehiclerouting.domain.Coordinates;
import org.optaweb.vehiclerouting.domain.Location;
import org.optaweb.vehiclerouting.domain.LocationData;
import org.optaweb.vehiclerouting.domain.RoutingProblem;
import org.optaweb.vehiclerouting.domain.RoutingProblemParameters;
import org.optaweb.vehiclerouting.domain.TimeWindowedOffshoreTask;
import org.optaweb.vehiclerouting.domain.TimeWindowedOffshoreTaskFactory;
//import org.optaweb.vehiclerouting.domain.TimeWindowedOffshoreTask;
import org.optaweb.vehiclerouting.domain.VehicleData;
import org.optaweb.vehiclerouting.domain.VehicleFactory;
import org.springframework.stereotype.Component;

/**
 * Data set marshaller using the YAML format.
 */
@Component
public class DataSetMarshaller {

    private final ObjectMapper mapper;

    /**
     * Create marshaller using the default object mapper, which is set up to use
     * YAML format.
     */
    DataSetMarshaller() {
        mapper = new ObjectMapper(new YAMLFactory());
    }

    /**
     * Constructor for testing purposes.
     *
     * @param mapper usually a mock object mapper
     */
    DataSetMarshaller(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Unmarshal routing problem from a reader.
     *
     * @param reader a reader
     * @return routing problem
     */
    public RoutingProblem unmarshal(Reader reader) {
        // TODO throw a checked exception that will force the caller to handle the
        // reading problem
        // (e.g. a bad format) and report it to the user or log an error
        try {
            return toDomain(unmarshalToDataSet(reader));
        } catch (Exception e) {
            System.out.println("cant unmarshal reader: " + reader.toString());
            throw new IllegalStateException("Can't  unmarshalToDataSet  demo data set Telb", e);
        }
    }

    /**
     * Marshal routing problem to string.
     *
     * @param routingProblem routing problem
     * @return string containing the marshaled routing problem
     */
    public String marshal(RoutingProblem routingProblem) {
        return marshal(toDataSet(routingProblem));
    }

    DataSetTelb unmarshalToDataSet(Reader reader) {
        DataSetTelb dataset = null;
        try {
            dataset = mapper.readValue(reader, DataSetTelb.class);
        } catch (IOException e) {
          System.out.println("cant read reader: " + reader.toString());
          throw new IllegalStateException("Can't  unmarshalToDataSet  demo data set Telb", e);
        }
        return dataset;
    }

    String marshal(DataSet dataSet) {
        try {
            return mapper.writeValueAsString(dataSet);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to marshal data set (" + dataSet.getName() + ")", e);
        }
    }

    static DataSetTelb toDataSet(RoutingProblem routingProblem) {
        DataSetTelb dataSet = new DataSetTelb();
        dataSet.setName(routingProblem.name());
        dataSet.setDepot(routingProblem.depot().map(DataSetMarshaller::toDataSet).orElse(null));
        dataSet.setVehicles(routingProblem.vehicles().stream()
                .map(DataSetMarshaller::toDataSet)
                .collect(Collectors.toList())
        );
        dataSet.setVisits(routingProblem.visits().stream()
                .map(DataSetMarshaller::toDataSet)
                .collect(Collectors.toList())
        );
        return dataSet;
    }

    static DataSetLocation toDataSet(LocationData locationData) {
        return new DataSetLocation(locationData.description(), locationData.coordinates().latitude().doubleValue(),
                locationData.coordinates().longitude().doubleValue());
    }

    static DataSetVehicle toDataSet(VehicleData vehicleData) {
        return new DataSetVehicle(vehicleData.name(), vehicleData.capacity());
    }

    static RoutingProblem toDomain(DataSetTelb dataSet) {
        try {
            RoutingProblemParameters routingProblemParameters;
            if (dataSet.getTelbParameters() != null) {
                routingProblemParameters = new RoutingProblemParameters(Optional.ofNullable(dataSet.getTelbParameters().getDemoContext()).orElse("contextDemo"), Optional.ofNullable(dataSet.getTelbParameters().getDemoComplexity()).orElse("conplexityDemo"), Optional.ofNullable(dataSet.getTelbParameters().getDemoElucidation()).orElse(""));
                routingProblemParameters.setIsloadingVesselCase(dataSet.getTelbParameters().getIsloadingVesselCase());
                routingProblemParameters.setIsloadingVesselCase(dataSet.getTelbParameters().getIsTravelTimeCase());
                routingProblemParameters.setBarrelPrice(Optional.ofNullable(dataSet.getTelbParameters().getBarrelPrice()).orElse(new Double(25)));
                routingProblemParameters.setCheckEarlyDate(Optional.ofNullable(dataSet.getTelbParameters().getCheckEarlyDate()).orElse(false));
                routingProblemParameters.setUpdateIncludingOrder(Optional.ofNullable(dataSet.getTelbParameters().getUpdateIncludingOrder()).orElse(false));
                routingProblemParameters.setBestSolutionKnowed(Optional.ofNullable(dataSet.getTelbParameters().getBestSolutionKnowed()).orElse("no best solution"));
            } else  {
                routingProblemParameters = new RoutingProblemParameters("contextDemo","conplexityDemo","");
            }

            RoutingProblem routingProblem = new RoutingProblem(Optional.ofNullable(dataSet.getName()).orElse(""),
            dataSet.getVehicles().stream().map(DataSetMarshaller::toDomain).collect(Collectors.toList()),Optional.ofNullable(dataSet.getDepot()).map(DataSetMarshaller::toDomain).orElse(null),
            dataSet.getVisits().stream().map(DataSetMarshaller::toDomain).collect(Collectors.toList()), routingProblemParameters);

            if (dataSet.getOffshoreTasksData() != null) {
                if (!dataSet.getOffshoreTasksData().isEmpty()) {
                    for (TimeWindowedOffshoreTaskData taskData : dataSet.getOffshoreTasksData()) {
                        System.out.println("taskData.getId(): "+ taskData.getId());
                        System.out.println("taskData.getOperationName(): "+ taskData.getOperationName());
                        System.out.println("taskData.getDataSetlocation().toString(): "+ taskData.getDataSetlocation().toString());
                        System.out.println("taskData.getLineType(): "+  taskData.getLineType());
                        System.out.println("taskData.getEntrega().getPotencialEntrega(): "+ taskData.getOutcome().getOutcomePotential());
                        System.out.println("taskData.getServiceDuration(): "+ taskData.getServiceDuration());

                        LocationData locationData = toDomain(taskData.getDataSetlocation());
                        System.out.println("taskData.getDataSetlocation()"+ taskData.getDataSetlocation().toString());

                        Long potential = taskData.getOutcome().getOutcomePotential();
                        if (potential == null) {
                            potential = taskData.getWell().getPotencial();
                        }
                        if (potential == null) {
                            potential = 0L;
                        }
                        TimeWindowedOffshoreTask timeWindowedOffshoreTask = TimeWindowedOffshoreTaskFactory.createSimpleTask(taskData.getId(), taskData.getOperationName(), locationData, taskData.getLineType(), taskData.getWell().getName(), potential, taskData.getServiceDuration(), routingProblem);
                        System.out.println("Task name: "+timeWindowedOffshoreTask.getOperationName() + "  Created");

                    }

                    routingProblemParameters = new RoutingProblemParameters(Optional.ofNullable(dataSet.getTelbParameters().getDemoContext()).orElse("contextDemo"), Optional.ofNullable(dataSet.getTelbParameters().getDemoComplexity()).orElse("conplexityDemo"), Optional.ofNullable(dataSet.getTelbParameters().getDemoElucidation()).orElse(""));
                    routingProblemParameters.setIsloadingVesselCase(dataSet.getTelbParameters().getIsloadingVesselCase());
                    routingProblemParameters.setIsloadingVesselCase(dataSet.getTelbParameters().getIsTravelTimeCase());
                    routingProblemParameters.setBarrelPrice(Optional.ofNullable(dataSet.getTelbParameters().getBarrelPrice()).orElse(new Double(25)));
                    routingProblemParameters.setCheckEarlyDate(Optional.ofNullable(dataSet.getTelbParameters().getCheckEarlyDate()).orElse(false));
                    routingProblemParameters.setUpdateIncludingOrder(Optional.ofNullable(dataSet.getTelbParameters().getUpdateIncludingOrder()).orElse(false));
                    routingProblemParameters.setBestSolutionKnowed(Optional.ofNullable(dataSet.getTelbParameters().getBestSolutionKnowed()).orElse("no best solution"));
                } else  {
                    System.out.println("Empty tasks");
                }
            } else  {
                System.out.println("getOffshoreTasksData() = null");
            }

            return routingProblem;
        } catch (Exception e){
            System.out.println("cant do to domain: ");
            throw new IllegalStateException("Failed to do RoutingProblem toDomaim method (" + dataSet.getName() + ")", e);
        }

    }

    static LocationData toDomain(DataSetLocation dataSetLocation) {
        return new LocationData(Coordinates.valueOf(dataSetLocation.getLatitude(), dataSetLocation.getLongitude()),
                dataSetLocation.getLabel());
    }

    static VehicleData toDomain(DataSetVehicle dataSetVehicle) {
        return VehicleFactory.vehicleData(dataSetVehicle.name, dataSetVehicle.capacity);
    }

}
