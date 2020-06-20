package org.optaweb.vehiclerouting.service.demo.dataset;


public class TimeWindowedOffshoreTaskData extends OffshoreTaskData   {

    private long readyTime;
    private long serviceDuration;
    private BillofPlsvMaterialData billofPlsvMaterialDeltaEntering;
    private Boolean isconflitobavitcurtoprazo;
    private long readyTimeOriginal;
    private long readyTimePredecessora;
    private OutcomeData entrega;
    private long conflictPullinPostProcDays;
    private long conflictCVDPostProcDays;
    private long arrivalTimePublished;
    private Long arrivalTime;
    private BillofPlsvMaterialData billofPlsvMaterialAtStartOperation;
	private TimeWindowedOffshoreTaskData  criticalPathTask;
    private Long criticalPathTaskEndTime;
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

    public OutcomeData getEntrega() {
        return entrega;
    }

    public void setEntrega(OutcomeData entrega) {
        this.entrega = entrega;
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