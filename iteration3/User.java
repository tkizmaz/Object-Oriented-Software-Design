package iteration3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

//imports that we use for list and arrays
public class User {
    
    //identify the variables on the class
    private long userID;
    private String username;
    private String userType;
    private List<AssignedLabel> assigneds = new ArrayList<AssignedLabel>();
    private double ConsistencyCheckProbability = 0.1;
    private UserPerformance userPerformance=new UserPerformance();
 
    
    public void setAssigneeds(AssignedLabel assigned){
        assigneds.add(assigned);
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
        System.out.println("In mother class method");
    }

    //assignedlabels should be in a list to store them
    public List<AssignedLabel> getAssignments(){
        return this.assigneds;
    }
    

    public double getConsistencyCheckProbability(){
        return this.ConsistencyCheckProbability;
    }


    public String getUserType() {
        return userType;
    }

    public void setConsistencyCheckProbability(double ConsistencyCheckProbability) {
        this.ConsistencyCheckProbability = ConsistencyCheckProbability;
    }


    public UserPerformance getUserPerformance() {
        return userPerformance;
    }


    public void setUserPerformance(UserPerformance userPerformance) {
        this.userPerformance = userPerformance;
    }


}