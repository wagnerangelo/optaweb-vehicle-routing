package org.optaweb.vehiclerouting.plugin.websocket;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.optaweb.vehiclerouting.domain.TimeWindowedOffshoreTask;

public class PortableTimeWindowedOffshoreTask extends PortableOffshoreTask {
    @JsonProperty(value = "readyTime")
    private final long readyTime;
    @JsonProperty(value = "serviceDuration")
    private final long serviceDuration;
    @JsonProperty(value = "billofPlsvMaterialDeltaEntering")
    private final PortableBillofPlsvMaterial billofPlsvMaterialDeltaEntering;
    @JsonProperty(value = "isconflitobavitcurtoprazo")
    private final Boolean isconflitobavitcurtoprazo;
    @JsonProperty(value = "readyTimeOriginal")
    private final long readyTimeOriginal;
    @JsonProperty(value = "readyTimePredecessora")
    private final long readyTimePredecessora;
    @JsonProperty(value = "outcome")
    private final PortableOutcome outcome;
    @JsonProperty(value = "conflictPullinPostProcDays")
    private final long conflictPullinPostProcDays;
    @JsonProperty(value = "conflictCVDPostProcDays")
    private final long conflictCVDPostProcDays;
    @JsonProperty(value = "arrivalTimePublished")
    private final long arrivalTimePublished;
    @JsonProperty(value = "arrivalTime")
    private final Long arrivalTime;
    @JsonProperty(value = "billofPlsvMaterialAtStartOperation")
    private final PortableBillofPlsvMaterial billofPlsvMaterialAtStartOperation;
    @JsonProperty(value = "criticalPathTask")
    private final PortableTimeWindowedOffshoreTask criticalPathTask;
    @JsonProperty(value = "criticalPathTaskEndTime")
    private final Long criticalPathTaskEndTime;
    @JsonProperty(value = "dockingBerth")
    private final PortableDockingBerth dockingBerth;

    @JsonCreator
    public PortableTimeWindowedOffshoreTask(long id, PortableLocation portableLocation, int demand,
            PortableOffshoreTask portableNextOffshoreTaskData, PortableOffshoreTask portablePreviousOffshoreTaskData,
            PortableVehicle portableVessel, String offshoreOperationType, boolean locked, String operationName,
            PortableWell portableWell, PortableProject project, Long priority, int construtiveEuristicOrder,
            Integer graphLevel, Integer invertedGraphosLevel, Long investedRootId,
            List<PortableTimeWindowedOffshoreTask> portableSuccessorList,
            List<PortableTimeWindowedOffshoreTask> portablePredecessorList, PortableVehicle portableCronowebVehicle,
            double cronowebStartTime, String lineType, List<PortableVehicle> portableViableVehicles,
            List<PortableVehicle> portableRecommendedSubPoolVehicles, int numberOfMapedPredecessors, boolean nodeMaped,
            long readyTime, long serviceDuration, PortableBillofPlsvMaterial billofPlsvMaterialDeltaEntering,
            Boolean isconflitobavitcurtoprazo, long readyTimeOriginal, long readyTimePredecessora,
            PortableOutcome outcome, long conflictPullinPostProcDays, long conflictCVDPostProcDays,
            long arrivalTimePublished, Long arrivalTime, PortableBillofPlsvMaterial billofPlsvMaterialAtStartOperation,
            PortableTimeWindowedOffshoreTask criticalPathTask, Long criticalPathTaskEndTime,
            PortableDockingBerth dockingBerth) {
        super(id, portableLocation, demand, portableNextOffshoreTaskData, portablePreviousOffshoreTaskData,
                portableVessel, offshoreOperationType, locked, operationName, portableWell, project, priority,
                construtiveEuristicOrder, graphLevel, invertedGraphosLevel, investedRootId, portableSuccessorList,
                portablePredecessorList, portableCronowebVehicle, cronowebStartTime, lineType, portableViableVehicles,
                portableRecommendedSubPoolVehicles, numberOfMapedPredecessors, nodeMaped);
        this.readyTime = readyTime;
        this.serviceDuration = serviceDuration;
        this.billofPlsvMaterialDeltaEntering = billofPlsvMaterialDeltaEntering;
        this.isconflitobavitcurtoprazo = isconflitobavitcurtoprazo;
        this.readyTimeOriginal = readyTimeOriginal;
        this.readyTimePredecessora = readyTimePredecessora;
        this.outcome = outcome;
        this.conflictPullinPostProcDays = conflictPullinPostProcDays;
        this.conflictCVDPostProcDays = conflictCVDPostProcDays;
        this.arrivalTimePublished = arrivalTimePublished;
        this.arrivalTime = arrivalTime;
        this.billofPlsvMaterialAtStartOperation = billofPlsvMaterialAtStartOperation;
        this.criticalPathTask = criticalPathTask;
        this.criticalPathTaskEndTime = criticalPathTaskEndTime;
        this.dockingBerth = dockingBerth;
    }

