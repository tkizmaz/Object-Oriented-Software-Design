package iteration3;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.ArrayList;
import java.util.List;
/*
    A- User Performance Metrics and Reports
    1- Number of datasets assigned (e.g. 4)
    2- List of all datasets with their completeness percentage (e.g. dataset1 %100, dataset2 %90, dataset3 15%, dataset 4 0%)
    3- Total number of instances labeled 
    4- Total number of unique instances labeled 
    5- Consistency percentage (e.g. %60 of the recurrent instances are labeled with the same class)
    6- Average time spent in labeling an instance in seconds
    7- Std. dev. of  time spent in labeling an instance in seconds
    */

public class UserPerformance {

    private User currentUser;
    private Dataset currentDataset;
    private List<Long> labeledInstanceIDs = new ArrayList<Long>();
 
    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public Dataset getCurrentDataset() {
        return this.currentDataset;
    }

    public void setCurrentDataset(Dataset currentDataset) {
        this.currentDataset = currentDataset;
    }

    // 1- Number of datasets assigned (e.g. 4)
    public int getNAssignedDatasets() { // we assign users to only one dataset
         return 1;
    }

   // 2- List of all datasets with their completeness percentage (e.g. dataset1 %100, dataset2 %90, dataset3 15%, dataset 4 0%)
    public String getDatasetComplPerList() {
        return (""+ (this.currentDataset.getAssignedLabels().size()/this.currentDataset.getInstances().size())*100+"%");  
    }

    // 3- Total number of instances labeled 
    public int getNInstanceLabelled() {
        return this.currentUser.getAssignments().size();
    }

    // 4- Total number of unique instances labeled 
    public int getNUniqueInstancesLabelled() {
        for(int i=0;i<this.currentUser.getAssignments().size();i++){
            labeledInstanceIDs.add(this.currentUser.getAssignments().get(i).getClassLabelID().getLabelID());
        }
        List<Long> withoutDupes = this.labeledInstanceIDs.stream()
                                      .distinct()
                                      .collect(Collectors.toList());
        return withoutDupes.size();
    }
 
    // 5- Consistency percentage (e.g. %60 of the recurrent instances are labeled with the same class)
    public double getConcistencyPercentage() {
        return this.currentUser.getConsistencyCheckProbability()*100;
    }

}