package iteration2;

import java.util.ArrayList;
import java.util.List;

public class Dataset {
    private completenesssPercentage;
    private long numberofUsers;
    private long datasetID;
    private String datasetName;
    private long maximumLabels;
    private List<User> userList = new ArrayList<User>(); //list attribute for users 
    private List<Label> labels=new ArrayList<Label>(); //list attribute for lables
    private List <Instance> instances = new ArrayList<Instance>(); //list attribute for Instances

    public void setDatasetID(long dsID){ //set method for ID of the dataset
        this.datasetID = dsID;
    }

    public void setCompletenessPercentage(AssignedLabel eachAssignedLabel){
        
    }

    public void setNumberofUsers(long nofusers){ //set method for ID of the dataset
        this.numberofUsers=nofusers;
    }

    public void setDatasetName(String dsName){ //set method for name of the dataset
        this.datasetName = dsName;
    }

    public void setMaximumLabels(long maxLabels){ //set method for number of maximum labels
        this.maximumLabels = maxLabels;
    }

    public long getDatasetID(){ //get method the dataset
        return this.datasetID;
    }

    public String getDatasetName(){ //get method for the name of the dataset
        return this.datasetName;
    }

    public long getMaximumLabels(){ //get method for the maximum number of labels
        return this.maximumLabels;
    }

    public void setLabels(List<Label> label){ //set method for list of labels
        this.labels = label;
    }

    public List<Label> getLabels(){ //get method for the list of labels
        return this.labels;
    }

    public void setInstances(List<Instance> instance){ //set method for the list of Instances
        this.instances = instance;
    }

    public List<Instance> getInstances(){ //get method for the list of Instances
        return this.instances;
    }

    public void setUsers(List<User> users){ //set method for the list of Users
        this.userList = users;
    }

    public List<User> getUsers(){ //get method for list of users 
        return this.userList;
    }

    public long getNumberofUsers(){
        return this.numberofUsers;
    }


}
