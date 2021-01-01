package iteration3;

import java.time.LocalDateTime;

class AssignedLabel {

    private Instance instance;
    private Label classLabelID;
    private User currentUser;
    private LocalDateTime time;

// set instance ID
    public void setInstance(Instance instanceID){
        this.instance = instanceID;
    }
// set  class label ID
    public void setClassLabel(Label classLabelID){
        this.classLabelID = classLabelID;
    }

    public void setUser(User currentUser){
        this.currentUser = currentUser;
    }
  
// set current time
    public void setTime(LocalDateTime time){
        this.time=time;
    }
// get instance ID from random labeling mechanism
    public Instance getInstance(){
        return this.instance;
    }
//  get class label ID from random labeling mechanism
    public Label getClassLabelID(){
        return this.classLabelID;
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