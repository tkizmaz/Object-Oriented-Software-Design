package iteration2;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
public class DatasetPerformance {

    private Dataset currentDataset;
    private float completenessPercentage;
    private List<Long> labeledInstanceIDs = new ArrayList<Long>();

    public void setCurrentDataset(Dataset currentDataset){
        this.currentDataset = currentDataset;
    }

    public void setCompletenessPercentage(){
        for(int i=0;i<this.currentDataset.getAssignedLabels().size();i++){
            labeledInstanceIDs.add(this.currentDataset.getAssignedLabels().get(i).getInstance().getInstanceID());
        }
        float numofInstances=this.currentDataset.getInstances().size();
        List<Long> withoutDupes = this.labeledInstanceIDs.stream()
                                      .distinct()
                                      .collect(Collectors.toList());
        this.completenessPercentage = (float)withoutDupes.size() / numofInstances * 100;
    }


    public void getUserAssigned(){
        for(int i=0;i<currentDataset.getUsers().size();i++){

            System.out.println(currentDataset.getUsers().get(i).getUserID()+" :"+ ((float)currentDataset.getUsers().get(i).getLabelCount() / (float)currentDataset.getInstances().size() *100));

        }
        
    }

    public float getCompletenessPercentage(){
        return this.completenessPercentage;
    }

}