    static PortableTimeWindowedOffshoreTask createFromTimeWindowdOffshoreTask(TimeWindowedOffshoreTask twOffshoreTask){
        if (twOffshoreTask != null) {
            List<PortableTimeWindowedOffshoreTask> portableSuccessorList = null;
            if (twOffshoreTask.getSucessorList() != null) {
                portableSuccessorList = twOffshoreTask.getSucessorList().stream().map(offshoreSucessorTask -> PortableTimeWindowedOffshoreTask.createFromTimeWindowdOffshoreTask(
                        (TimeWindowedOffshoreTask) offshoreSucessorTask)).collect(Collectors.toList());
            }
            List<PortableTimeWindowedOffshoreTask> portablePredecessorList = null;
            if (twOffshoreTask.getPredecessorList() != null) {
                 portablePredecessorList = twOffshoreTask.getPredecessorList().stream().map(offshorePredecessorTask -> PortableTimeWindowedOffshoreTask.createFromTimeWindowdOffshoreTask( (TimeWindowedOffshoreTask) offshorePredecessorTask)).collect(Collectors.toList());
            }
            List<PortableVehicle> portableViableVehicles = null;
            if (twOffshoreTask.getViableVehicles() != null ) {
                portableViableVehicles = twOffshoreTask.getViableVehicles().stream().map(viableVehicle -> PortableVehicle.fromVehicle(viableVehicle)).collect(Collectors.toList());
            }
            List<PortableVehicle> portableRecommendedSubPoolVehicles = null;
            if (twOffshoreTask.getRecommendedSubPoolVehicles() != null ) {
                portableRecommendedSubPoolVehicles = twOffshoreTask.getRecommendedSubPoolVehicles().stream().map(recommendedSubPoolVehicles -> PortableVehicle.fromVehicle(recommendedSubPoolVehicles)).collect(Collectors.toList());
            }

            return new PortableTimeWindowedOffshoreTask(twOffshoreTask.getId(), PortableLocation.fromLocation(twOffshoreTask.getlocation()), twOffshoreTask.getDemand(), PortableTimeWindowedOffshoreTask.createFromTimeWindowdOffshoreTask((TimeWindowedOffshoreTask) twOffshoreTask.getNextOffshoreTaskData()),
            PortableTimeWindowedOffshoreTask.createFromTimeWindowdOffshoreTask((TimeWindowedOffshoreTask) twOffshoreTask.getPreviousOffshoreTaskData()), PortableVehicle.fromVehicle(twOffshoreTask.getVessel()), twOffshoreTask.getOffshoreOperationType(), twOffshoreTask.isLocked(), twOffshoreTask.getOperationName(),
            PortableWell.fromWell(twOffshoreTask.getWell()), PortableProject.fromProject(twOffshoreTask.getProject()), twOffshoreTask.getPriority(), twOffshoreTask.getConstrutiveEuristicOrder(),
            twOffshoreTask.getGraphLevel(),  twOffshoreTask.getInvertedGraphosLevel(),  twOffshoreTask.getInvestedRootId(), portableSuccessorList, portablePredecessorList, PortableVehicle.fromVehicle(twOffshoreTask.getCronowebVehicle()), twOffshoreTask.getCronowebStartTime(), twOffshoreTask.getLineType(),
            portableViableVehicles, portableRecommendedSubPoolVehicles, twOffshoreTask.getNumberOfMapedPredecessors(),twOffshoreTask.isNodeMaped(), twOffshoreTask.getReadyTime(), twOffshoreTask.getServiceDuration(),
            PortableBillofPlsvMaterial.createFrom(twOffshoreTask.getBillofPlsvMaterialDeltaEntering()), twOffshoreTask.getIsconflitobavitcurtoprazo(),twOffshoreTask.getReadyTimeOriginal(), twOffshoreTask.getReadyTimePredecessora(), PortableOutcome.createFrom(twOffshoreTask.getOutcome()), twOffshoreTask.getConflictPullinPostProcDays(), twOffshoreTask.getConflictCVDPostProcDays(), twOffshoreTask.getArrivalTimePublished(), twOffshoreTask.getArrivalTime(),
            PortableBillofPlsvMaterial.createFrom(twOffshoreTask.getBillofPlsvMaterialAtStartOperation()),
            PortableTimeWindowedOffshoreTask.createFromTimeWindowdOffshoreTask(twOffshoreTask.getCriticalPathTask()),twOffshoreTask.getCriticalPathTaskEndTime(),PortableDockingBerth.createFrom(twOffshoreTask.getDockingBerth()));

        } else {
            return null;
        }
    }



    public long getReadyTime() {
        return readyTime;
    }

    public long getServiceDuration() {
        return serviceDuration;
    }

    public PortableBillofPlsvMaterial getBillofPlsvMaterialDeltaEntering() {
        return billofPlsvMaterialDeltaEntering;
    }

    public Boolean getIsconflitobavitcurtoprazo() {
        return isconflitobavitcurtoprazo;
    }

    public long getReadyTimeOriginal() {
        return readyTimeOriginal;
    }

    public long getReadyTimePredecessora() {
        return readyTimePredecessora;
    }

    public PortableOutcome getOutcome() {
        return outcome;
    }

    public long getConflictPullinPostProcDays() {
        return conflictPullinPostProcDays;
    }

    public long getConflictCVDPostProcDays() {
        return conflictCVDPostProcDays;
    }

    public long getArrivalTimePublished() {
        return arrivalTimePublished;
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }

    public PortableBillofPlsvMaterial getBillofPlsvMaterialAtStartOperation() {
        return billofPlsvMaterialAtStartOperation;
    }

    public PortableTimeWindowedOffshoreTask getCriticalPathTask() {
        return criticalPathTask;
    }

    public Long getCriticalPathTaskEndTime() {
        return criticalPathTaskEndTime;
    }

    public PortableDockingBerth getDockingBerth() {
        return dockingBerth;
    }
}