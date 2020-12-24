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
        UserPerformance userPerformance=new UserPerformance();
        userPerformance.setCurrentUser(this.currentUser); // to currentUser in UserPerformance
        //DatasetPerformance performance = new DatasetPerformance();
        // to create an assignedLabel list object called assigneds
        List<AssignedLabel> assigneds = new ArrayList<AssignedLabel>();
        JSONHandler readJS = new JSONHandler();
        //to go through samples one by one and tag them
        for(int i=0; i<this.instances.size(); i++){
            long startTime = System.currentTimeMillis();

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
                if(this.currentUser.getAssignments().size()>0 && (int)this.currentUser.getConsistencyCheckProbability()*100 >= rand.nextInt(100)){
                    System.out.println("CHANCE OCCURED on" + this.currentUser.getUserID());
                    long oldAssignmentCount =this.currentUser.getAssignments().size();
                    long whichAssignment = (long)rand.nextInt((int)oldAssignmentCount);
                    newAssignment.setClassLabel(this.currentUser.getAssignments().get((int)whichAssignment).getClassLabelID());
                    newAssignment.setInstance(this.currentUser.getAssignments().get((int)whichAssignment).getInstance());
                    newAssignment.setTime(this.currentUser.getAssignments().get((int)whichAssignment).getLocalTime());
                    newAssignment.setUser(this.currentUser);
                    currentDataset.setAssignedLabels(newAssignment);
                    //readJS.writeUserMetrics(newAssignment,currentDataset);
                    //performance.setCurrentDataset(currentDataset);
                    //performance.setCompletenessPercentage();
                    this.currentUser.setAssigneeds(newAssignment);
                    //performance.getUserAssigned();
                    userPerformance.setCurrentUser(this.currentUser);
                    try{
                        Thread.sleep(500);
                    }
                    catch(InterruptedException exception){
                        System.out.println("bir problem var");
              
                        }

                    }
                    
                else{
                    newAssignment.setClassLabel(classLabels);
                    newAssignment.setUser(this.currentUser);
                    newAssignment.setTime(LocalDateTime.now());
                    newAssignment.setInstance(instances.get(i));
                    assigneds.add(newAssignment); 
                    currentDataset.setAssignedLabels(newAssignment);
                    //readJS.writeUserMetrics(newAssignment,currentDataset);
                    //performance.setCurrentDataset(currentDataset);
                    //performance.setCompletenessPercentage();
                    this.currentUser.setAssigneeds(newAssignment);
                    this.currentUser.incrementCount();
                    //performance.getUserAssigned();
                    userPerformance.setNUniqueInstancesLabelled(1);
                    try{
                        Thread.sleep(500);
                    }
                    catch(InterruptedException exception){
                        System.out.println("bir problem var");
              
                    }
                }
                //to calculate and store the number of assigned labels by user                    
                userPerformance.setNInstanceLabelled(1);       
            }
            long endTime = System.currentTimeMillis();
            userPerformance.extendTimeSpent(endTime-startTime);          
        }        
    }
    
    // getter for userID
    public User getUser(){
        return this.currentUser;
    }


}