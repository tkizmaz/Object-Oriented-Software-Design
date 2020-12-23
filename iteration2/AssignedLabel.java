package iteration2;

import java.time.LocalDateTime;

class AssignedLabel {

    private Instance instance;
    private Label classLabelIDs[];
    private User currentUser;
    private LocalDateTime time;

// set instance ID
    public void setInstanceID(Instance instanceID){
        this.instance = instanceID;
    }
// set  class label ID
    public void setClassLabelID(Label[] classLabelID){
        this.classLabelIDs = classLabelID;
    }

    public void setUser(User currentUser){
        this.currentUser = currentUser;
    }
  
// set current time
    public void setTime(LocalDateTime time){
        this.time=time;
    }
// get instance ID from random labeling mechanism
    public Instance getInstanceID(){
        return this.instance;
    }
//  get class label ID from random labeling mechanism
    public Label[] getClassLabelID(){
        return this.classLabelIDs;
    }
//get user ID from random labeling mechanism
// get current  time
    public LocalDateTime getLocalTime(){
        return this.time;
    }

    public User getUser(){
        return this.currentUser;
    }


}