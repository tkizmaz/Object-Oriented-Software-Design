package iteration3;

public class QuessBot extends User {

    QuessingLabeling quessBotLabeling = new QuessingLabeling();
    @Override
    public void makeAssignment(Dataset currentDataset) {
        // TODO Auto-generated method stub
        quessBotLabeling.setUser(this);
        quessBotLabeling.setLabels(currentDataset.getLabels());
        quessBotLabeling.setInstances(currentDataset.getInstances());
        quessBotLabeling.setAssignedLabels(currentDataset);
    }
}
