package iteration1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;

public class RandomLabellingMechanism extends LabellingMechanism{
    
    private long userID;
    private int arraySize;
    private List<Instance> instances = new ArrayList<Instance>();
    private List<Label> labels = new ArrayList<Label>();
    private List<AssignedLabel> assignedLabels = new ArrayList<AssignedLabel>();

    Random rand = new Random();

    // to set userID
    public void setUserID(long userID){
        this.userID = userID;
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
    public void setAssignedLabels(long maxLabels){
        // to create an assignedLabel list object called assigneds
        List<AssignedLabel> assigneds = new ArrayList<AssignedLabel>();

        //to go through samples one by one and tag them
        for(int i=0; i<this.instances.size(); i++){
            // if maxLabels equals to one, sentiment labelling is performed.
            if(maxLabels==1){
                this.arraySize=1;
            }
            // if maxLabel not equals to one, classification will be performed.
            else{
                this.arraySize = rand.nextInt((int)maxLabels)+1;
            }
            // to clearify array size
            long classLabels[] = new long[this.arraySize];

            // it fill the inside of the array with random labels
            for(int p=0;p < this.arraySize;p++){
                int x= rand.nextInt(this.labels.size());
                for(long k : classLabels){
                    while(k == x){
                        x= rand.nextInt(this.labels.size());
                    }
                }
                classLabels[p]=x;
            }

            // to assign the random labels to instances
            AssignedLabel newAssignment = new AssignedLabel();
            newAssignment.setClassLabelID(classLabels);
            newAssignment.setUserID(this.userID);
            newAssignment.setTime(LocalDateTime.now());
            newAssignment.setInstanceID(instances.get(i).getInstanceID());
            assigneds.add(newAssignment); 
        }
        this.assignedLabels = assigneds;
    } 
    
    // getter for userID
    public long getUserID(){
        return this.userID;
    }

    // getter for assignedLabel
    public List<AssignedLabel> getAssignedLabels(){
        return this.assignedLabels;
    }

}