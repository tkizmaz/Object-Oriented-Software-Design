package iteration3;

import java.util.ArrayList;
import java.util.List;

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
    private int nLabelAssignments;
    private int nUniqueLabelAssignments;
    private int nUniqueUsers;
    private String mostFrequentClassLabel;
    private List<Integer> classLabels = new ArrayList<Integer>();
    private float entropy;

    public int getNLabelAssignments() {
        return nLabelAssignments;
    }

    public void setNLabelAssignments(int n) {
        this.nLabelAssignments += n;
    }

    public int getNUniqueLabelAssignments() {
        return nUniqueLabelAssignments;
    }

    public void setNUniqueLabelAssignments(int nUniqueLabelAssignments) {
        this.nUniqueLabelAssignments += nUniqueLabelAssignments;
 
    }

    public int getNUniqueUsers() {
        return nUniqueUsers;
    }

    public void setNUniqueUsers(int n) {
        this.nUniqueUsers += n;
    }

    public String getMostFrequentClassLabel() {
        return mostFrequentClassLabel;
    }

    public void setMostFrequentClassLabel(String mostFrequentClassLabel) {
        this.mostFrequentClassLabel = mostFrequentClassLabel;
    }

    public List<Integer> getClassLabels() {
        return classLabels;
    }

    public void setClassLabels(List<Integer> classLabels) {
        this.classLabels = classLabels;
    }

    public float getEntropy() {
        return entropy;
    }

    public void setEntropy(float entropy) {
        this.entropy = entropy;
    }

    public Dataset getCurrentDataset() {
        return currentDataset;
    }

    public void setCurrentDataset(Dataset currentDataset) {
        this.currentDataset = currentDataset;
    }

}