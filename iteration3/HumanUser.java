package iteration3;

public class HumanUser extends User {

    HumanLabelingMechanism humanLabeling = new HumanLabelingMechanism();
    @Override
    public void makeAssignment(Dataset currentDataset) {
        humanLabeling.setUser(this);
        humanLabeling.setLabels(currentDataset.getLabels());
        humanLabeling.setInstances(currentDataset.getInstances());
        humanLabeling.setAssignedLabels(currentDataset);
    }
}
