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

    public void setInstanceID(long instanceID){
        this.instanceID = instanceID;
    }

    public void setClassLabelID(long[] classLabelID){
        this.classLabelID = classLabelID;
    }
  

    public void setUserID(long userID){
        this.userID = userID;
    }

    public void setTime(LocalDateTime time){
        this.time=time;
    }

    public long getInstanceID(){
        return this.instanceID;
    }

    public long[] getClassLabelID(){
        return this.classLabelID;
    }

    public long getUserID(){
        return this.userID;
    }

    public LocalDateTime getLocalTime(){
        return this.time;
    }


}