import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; 
import java.util.Random;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
public class RandomLabellingMechanism extends LabellingMechanism{
    private long userID;
    private int arraySize;
    private List<Instance> instances = new ArrayList<Instance>();
    private List<Label> labels = new ArrayList<Label>();
    private List<AssignedLabel> assignedLabels = new ArrayList<AssignedLabel>();
    private List<Array> classLabelList = new ArrayList<Array>();

    Random rand = new Random();

    public void setUserID(long userID){
        this.userID = userID;
    }

    public long getUserID(){
        return this.userID;
    }

    public void setInstances(List<Instance> instances){
        this.instances = instances;
    }

    public void setLabels(List<Label> labels){
        this.labels = labels;
    }


    public List<AssignedLabel> getAssignedLabels(){
        return this.assignedLabels;
    }

    public void setAssignedLabels(long maxLabels){
        List<AssignedLabel> assigneds = new ArrayList<AssignedLabel>();


        for(int i=0; i<this.instances.size(); i++){
            if(maxLabels==1){
                this.arraySize=1;
            }
            else{
                this.arraySize = rand.nextInt((int)maxLabels)+1;
            }
            long classLabels[] = new long[this.arraySize];
            for(int p=0;p < this.arraySize;p++){
                classLabels[p] = rand.nextInt(this.labels.size())+1;
            }
            AssignedLabel newAssignment = new AssignedLabel();
            newAssignment.setClassLabelID(classLabels);
            newAssignment.setUserID(this.userID);
            newAssignment.setTime(LocalDateTime.now());
            newAssignment.setInstanceID(instances.get(i).getInstanceID());
            assigneds.add(newAssignment); 

        }

        this.assignedLabels = assigneds;
        


    }   


}