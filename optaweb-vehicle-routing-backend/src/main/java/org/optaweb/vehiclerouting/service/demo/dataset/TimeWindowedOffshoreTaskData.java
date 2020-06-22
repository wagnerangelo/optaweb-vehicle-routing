package org.optaweb.vehiclerouting.service.demo.dataset;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeWindowedOffshoreTaskData extends OffshoreTaskData   {
    @JsonProperty(value = "readyTime")
    private long readyTime;
    @JsonProperty(value = "serviceDuration")
    private long serviceDuration;
    @JsonProperty(value = "billofPlsvMaterialDeltaEntering")
    private BillofPlsvMaterialData billofPlsvMaterialDeltaEntering;
    @JsonProperty(value = "isconflitobavitcurtoprazo")
    private Boolean isconflitobavitcurtoprazo;
    @JsonProperty(value = "readyTimeOriginal")
    private long readyTimeOriginal;
    @JsonProperty(value = "readyTimePredecessora")
    private long readyTimePredecessora;
    @JsonProperty(value = "outcome")
    private OutcomeData outcome;
    @JsonProperty(value = "conflictPullinPostProcDays")
    private long conflictPullinPostProcDays;
    @JsonProperty(value = "conflictCVDPostProcDays")
    private long conflictCVDPostProcDays;
    @JsonProperty(value = "arrivalTimePublished")
    private long arrivalTimePublished;
    @JsonProperty(value = "arrivalTime")
    private Long arrivalTime;
    @JsonProperty(value = "billofPlsvMaterialAtStartOperation")
    private BillofPlsvMaterialData billofPlsvMaterialAtStartOperation;
    @JsonProperty(value = "criticalPathTask")
	private TimeWindowedOffshoreTaskData criticalPathTask;
    @JsonProperty(value = "criticalPathTaskEndTime")
    private Long criticalPathTaskEndTime;
    @JsonProperty(value = "dockingBerthData")
    private DockingBerthData dockingBerthData;

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

    public BillofPlsvMaterialData getBillofPlsvMaterialDeltaEntering() {
        return billofPlsvMaterialDeltaEntering;
    }

    public void setBillofPlsvMaterialDeltaEntering(BillofPlsvMaterialData billofPlsvMaterialDeltaEntering) {
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

    public OutcomeData getOutcome() {
        return outcome;
    }

    public void setOutcome(OutcomeData outcome) {
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

    public BillofPlsvMaterialData getBillofPlsvMaterialAtStartOperation() {
        return billofPlsvMaterialAtStartOperation;
    }

    public void setBillofPlsvMaterialAtStartOperation(BillofPlsvMaterialData billofPlsvMaterialAtStartOperation) {
        this.billofPlsvMaterialAtStartOperation = billofPlsvMaterialAtStartOperation;
    }

    public TimeWindowedOffshoreTaskData getCriticalPathTask() {
        return criticalPathTask;
    }

    public void setCriticalPathTask(TimeWindowedOffshoreTaskData criticalPathTask) {
        this.criticalPathTask = criticalPathTask;
    }

    public Long getCriticalPathTaskEndTime() {
        return criticalPathTaskEndTime;
    }

    public void setCriticalPathTaskEndTime(Long criticalPathTaskEndTime) {
        this.criticalPathTaskEndTime = criticalPathTaskEndTime;
    }

    public DockingBerthData getDockingBerthData() {
        return dockingBerthData;
    }

    public void setDockingBerthData(DockingBerthData dockingBerthData) {
        this.dockingBerthData = dockingBerthData;
    }

}