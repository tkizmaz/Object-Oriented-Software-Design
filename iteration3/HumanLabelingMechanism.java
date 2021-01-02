package iteration3;
import java.time.LocalDateTime;
import java.util.Scanner;

public class HumanLabelingMechanism extends LabellingMechanism {

    private JSONHandler writeJS = new JSONHandler();
    private InstancePerformance iPerformance = new InstancePerformance();
    private DatasetPerformance dPerformance = new DatasetPerformance();
    private UserPerformance uPerformance = new UserPerformance();

    @Override
    public void setAssignedLabels(Dataset currentDataset) {

        iPerformance.setCurrentDataset(currentDataset);
        dPerformance.setCurrentDataset(currentDataset);
        uPerformance.setCurrentDataset(currentDataset);
        uPerformance.setCurrentUser(this.getUser());

        Scanner scanner = new Scanner(System.in);
        AssignedLabel assignedLabel = new AssignedLabel();
        boolean isSameInstance = false;

        int randomToInstanceChoose = rand.nextInt(currentDataset.getInstances().size());
        Instance instanceText = currentDataset.getInstances().get(randomToInstanceChoose);

        for(int i=0;i<this.getUser().getAssignments().size();i++){
            Instance labeledInstance = new Instance();
            labeledInstance = getUser().getAssignments().get(i).getInstance();
            if(currentDataset.getInstances().get(randomToInstanceChoose).equals(labeledInstance)){
                isSameInstance = true;
            }
        }

        
        if(isSameInstance){
            int randomNumber = rand.nextInt(100)+1;
            if(this.getUser().getAssignments().size()>0 && getUser().getConsistencyCheckProbability()*100 >= randomNumber){
                System.out.println("");
                for(int i=0;i<currentDataset.getLabels().size();i++){
                    System.out.println(currentDataset.getLabels().get(i).getLabelText() + " : "+ currentDataset.getLabels().get(i).getLabelID());
                }

                System.out.println("");
                System.out.println(instanceText.getInstanceText());
                int labelToChoose = scanner.nextInt();

                assignedLabel.setClassLabel(currentDataset.getLabels().get(labelToChoose-1));
                assignedLabel.setInstance(instanceText);
                assignedLabel.setTime(LocalDateTime.now());
                assignedLabel.setUser(getUser());
                currentDataset.setAssignedLabels(assignedLabel);
                this.getUser().setAssigneeds(assignedLabel);
                System.out.println("User with id of: "+this.getUser().getUserID()+" with user type "+this.getUser().getUserType() +" created an assignment :"+ assignedLabel);
            }
            else{
                return;
            }
        }

        else{
            System.out.println("");
            for(int i=0;i<currentDataset.getLabels().size();i++){
                System.out.println(currentDataset.getLabels().get(i).getLabelText() + " : "+ currentDataset.getLabels().get(i).getLabelID());
            }

            System.out.println("");
            System.out.println(instanceText.getInstanceText());
            int labelToChoose = scanner.nextInt();

            assignedLabel.setClassLabel(currentDataset.getLabels().get(labelToChoose-1));
            assignedLabel.setInstance(instanceText);
            assignedLabel.setTime(LocalDateTime.now());
            assignedLabel.setUser(getUser());
            currentDataset.setAssignedLabels(assignedLabel);
            this.getUser().setAssigneeds(assignedLabel);
            System.out.println("User with id of: "+this.getUser().getUserID()+" with user type "+this.getUser().getUserType() +" created an assignment :"+ assignedLabel);
        }

        iPerformance.setFrequency();
        writeJS.writeUserMetrics(uPerformance);
        writeJS.writeDatasetMetrics(dPerformance);
        writeJS.writeInstanceMetrics(iPerformance);

        

    }
}
