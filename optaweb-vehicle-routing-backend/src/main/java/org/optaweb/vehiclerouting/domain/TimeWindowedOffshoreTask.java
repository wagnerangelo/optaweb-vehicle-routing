package org.optaweb.vehiclerouting.domain;

import java.util.ArrayList;

public class TimeWindowedOffshoreTask extends OffshoreTask {

    private long readyTime;
    private long serviceDuration;
    private BillofPlsvMaterial billofPlsvMaterialDeltaEntering;
    private Boolean isconflitobavitcurtoprazo;
    private long readyTimeOriginal;
    private long readyTimePredecessora;
    private Outcome outcome;
    private long conflictPullinPostProcDays;
    private long conflictCVDPostProcDays;
    private long arrivalTimePublished;
    private Long arrivalTime;
    private BillofPlsvMaterial billofPlsvMaterialAtStartOperation;
    private TimeWindowedOffshoreTask criticalPathTask;
    private Long criticalPathTaskEndTime;
    private DockingBerth dockingBerth;


    public TimeWindowedOffshoreTask(long id, Location location, int demand, OffshoreTask nextOffshoreTaskData,
            OffshoreTask previousOffshoreTaskData, Vehicle vessel, String offshoreOperationType, boolean locked,
            String operationName, Well well, Project project, Long priority, int construtiveEuristicOrder,
            Integer graphLevel, Integer invertedGraphosLevel, Long investedRootId, ArrayList<OffshoreTask> sucessorList,
            ArrayList<OffshoreTask> predecessorList, Vehicle cronowebVehicle, double cronowebStartTime, String lineType,
            ArrayList<Vehicle> viableVehicles, ArrayList<Vehicle> recommendedSubPoolVehicles,
            int numberOfMapedPredecessors, boolean nodeMaped, long readyTime, long serviceDuration,
            BillofPlsvMaterial billofPlsvMaterialDeltaEntering, Boolean isconflitobavitcurtoprazo,
            long readyTimeOriginal, long readyTimePredecessora, Outcome outcome, long conflictPullinPostProcDays,
            long conflictCVDPostProcDays, long arrivalTimePublished, Long arrivalTime,
            BillofPlsvMaterial billofPlsvMaterialAtStartOperation, TimeWindowedOffshoreTask criticalPathTask,
            Long criticalPathTaskEndTime, DockingBerth dockingBerth) {
        super(id, location, demand, nextOffshoreTaskData, previousOffshoreTaskData, vessel, offshoreOperationType,
                locked, operationName, well, project, priority, construtiveEuristicOrder, graphLevel,
                invertedGraphosLevel, investedRootId, sucessorList, predecessorList, cronowebVehicle, cronowebStartTime,
                lineType, viableVehicles, recommendedSubPoolVehicles, numberOfMapedPredecessors, nodeMaped);
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

    public TimeWindowedOffshoreTask(long id, Location location, String operationName, Well well, Project project,
            String lineType, long readyTime, long serviceDuration, BillofPlsvMaterial billofPlsvMaterialDeltaEntering,
            Boolean isconflitobavitcurtoprazo, long readyTimeOriginal, long readyTimePredecessora, Outcome outcome,
            long conflictPullinPostProcDays, long conflictCVDPostProcDays, long arrivalTimePublished, Long arrivalTime,
            BillofPlsvMaterial billofPlsvMaterialAtStartOperation, TimeWindowedOffshoreTask criticalPathTask,
            Long criticalPathTaskEndTime, DockingBerth dockingBerth) {
        super(id, location, operationName, well, project, lineType);
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

    public long getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(long readyTime) {
        this.readyTime = readyTime;
    }

    public long getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(long serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public BillofPlsvMaterial getBillofPlsvMaterialDeltaEntering() {
        return billofPlsvMaterialDeltaEntering;
    }

    public void setBillofPlsvMaterialDeltaEntering(BillofPlsvMaterial billofPlsvMaterialDeltaEntering) {
        this.billofPlsvMaterialDeltaEntering = billofPlsvMaterialDeltaEntering;
    }

    public Boolean getIsconflitobavitcurtoprazo() {
        return isconflitobavitcurtoprazo;
    }

    public void setIsconflitobavitcurtoprazo(Boolean isconflitobavitcurtoprazo) {
        this.isconflitobavitcurtoprazo = isconflitobavitcurtoprazo;
    }

    public long getReadyTimeOriginal() {
        return readyTimeOriginal;
    }

    public void setReadyTimeOriginal(long readyTimeOriginal) {
        this.readyTimeOriginal = readyTimeOriginal;
    }

    public long getReadyTimePredecessora() {
        return readyTimePredecessora;
    }

    public void setReadyTimePredecessora(long readyTimePredecessora) {
        this.readyTimePredecessora = readyTimePredecessora;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public long getConflictPullinPostProcDays() {
        return conflictPullinPostProcDays;
    }

    public void setConflictPullinPostProcDays(long conflictPullinPostProcDays) {
        this.conflictPullinPostProcDays = conflictPullinPostProcDays;
    }

    public long getConflictCVDPostProcDays() {
        return conflictCVDPostProcDays;
    }

    public void setConflictCVDPostProcDays(long conflictCVDPostProcDays) {
        this.conflictCVDPostProcDays = conflictCVDPostProcDays;
    }

    public long getArrivalTimePublished() {
        return arrivalTimePublished;
    }

    public void setArrivalTimePublished(long arrivalTimePublished) {
        this.arrivalTimePublished = arrivalTimePublished;
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public BillofPlsvMaterial getBillofPlsvMaterialAtStartOperation() {
        return billofPlsvMaterialAtStartOperation;
    }

    public void setBillofPlsvMaterialAtStartOperation(BillofPlsvMaterial billofPlsvMaterialAtStartOperation) {
        this.billofPlsvMaterialAtStartOperation = billofPlsvMaterialAtStartOperation;
    }

    public TimeWindowedOffshoreTask getCriticalPathTask() {
        return criticalPathTask;
    }

    public void setCriticalPathTask(TimeWindowedOffshoreTask criticalPathTask) {
        this.criticalPathTask = criticalPathTask;
    }

    public Long getCriticalPathTaskEndTime() {
        return criticalPathTaskEndTime;
    }

    public void setCriticalPathTaskEndTime(Long criticalPathTaskEndTime) {
        this.criticalPathTaskEndTime = criticalPathTaskEndTime;
    }

    public DockingBerth getDockingBerth() {
        return dockingBerth;
    }

    public void setDockingBerth(DockingBerth dockingBerth) {
        this.dockingBerth = dockingBerth;
    }


}