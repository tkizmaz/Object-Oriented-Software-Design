package iteration1;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class AssignedLabel {

    private long instanceID;
    private long classLabelID[];
    private long userID;
    private LocalDateTime time;
    /*public LocalDateTime datetime = LocalDateTime.now();
    public DateTimeFormatter nice_version_date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    System.out.println(nice_version_date);*/

// set instance ID
    public void setInstanceID(long instanceID){
        this.instanceID = instanceID;
    }
// set  class label ID
    public void setClassLabelID(long[] classLabelID){
        this.classLabelID = classLabelID;
    }
  
// set user ID
    public void setUserID(long userID){
        this.userID = userID;
    }
// set current time
    public void setTime(LocalDateTime time){
        this.time=time;
    }
// get instance ID from random labeling mechanism
    public long getInstanceID(){
        return this.instanceID;
    }
//  get class label ID from random labeling mechanism
    public long[] getClassLabelID(){
        return this.classLabelID;
    }
//get user ID from random labeling mechanism
    public long getUserID(){
        return this.userID;
    }
// get current  time
    public LocalDateTime getLocalTime(){
        return this.time;
    }


}