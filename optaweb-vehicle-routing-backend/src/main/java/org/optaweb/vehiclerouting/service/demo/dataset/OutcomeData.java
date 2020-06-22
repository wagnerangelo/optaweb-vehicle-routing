package org.optaweb.vehiclerouting.service.demo.dataset;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutcomeData {

    @JsonProperty(value = "id")
    private long id;
    @JsonProperty(value = "vehiclecaminhocrítico")
    private DataSetVehicle vehiclecaminhocrítico;
    @JsonProperty(value = "indexInTaskType")
    private String indexInTaskType;
    @JsonProperty(value = "locked")
    private boolean locked;
    @JsonProperty(value = "outcomeName")
    private String outcomeName;
    @JsonProperty(value = "well")
    private WellData well;
    @JsonProperty(value = "project")
    private Project project;
    @JsonProperty(value = "outcomePotential")
 	private Long outcomePotential;
     @JsonProperty(value = "outcomeTime")
    private Long outcomeTime;
    @JsonProperty(value = "dueTime")
    private long dueTime;
    @JsonProperty(value = "leadTime")
    private long leadTime;
    @JsonProperty(value = "readyTimeOriginal")
    private long readyTimeOriginal;
    @JsonProperty(value = "readyTime")
    private Long readyTime;
    @JsonProperty(value = "readyTimePredecessora")
    private long readyTimePredecessora;
    @JsonProperty(value = "listaPredecessoras")
	private ArrayList<TimeWindowedOffshoreTaskData> listaPredecessoras;
    @JsonProperty(value = "criticalPathLastTask")
	private TimeWindowedOffshoreTaskData criticalPathLastTask;
    @JsonProperty(value = "outcomeListPredecessor")
	private ArrayList<OutcomeData> outcomeListPredecessor;
    @JsonProperty(value = "outcomeListSucessor")
	private ArrayList<OutcomeData> outcomeListSucessor;
    @JsonProperty(value = "graphLevel")
	private int graphLevel;
    @JsonProperty(value = "criticalPathLastOutcome")
	private OutcomeData criticalPathLastOutcome;
    @JsonProperty(value = "outcomeTimeOutcomePredecessor")
	private Long outcomeTimeOutcomePredecessor;
    @JsonProperty(value = "outcomeTimeComposite")
	private Long outcomeTimeComposite;

    public DataSetVehicle getVehiclecaminhocrítico() {
        return vehiclecaminhocrítico;
    }

    public void setVehiclecaminhocrítico(DataSetVehicle vehiclecaminhocrítico) {
        this.vehiclecaminhocrítico = vehiclecaminhocrítico;
    }

    public String getIndexInTaskType() {
        return indexInTaskType;
    }

    public void setIndexInTaskType(String indexInTaskType) {
        this.indexInTaskType = indexInTaskType;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getOutcomeName() {
        return outcomeName;
    }

    public void setOutcomeName(String outcomeName) {
        this.outcomeName = outcomeName;
    }

    public WellData getWell() {
        return well;
    }

    public void setWell(WellData well) {
        this.well = well;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getOutcomePotential() {
        return outcomePotential;
    }

    public void setOutcomePotential(Long outcomePotential) {
        this.outcomePotential = outcomePotential;
    }

    public Long getOutcomeTime() {
        return outcomeTime;
    }

    public void setOutcomeTime(Long outcomeTime) {
        this.outcomeTime = outcomeTime;
    }

    public long getDueTime() {
        return dueTime;
    }

    public void setDueTime(long dueTime) {
        this.dueTime = dueTime;
    }

    public long getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(long leadTime) {
        this.leadTime = leadTime;
    }

    public long getReadyTimeOriginal() {
        return readyTimeOriginal;
    }

    public void setReadyTimeOriginal(long readyTimeOriginal) {
        this.readyTimeOriginal = readyTimeOriginal;
    }

    public Long getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(Long readyTime) {
        this.readyTime = readyTime;
    }

    public long getReadyTimePredecessora() {
        return readyTimePredecessora;
    }

    public void setReadyTimePredecessora(long readyTimePredecessora) {
        this.readyTimePredecessora = readyTimePredecessora;
    }

    public ArrayList<TimeWindowedOffshoreTaskData> getListaPredecessoras() {
        return listaPredecessoras;
    }

    public void setListaPredecessoras(ArrayList<TimeWindowedOffshoreTaskData> listaPredecessoras) {
        this.listaPredecessoras = listaPredecessoras;
    }

    public TimeWindowedOffshoreTaskData getCriticalPathLastTask() {
        return criticalPathLastTask;
    }

    public void setCriticalPathLastTask(TimeWindowedOffshoreTaskData criticalPathLastTask) {
        this.criticalPathLastTask = criticalPathLastTask;
    }

    public ArrayList<OutcomeData> getOutcomeListPredecessor() {
        return outcomeListPredecessor;
    }

    public void setOutcomeListPredecessor(ArrayList<OutcomeData> outcomeListPredecessor) {
        this.outcomeListPredecessor = outcomeListPredecessor;
    }

    public ArrayList<OutcomeData> getOutcomeListSucessor() {
        return outcomeListSucessor;
    }

    public void setOutcomeListSucessor(ArrayList<OutcomeData> outcomeListSucessor) {
        this.outcomeListSucessor = outcomeListSucessor;
    }

    public int getGraphLevel() {
        return graphLevel;
    }

    public void setGraphLevel(int graphLevel) {
        this.graphLevel = graphLevel;
    }

    public OutcomeData getCriticalPathLastOutcome() {
        return criticalPathLastOutcome;
    }

    public void setCriticalPathLastOutcome(OutcomeData criticalPathLastOutcome) {
        this.criticalPathLastOutcome = criticalPathLastOutcome;
    }

    public Long getOutcomeTimeOutcomePredecessor() {
        return outcomeTimeOutcomePredecessor;
    }

    public void setOutcomeTimeOutcomePredecessor(Long outcomeTimeOutcomePredecessor) {
        this.outcomeTimeOutcomePredecessor = outcomeTimeOutcomePredecessor;
    }

    public Long getOutcomeTimeComposite() {
        return outcomeTimeComposite;
    }

    public void setOutcomeTimeComposite(Long outcomeTimeComposite) {
        this.outcomeTimeComposite = outcomeTimeComposite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}