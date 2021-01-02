package iteration3;

import java.util.stream.LongStream;

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

 
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public Dataset getCurrentDataset() {
        return currentDataset;
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
        return (""+ currentDataset.getAssignedLabels().size()/currentDataset.getInstances().size()*100+"%");  
    }

    // 3- Total number of instances labeled 
    public int getNInstanceLabelled() {
        return currentUser.getAssignments().size();
    }

    // 4- Total number of unique instances labeled 
    public int getNUniqueInstancesLabelled() {
        long[] assignedL= new long[(int)currentUser.getAssignments().size()];
        int nUnIns=0;
        int index=0;
        for (AssignedLabel al : currentUser.getAssignments()){
            assignedL[index]=al.getInstance().getInstanceID();
            index ++;
            if (!LongStream.of(assignedL).anyMatch(x ->x == al.getInstance().getInstanceID())){      //???
                nUnIns++;
            }
        }
        return nUnIns;
    }
 
    // 5- Consistency percentage (e.g. %60 of the recurrent instances are labeled with the same class)
    public double getConcistencyPercentage() {
        return this.currentUser.getConsistencyCheckProbability()*100;
    }

}