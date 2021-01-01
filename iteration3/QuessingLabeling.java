package iteration3;
import java.time.LocalDateTime;
public class QuessingLabeling extends LabellingMechanism {
    
    @Override
    public void setAssignedLabels(Dataset currentDataset) {
  
        AssignedLabel assignedLabel = new AssignedLabel();
        boolean isSameInstance = false;

        int randomToInstanceChoose = rand.nextInt(currentDataset.getInstances().size());

        int randomToLabel = rand.nextInt(currentDataset.getLabels().size());

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
                System.out.println("Chance occured!");
                assignedLabel.setInstance(currentDataset.getInstances().get(randomToInstanceChoose));
                assignedLabel.setClassLabel(currentDataset.getLabels().get(randomToLabel));
                assignedLabel.setTime(LocalDateTime.now());
                assignedLabel.setUser(this.getUser());
                this.getUser().setAssigneeds(assignedLabel);
                currentDataset.setAssignedLabels(assignedLabel);
                System.out.println("User with id of: "+this.getUser().getUserID()+" created an assignment :"+ assignedLabel);
            }
            else{
                return;
            }
        }
        

        else{
            assignedLabel.setInstance(currentDataset.getInstances().get(randomToInstanceChoose));
            assignedLabel.setClassLabel(currentDataset.getLabels().get(randomToLabel));
            assignedLabel.setTime(LocalDateTime.now());
            assignedLabel.setUser(this.getUser());
            this.getUser().setAssigneeds(assignedLabel);
            currentDataset.setAssignedLabels(assignedLabel);
            System.out.println("User with id of: "+this.getUser().getUserID()+" created an assignment :"+ assignedLabel);
        }
        
        System.out.println("End of labeling");

        try{
            Thread.sleep(500);
        }
        catch(InterruptedException exception){
            System.out.println("bir problem var");
  
        }
    }

}
