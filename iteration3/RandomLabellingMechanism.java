package iteration3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import java.time.LocalDateTime;

public class RandomLabellingMechanism extends LabellingMechanism{
    private User currentUser;
    private List<Instance> instances = new ArrayList<Instance>();
    private List<Label> labels = new ArrayList<Label>();
    private UserPerformance userPerformance=new UserPerformance();
    private InstancePerformance instancePerformance = new InstancePerformance();
    Random rand = new Random();

    // to set userID
    public void setUser(User currentUser){
        this.currentUser = currentUser;
    }

    // to set instances list
    public void setInstances(List<Instance> instances){
        this.instances = instances;
    }

    // to set labels list
    public void setLabels(List<Label> labels){
        this.labels = labels;
    }

    // to set assignedLabels
    public void setAssignedLabels(Dataset currentDataset){

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
            if(this.getUser().getAssignments().size()>0 && this.currentUser.getConsistencyCheckProbability()*100 >= randomNumber){
                System.out.println("Chance occured!");
                randomToInstanceChoose = rand.nextInt(this.getUser().getAssignments().size());
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
            System.out.println("In else!");
            randomToInstanceChoose = rand.nextInt(currentDataset.getInstances().size());
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
    
    // getter for userID
    public User getUser(){
        return this.currentUser;
    }


}