package iteration2;

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
    private int nAssignedDatasets;
    private List<String> datasetComplPerList = new ArrayList<String>();
    private int nInstanceLabelled=0;
    private int nUniqueInstancesLabelled=0;
    private long concistencyPercentage=0;
    private List<Long> timeSpent= new ArrayList<Long>();
    private float avgTimeSpent=0;
    private float stdTimeSpent=0;
 
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public int getNAssignedDatasets() {
        return nAssignedDatasets;
    }


    public void setNAssignedDatasets(int nAssignedDatasets) {
        this.nAssignedDatasets = nAssignedDatasets;
    }

 
    public List<String> getDatasetComplPerList() {
        return datasetComplPerList;
    }


    public void setDatasetComplPerList(List<String> datasetComplPerList) { //Taha'dan
        this.datasetComplPerList = datasetComplPerList;
    }


    public int getNInstanceLabelled() {
        
        return nInstanceLabelled;
    }

 
    public void setNInstanceLabelled(int n) {
        this.nInstanceLabelled += n;
        //System.out.println("User: "+currentUser.getUserID()+"   # labelled Instance: "+this.nInstanceLabelled);
    }


    public int getNUniqueInstancesLabelled() {
        return nUniqueInstancesLabelled;
    }

  
    public void setNUniqueInstancesLabelled(int n) {
        this.nUniqueInstancesLabelled += n;
        //System.out.println("User: "+currentUser.getUserID()+"   # unique labelled Instance: "+this.nUniqueInstancesLabelled);
    }

 
    public long getConcistencyPercentage() {
        return concistencyPercentage;
    }


    public void setConcistencyPercentage(long concistencyPercentage) {
        this.concistencyPercentage = concistencyPercentage;
    }

    public float getAvgTimeSpent() {

        return avgTimeSpent;
    }

    public void setAvgTimeSpent() {
        float sum=0;
        float tho=1000;
        for(int i=0; i<this.timeSpent.size(); i++){
           // System.out.println("User: "+currentUser.getUserID()+" time spent:"+timeSpent.get(i)+" sum: "+sum);
            sum += timeSpent.get(i);
        }      
        this.avgTimeSpent = sum/(nInstanceLabelled*tho);
        //System.out.println("User: "+currentUser.getUserID()+" Avg Time Spent: "+this.avgTimeSpent);
    }

    public float getStdTimeSpent() {
        return stdTimeSpent;
    }

    public void setStdTimeSpent() {
       float standardDeviation=0;
        for(long num: timeSpent) {
            standardDeviation += Math.pow(num - avgTimeSpent,2);
            
        }
        this.stdTimeSpent =(float) Math.sqrt(standardDeviation/timeSpent.size());
        //System.out.println("User: "+currentUser.getUserID()+" Std : "+ this.stdTimeSpent);
    }

    public void extendTimeSpent(long passedTime){
        this.timeSpent.add(passedTime);
        setAvgTimeSpent();
        setStdTimeSpent();
    }

    public List<Long> getTimeSpent(){
        return timeSpent;
    }

}