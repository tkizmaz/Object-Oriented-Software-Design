package iteration1;

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
        randomLabeling.setAssignedLabels(currentDataset.getMaximumLabels());
        this.assigneds= randomLabeling.getAssignedLabels();
    }
    //assignedlabels should be in a list to store them
    public List<AssignedLabel> getAssignments(){
        return this.assigneds;
    }

    
}