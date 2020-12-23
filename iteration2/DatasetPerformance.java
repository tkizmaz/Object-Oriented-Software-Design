package iteration2;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public class DatasetPerformance {
    private Dataset currentDataset;
    private List<List<AssignedLabel>> assignedLabels=new ArrayList<>();
    private float completenessPercentage;

    public void setCurrentDataset(Dataset currentDataset){
        this.currentDataset=currentDataset;
    }

    public void setAssignedLabels(List<List<AssignedLabel>> assignedLabels){
        this.assignedLabels=assignedLabels;
    }

    public Dataset getCurrentDataset(){
        return this.currentDataset;
    }

    public List<List<AssignedLabel>> setAssignedLabels(){
        return this.assignedLabels;
    }

    public float C1calculationsForDataset(){
        //C1
        int labeledInstancesCount=0;
        List<Integer> labeledInstances = new ArrayList<>();
        System.out.println("For database id:" + this.currentDataset.getDatasetID());
        for(int i=0;i<assignedLabels.size();i++){
            for(int z=0;z<assignedLabels.get(i).size();z++){
                labeledInstancesCount = (int)assignedLabels.get(i).get(z).getInstanceID();
                labeledInstances.add(labeledInstancesCount);
            }
        }
        List<Integer> labeledUniqueInstances = labeledInstances.stream().distinct().collect(Collectors.toList());
        this.completenessPercentage = ((float)labeledUniqueInstances.size()/(float)this.currentDataset.getInstances().size()) *100 ;
        return this.completenessPercentage;
    }

}
