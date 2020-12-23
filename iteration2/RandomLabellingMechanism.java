package iteration2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;

public class RandomLabellingMechanism extends LabellingMechanism{
    private JSONHandler jsonHandler= new JSONHandler();
    private User currentUser;
    private int arraySize;
    private List<Instance> instances = new ArrayList<Instance>();
    private List<Label> labels = new ArrayList<Label>();
    private List<AssignedLabel> assignedLabels = new ArrayList<AssignedLabel>();

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
        // to create an assignedLabel list object called assigneds
        List<AssignedLabel> assigneds = new ArrayList<AssignedLabel>();
        
        //to go through samples one by one and tag them
        for(int i=0; i<this.instances.size(); i++){

            // if maxLabels equals to one, sentiment labelling is performed.
            if(currentDataset.getMaximumLabels()==1){
                this.arraySize=1;
            }
            // if maxLabel not equals to one, classification will be performed.
            else{
                this.arraySize = (int)currentDataset.getMaximumLabels();
            }
            // to clearify array size2

            int randomizedLabelCount = rand.nextInt(this.arraySize+1);
            long classLabels[] = new long[randomizedLabelCount];
            // it fill the inside of the array with random labels
            if(randomizedLabelCount>0){
                double chance=this.currentUser.getConsistencyCheckProbability()*100;

                for(int p=0;p < randomizedLabelCount;p++){
                    int x= rand.nextInt(this.labels.size())+1;
                    for(long k : classLabels){
                        while(k == x){
                            x= rand.nextInt(this.labels.size());
                        }
                    }

                    classLabels[p]=x;
                }

                AssignedLabel newAssignment = new AssignedLabel();
                if(this.currentUser.getAssignments().size()>0&&rand.nextInt(100)<=(int)chance){
                    System.out.println("CHANCE OCCURED on" + this.currentUser.getUserID());
                    long oldAssignmentCount =this.currentUser.getAssignments().size();
                    long whichAssignment = (long)rand.nextInt((int)oldAssignmentCount);
                    this.currentUser.getAssignments().get((int)whichAssignment).setClassLabelID(classLabels);
                    this.currentUser.getAssignments().get((int)whichAssignment).setUserID(this.currentUser.getUserID());
                    this.currentUser.getAssignments().get((int)whichAssignment).setTime(LocalDateTime.now());
                    this.currentUser.getAssignments().get((int)whichAssignment).setInstanceID(instances.get(i).getInstanceID());
                    
                    }
                    
                else{
                    newAssignment.setClassLabelID(classLabels);
                    newAssignment.setUserID(this.currentUser.getUserID());
                    newAssignment.setTime(LocalDateTime.now());
                    newAssignment.setInstanceID(instances.get(i).getInstanceID());
                    assigneds.add(newAssignment); 
                    currentUser.getAssignments().add(newAssignment);
                    this.assignedLabels = assigneds;
                    
                }
                
                
                }

                

            }

            
            
    }
    
    // getter for userID
    public User getUser(){
        return this.currentUser;
    }

    // getter for assignedLabel
    public List<AssignedLabel> getAssignedLabels(){
        return this.assignedLabels;
    }

}