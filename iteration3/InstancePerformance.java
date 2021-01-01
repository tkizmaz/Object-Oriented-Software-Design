package iteration3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

/*
B- Instance Performance Metrics
1- Total number of label assignments (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is 10)
2- Number of unique label assignments (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is 2)
3- Number of unique users (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is 3)
4- Most frequent class label and percentage (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is p and percentage is 60%)
5- List class labels and percentages (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is (p, 60%) and (n, 40%))
6- Entropy (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is -6/10*LOG(6/10;2)-4/10*LOG(4/10;2)=0,971). Use the number of unique labels in this particular instance as the log base. 
*/



public class InstancePerformance {

    private Dataset currentDataset; 
    int[] frequency= {0,0,0}; // Index 0: Positive, Index 1: Negative, Index 2: Notr


    public Dataset getCurrentDataset() {
        return currentDataset;
    }

    public void setCurrentDataset(Dataset currentDataset) {
        this.currentDataset = currentDataset;
    }
    
    public void setFrequency(){
        for (AssignedLabel al : currentDataset.getAssignedLabels()){
            if (al.getClassLabelID().getLabelID()==(long) 1){
                frequency[0]+=1;
            }
            if (al.getClassLabelID().getLabelID()==(long) 2){
                frequency[1]+=1;
            }
            if (al.getClassLabelID().getLabelID()==(long) 3){
                frequency[2]+=1;
            }
        }
    }

    //1- Total number of label assignments (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is 10)
    public int getNLabelAssignments() {
        return currentDataset.getAssignedLabels().size();
    }

    //2- Number of unique label assignments (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is 2)
    public int getNUniqueLabelAssignments() {
        long[] assignedL= new long[(int)currentDataset.getAssignedLabels().size()];
        int nUnIns=0;
        for (AssignedLabel al : currentDataset.getAssignedLabels()){
            if (!LongStream.of(assignedL).anyMatch(x ->x == al.getInstance().getInstanceID())){      //???
                nUnIns++;
            }
        }
        return nUnIns;
    }

    // 3- Number of unique users (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is 3)
    public int getNUniqueUsers() {
        return currentDataset.getUsers().size(); // we store only unique users
    }

    // 4- Most frequent class label and percentage (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is p and percentage is 60%)
    public String getMostFrequentClassLabel() {
        return "mostFrequentClassLabel";
    }


    //5- List class labels and percentages (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is (p, 60%) and (n, 40%))
    public String getClassLabelsPercentages(){
        String rValue = ("Positive: "+frequency[0]/3*100+"%"+ ", Negative: "+frequency[1]/3*100+"%"+ ", Notr: "+frequency[2]/3*100+"%");
        return rValue;
    }

    //6- Entropy (e.g. for labeling assignments of user 1, 2 and 3 -> [(u1,p),(u2,p),(u3,n),(u2,n),(u2,p),(u1,p),(u1,p),(u3,n),(u3,p),(u1,n)] it is -6/10*LOG(6/10;2)-4/10*LOG(4/10;2)=0,971). Use the number of unique labels in this particular instance as the log base. 

    public double getEntropy() {
        double entropy=0;
        for (int k : frequency){
            double ratio=k/getNUniqueLabelAssignments();
            entropy-=ratio*log2(ratio);
        }
        return entropy;
    }
    
    public static double log2(double N) 
    { 
        double result = (Math.log(N) / Math.log(2)); 
        return result; 
    } 
}