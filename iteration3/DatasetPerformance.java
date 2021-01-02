package iteration3;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

/*
C- Dataset Performance Metrics
1- Completeness percentage (e.g. what percentage of the instances are labeled)
2- Class distribution based on final instance labels (e.g. 70% positive, 30% negative )
3- List number of unique instances for each class label ()
4- Number of users assigned to this dataset
5- List of users assigned and their completeness percentage (e.g. (user 1, 99%), (user2, %80), and (user3,30%), meaning user 3 has labeled 30% of the unique instances in this dataset )
6- List of users assigned and their consistency percentage (e.g. (user 1, 99%), (user2, %89), and (user3,70%), please see A.5 for consistency calculation)
*/

public class DatasetPerformance {

    private Dataset currentDataset;
    private List<AssignedLabel> labeledInstanceIDs = new ArrayList<AssignedLabel>();
    

    public void setCurrentDataset(Dataset currentDataset){
        this.currentDataset = currentDataset;
    }

    public Dataset getCurrentDataset() {
        return currentDataset;
    }

    
    // 1- Completeness percentage (e.g. what percentage of the instances are labeled)
    public float getCompletenessPercentage(){
        List<Long> labeledInstanceIDs = new ArrayList<Long>();
        for(int i=0;i<this.currentDataset.getAssignedLabels().size();i++){
            labeledInstanceIDs.add(this.currentDataset.getAssignedLabels().get(i).getInstance().getInstanceID());
        }
        float numofInstances=this.currentDataset.getInstances().size();
        List<Long> withoutDupes = labeledInstanceIDs.stream()
                                      .distinct()
                                      .collect(Collectors.toList());
        return (float)withoutDupes.size() / numofInstances * 100;
    }

    //2- Class distribution based on final instance labels (e.g. 70% positive, 30% negative )
    public String getDistributionOfInstanceLabels(){
        int numberOfPositives=0;
        int size=currentDataset.getAssignedLabels().size();
        if (currentDataset.getMaximumLabels()==3){
            for(AssignedLabel al : currentDataset.getAssignedLabels()){
                if(al.getClassLabelID().getLabelID()==0){ // 0 means positive ?????
                    numberOfPositives++;
                }
            }
        }
        return (numberOfPositives/size*100 +"% positive "+ (size-numberOfPositives)/size*100 +"% negative");
    }

    //3- List number of unique instances for each class label ()
    public int getNUniqueInstancesLabelled() {
        for(int i=0;i<this.currentDataset.getAssignedLabels().size();i++){
            labeledInstanceIDs.add(this.currentDataset.getAssignedLabels().get(i));
        }
        List<AssignedLabel> withoutDupes = this.labeledInstanceIDs.stream()
                                      .distinct()
                                      .collect(Collectors.toList());
        return withoutDupes.size();
    }
 

    // 4- Number of users assigned to this dataset
    public int getNumberofUsers(){
        return currentDataset.getUsers().size();
    }

    // 5- List of users assigned and their completeness percentage (e.g. (user 1, 99%), (user2, %80), and (user3,30%), meaning user 3 has labeled 30% of the unique instances in this dataset )
    public String getUserAssignedCompleteness(){
        String rValue="";
        for(int i=0;i<currentDataset.getUsers().size();i++){
            rValue+="(UserID: "+currentDataset.getUsers().get(i).getUserID()+" :"+ ((float)currentDataset.getUsers().get(i).getLabelCount() / (float)currentDataset.getInstances().size() *100+") ");
        } 
        return rValue;         
    }

    // 6- List of users assigned and their consistency percentage (e.g. (user 1, 99%), (user2, %89), and (user3,70%), please see A.5 for consistency calculation)
    public String getUserAssignedConsistecy() {
        String rValue="";
        for(int i=0;i<currentDataset.getUsers().size();i++){
            rValue+=("(UserID: "+currentDataset.getUsers().get(i).getUserID()+" :"+ currentDataset.getUsers().get(i).getConsistencyCheckProbability()*100+") ");
        }  
        return rValue;      
    }

}
