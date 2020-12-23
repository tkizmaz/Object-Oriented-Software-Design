package iteration2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;

public class RandomLabellingMechanism extends LabellingMechanism{
    private User currentUser;
    private int arraySize;
    private List<Instance> instances = new ArrayList<Instance>();
    private List<Label> labels = new ArrayList<Label>();

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
            Label classLabels[] = new Label[randomizedLabelCount];
            // it fill the inside of the array with random labels
            if(randomizedLabelCount>0){
                double chance=this.currentUser.getConsistencyCheckProbability()*100;
                for(int p=0;p < randomizedLabelCount;p++){
                    Label x = this.labels.get(rand.nextInt(this.labels.size()));
                    for(Label k : classLabels){
                        while(k == x){
                            x= this.labels.get(rand.nextInt(this.labels.size()));
                        }
                    }

                    classLabels[p] = x;
                }

                AssignedLabel newAssignment = new AssignedLabel();
                if(this.currentUser.getAssignments().size()>0&&rand.nextInt(100)<=(int)chance){
                    System.out.println("CHANCE OCCURED on" + this.currentUser.getUserID());
                    long oldAssignmentCount =this.currentUser.getAssignments().size();
                    long whichAssignment = (long)rand.nextInt((int)oldAssignmentCount);
                    newAssignment.setClassLabelID(this.currentUser.getAssignments().get((int)whichAssignment).getClassLabelID());
                    newAssignment.setInstanceID(this.currentUser.getAssignments().get((int)whichAssignment).getInstanceID());
                    newAssignment.setTime(this.currentUser.getAssignments().get((int)whichAssignment).getLocalTime());
                    newAssignment.setUser(this.currentUser);
                    currentDataset.setAssignedLabels(newAssignment);
                    try{
                        Thread.sleep(500);
                    }
                    catch(InterruptedException exception){
                        System.out.println("bir problem var");
              
                        }

                    }
                    
                else{
                    newAssignment.setClassLabelID(classLabels);
                    newAssignment.setUser(this.currentUser);
                    newAssignment.setTime(LocalDateTime.now());
                    newAssignment.setInstanceID(instances.get(i));
                    assigneds.add(newAssignment); 
                    currentDataset.setAssignedLabels(newAssignment);
                    try{
                        Thread.sleep(500);
                    }
                    catch(InterruptedException exception){
                        System.out.println("bir problem var");
              
                        }
                    }
                    
                }
                
                
                }

                

            }
    
    // getter for userID
    public User getUser(){
        return this.currentUser;
    }


}