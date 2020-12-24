package iteration2;

import java.util.ArrayList;
import java.util.List;

//imports that we use for list and arrays
public class User {
    
    //identify the variables on the class
    private long userID;
    private String username;
    private String userType;
    private RandomLabellingMechanism randomLabeling = new RandomLabellingMechanism();
    private List<AssignedLabel> assigneds = new ArrayList<AssignedLabel>();
    private double ConsistencyCheckProbability = 0.1;
    private int labelCount;
    private int uniqueLabelCount;
    
    public void setAssigneeds(AssignedLabel assigned){
        assigneds.add(assigned);
    }

    public void incrementCount(){
        labelCount++;
    }
    
    public void uniqueIncrementCount(){
        uniqueLabelCount++;
    }

    //get and set functions that set user information
    public long getUserID() {
        return userID;
    }
  
    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }
  
    public void setUsername(String username) {
        this.username = username;
    }

    public String getuserType() {
        return userType;
    }
  
    public void setUserType(String userType) {
        this.userType = userType;
    }

    // make assignment to create lists 
    public void makeAssignment(Dataset currentDataset){
        randomLabeling.setUser(this);
        randomLabeling.setLabels(currentDataset.getLabels());
        randomLabeling.setInstances(currentDataset.getInstances());
        randomLabeling.setAssignedLabels(currentDataset);
    }
    //assignedlabels should be in a list to store them
    public List<AssignedLabel> getAssignments(){
        return this.assigneds;
    }

    public double getConsistencyCheckProbability(){
        return this.ConsistencyCheckProbability;
    }
    public int getLabelCount(){
        return this.labelCount;
    }

    public int getUniqueLabelCount(){
        return this.uniqueLabelCount;
    }

    
}