package iteration3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import java.time.LocalDateTime;

// This class was created to use polymorphism in the next stages.
public class LabellingMechanism{

    private User currentUser;
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

    }
    
    // getter for userID
    public User getUser(){
        return this.currentUser;
    }


}