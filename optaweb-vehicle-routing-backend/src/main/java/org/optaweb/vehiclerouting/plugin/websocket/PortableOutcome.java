package org.optaweb.vehiclerouting.plugin.websocket;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.optaweb.vehiclerouting.domain.Outcome;
public class PortableOutcome {

    @JsonProperty(value = "id")
	private final long id;
    @JsonProperty(value = "portableVehiclecaminhocrítico")
    private final PortableVehicle portableVehiclecaminhocrítico;
    @JsonProperty(value = "indexInTaskType")
    private final String indexInTaskType;
    @JsonProperty(value = "locked")
    private final boolean locked;
    @JsonProperty(value = "outcomeName")
    private final String outcomeName;
    @JsonProperty(value = "portableWell")
    private final PortableWell portableWell;
    @JsonProperty(value = "portableProject")
    private final PortableProject portableProject;
    @JsonProperty(value = "potential")
    private final Long potential;
    @JsonProperty(value = "outcomeTime")
    private final Long outcomeTime;
    @JsonProperty(value = "dueTime")
    private final long dueTime;
    @JsonProperty(value = "leadTime")
    private final long leadTime;
    @JsonProperty(value = "readyTimeOriginal")
    private final long readyTimeOriginal;
    @JsonProperty(value = "readyTime")
    private final Long readyTime;
    @JsonProperty(value = "readyTimePredecessor")
    private final long readyTimePredecessora;
    @JsonProperty(value = "listaPredecessoras")
    private final List<PortableTimeWindowedOffshoreTask> listaPredecessoras;
    @JsonProperty(value = "criticalPathLastTask")
	private final PortableTimeWindowedOffshoreTask criticalPathLastTask;
    @JsonProperty(value = "outcomeListPredecessor")
    private final List<PortableOutcome> outcomeListPredecessor;
    @JsonProperty(value = "outcomeListSucessor")
    private final List<PortableOutcome> outcomeListSucessor;
    @JsonProperty(value = "graphLevel")
    private final int graphLevel;
    @JsonProperty(value = "criticalPathLastOutcome")
    private final PortableOutcome criticalPathLastOutcome;
    @JsonProperty(value = "outcomeTimeOutcomePredecessor")
    private final Long outcomeTimeOutcomePredecessor;
    @JsonProperty(value = "outcomeTimeComposite")
    private final Long outcomeTimeComposite;

    @JsonCreator
    public PortableOutcome(long id, PortableVehicle portableVehiclecaminhocrítico, String indexInTaskType,
            boolean locked, String outcomeName, PortableWell portableWell, PortableProject portableProject,
            Long potential, Long outcomeTime, long dueTime, long leadTime, long readyTimeOriginal, Long readyTime,
            long readyTimePredecessora, List<PortableTimeWindowedOffshoreTask> listaPredecessoras,
            PortableTimeWindowedOffshoreTask criticalPathLastTask, List<PortableOutcome> outcomeListPredecessor,
            List<PortableOutcome> outcomeListSucessor, int graphLevel, PortableOutcome criticalPathLastOutcome,
            Long outcomeTimeOutcomePredecessor, Long outcomeTimeComposite) {
        this.id = id;
        this.portableVehiclecaminhocrítico = portableVehiclecaminhocrítico;
        this.indexInTaskType = indexInTaskType;
        this.locked = locked;
        this.outcomeName = outcomeName;
        this.portableWell = portableWell;
        this.portableProject = portableProject;
        this.potential = potential;
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

    static PortableOutcome createFrom(Outcome outcome) {
        return new PortableOutcome(outcome.getId(), PortableVehicle.fromVehicle(outcome.getVehiclecaminhocrítico()), outcome.getIndexInTaskType(), outcome.isLocked(), outcome.getOutcomeName(), PortableWell.fromWell(outcome.getWell()), PortableProject.fromProject(outcome.getProject()), outcome.getPotential(), outcome.getOutcomeTime(), outcome.getDueTime(), outcome.getLeadTime(), outcome.getReadyTimeOriginal(), outcome.getReadyTime(),
        outcome.getReadyTimePredecessora(), outcome.getListaPredecessoras().stream().map(offshoreTask -> PortableTimeWindowedOffshoreTask.createFromTimeWindowdOffshoreTask(
                        offshoreTask)).collect(Collectors.toList()),
                PortableTimeWindowedOffshoreTask.createFromTimeWindowdOffshoreTask(outcome.getCriticalPathLastTask()),
                outcome.getOutcomeListPredecessor().stream()
                        .map(outcomePredecessor -> PortableOutcome.createFrom(outcomePredecessor))
                        .collect(Collectors.toList()),
                outcome.getOutcomeListSucessor().stream()
                        .map(outcomeSuccessor -> PortableOutcome.createFrom(outcomeSuccessor))
                        .collect(Collectors.toList()),
                outcome.getGraphLevel(), PortableOutcome.createFrom(outcome.getCriticalPathLastOutcome()),
                outcome.getOutcomeTimeOutcomePredecessor(), outcome.getOutcomeTimeComposite());


    }

    public long getId() {
        return id;
    }

    public PortableVehicle getPortableVehiclecaminhocrítico() {
        return portableVehiclecaminhocrítico;
    }

    public String getIndexInTaskType() {
        return indexInTaskType;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getOutcomeName() {
        return outcomeName;
    }

    public PortableWell getPortableWell() {
        return portableWell;
    }

    public PortableProject getPortableProject() {
        return portableProject;
    }

    public Long getPotential() {
        return potential;
    }

    public Long getOutcomeTime() {
        return outcomeTime;
    }

    public long getDueTime() {
        return dueTime;
    }

    public long getLeadTime() {
        return leadTime;
    }

    public long getReadyTimeOriginal() {
        return readyTimeOriginal;
    }

    public Long getReadyTime() {
        return readyTime;
    }

    public long getReadyTimePredecessora() {
        return readyTimePredecessora;
    }

    public List<PortableTimeWindowedOffshoreTask> getListaPredecessoras() {
        return listaPredecessoras;
    }

    public PortableTimeWindowedOffshoreTask getCriticalPathLastTask() {
        return criticalPathLastTask;
    }

    public List<PortableOutcome> getOutcomeListPredecessor() {
        return outcomeListPredecessor;
    }

    public List<PortableOutcome> getOutcomeListSucessor() {
        return outcomeListSucessor;
    }

    public int getGraphLevel() {
        return graphLevel;
    }

    public PortableOutcome getCriticalPathLastOutcome() {
        return criticalPathLastOutcome;
    }

    public Long getOutcomeTimeOutcomePredecessor() {
        return outcomeTimeOutcomePredecessor;
    }

    public Long getOutcomeTimeComposite() {
        return outcomeTimeComposite;
    }
}