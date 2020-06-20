package org.optaweb.vehiclerouting.domain;

import java.util.ArrayList;

public class Outcome {

	private long id;
    private Vehicle vehiclecaminhocrítico;
    private String indexInTaskType;
    private boolean locked;
    private String outcomeName;
    private Well well;
    private Project project;
 	private Long potencialEntrega;
    private Long outcomeTime;
    private long dueTime;
    private long leadTime;
    private long readyTimeOriginal;
    private Long readyTime;
    private long readyTimePredecessora;
	private ArrayList<TimeWindowedOffshoreTask> listaPredecessoras;
	private TimeWindowedOffshoreTask criticalPathLastTask;
	private ArrayList<Outcome> outcomeListPredecessor;
	private ArrayList<Outcome> outcomeListSucessor;
	private int graphLevel;
	private Outcome criticalPathLastOutcome;
	private Long outcomeTimeOutcomePredecessor;
	private Long outcomeTimeComposite;

    public Outcome(long id, String outcomeName, Well well) {
        this.id = id;
        this.outcomeName = outcomeName;
        this.well = well;
    }


    public Outcome(long id, Vehicle vehiclecaminhocrítico, String indexInTaskType, boolean locked, String outcomeName,
            Well well, Project project, Long potencialEntrega, Long outcomeTime, long dueTime, long leadTime,
            long readyTimeOriginal, Long readyTime, long readyTimePredecessora,
            ArrayList<TimeWindowedOffshoreTask> listaPredecessoras, TimeWindowedOffshoreTask criticalPathLastTask,
            ArrayList<Outcome> outcomeListPredecessor, ArrayList<Outcome> outcomeListSucessor, int graphLevel,
            Outcome criticalPathLastOutcome, Long outcomeTimeOutcomePredecessor, Long outcomeTimeComposite) {
        this(id,outcomeName,well);
        this.vehiclecaminhocrítico = vehiclecaminhocrítico;
        this.indexInTaskType = indexInTaskType;
        this.locked = locked;
        this.project = project;
        this.potencialEntrega = potencialEntrega;
        this.outcomeTime = outcomeTime;
        this.dueTime = dueTime;
        this.leadTime = leadTime;
        this.readyTimeOriginal = readyTimeOriginal;
        this.readyTime = readyTime;
        this.readyTimePredecessora = readyTimePredecessora;
        this.listaPredecessoras = listaPredecessoras;
        this.criticalPathLastTask = criticalPathLastTask;
        this.outcomeListPredecessor = outcomeListPredecessor;
        this.outcomeListSucessor = outcomeListSucessor;
        this.graphLevel = graphLevel;
        this.criticalPathLastOutcome = criticalPathLastOutcome;
        this.outcomeTimeOutcomePredecessor = outcomeTimeOutcomePredecessor;
        this.outcomeTimeComposite = outcomeTimeComposite;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Vehicle getVehiclecaminhocrítico() {
        return vehiclecaminhocrítico;
    }

    public void setVehiclecaminhocrítico(Vehicle vehiclecaminhocrítico) {
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

    public Well getWell() {
        return well;
    }

    public void setWell(Well well) {
        this.well = well;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getPotencialEntrega() {
        return potencialEntrega;
    }

    public void setPotencialEntrega(Long potencialEntrega) {
        this.potencialEntrega = potencialEntrega;
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

    public ArrayList<TimeWindowedOffshoreTask> getListaPredecessoras() {
        return listaPredecessoras;
    }

    public void setListaPredecessoras(ArrayList<TimeWindowedOffshoreTask> listaPredecessoras) {
        this.listaPredecessoras = listaPredecessoras;
    }

    public TimeWindowedOffshoreTask getCriticalPathLastTask() {
        return criticalPathLastTask;
    }

    public void setCriticalPathLastTask(TimeWindowedOffshoreTask criticalPathLastTask) {
        this.criticalPathLastTask = criticalPathLastTask;
    }

    public ArrayList<Outcome> getOutcomeListPredecessor() {
        return outcomeListPredecessor;
    }

    public void setOutcomeListPredecessor(ArrayList<Outcome> outcomeListPredecessor) {
        this.outcomeListPredecessor = outcomeListPredecessor;
    }

    public ArrayList<Outcome> getOutcomeListSucessor() {
        return outcomeListSucessor;
    }

    public void setOutcomeListSucessor(ArrayList<Outcome> outcomeListSucessor) {
        this.outcomeListSucessor = outcomeListSucessor;
    }

    public int getGraphLevel() {
        return graphLevel;
    }

    public void setGraphLevel(int graphLevel) {
        this.graphLevel = graphLevel;
    }

    public Outcome getCriticalPathLastOutcome() {
        return criticalPathLastOutcome;
    }

    public void setCriticalPathLastOutcome(Outcome criticalPathLastOutcome) {
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

}