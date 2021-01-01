package iteration3;

public class BotUser extends User {
    
    private RandomLabellingMechanism randomLabeling = new RandomLabellingMechanism();

    @Override
    public void makeAssignment(Dataset currentDataset) {
        randomLabeling.setUser(this);
        randomLabeling.setLabels(currentDataset.getLabels());
        randomLabeling.setInstances(currentDataset.getInstances());
        randomLabeling.setAssignedLabels(currentDataset);
    }

}
