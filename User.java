import java.util.ArrayList;
import java.util.List;

public class User {
    private long userID;
    private String username;
    private String userType;
    private RandomLabellingMechanism randomLabeling = new RandomLabellingMechanism();
    private List<AssignedLabel> assigneds = new ArrayList<AssignedLabel>();

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

    public void makeAssignment(List<Label> label,List<Instance> instance,long maxLabels){
        randomLabeling.setUserID(this.userID);
        randomLabeling.setLabels(label);
        randomLabeling.setInstances(instance);
        randomLabeling.setAssignedLabels(maxLabels);
        this.assigneds= randomLabeling.getAssignedLabels();
    }
    
    public List<AssignedLabel> getAssignments(){
        return this.assigneds;
    }

    
}