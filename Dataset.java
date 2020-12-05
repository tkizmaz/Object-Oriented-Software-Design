import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

public class Dataset {
    
    private long datasetID;
    private String datasetName;
    private long maximumLabels;
    private List<User> userList = new ArrayList<User>();
    private List<Label> labels=new ArrayList<Label>();;
    private List <Instance> instances = new ArrayList<Instance>();

    public void setDatasetID(long dsID){
        this.datasetID = dsID;
    }

    public void setDatasetName(String dsName){
        this.datasetName = dsName;
    }

    public void setMaximumLabels(long maxLabels){
        this.maximumLabels = maxLabels;
    }

    public long getDatasetID(){
        return this.datasetID;
    }

    public String getDatasetName(){
        return this.datasetName;
    }

    public long getMaximumLabels(){
        return this.maximumLabels;
    }

    public void setLabels(List<Label> label){
        this.labels = label;
    }

    public List<Label> getLabels(){
        return this.labels;
    }

    public void setInstances(List<Instance> instance){
        this.instances = instance;
    }

    public List<Instance> getInstances(){
        return this.instances;
    }

    public void setUsers(List<User> users){
        this.userList = users;
    }

    public List<User> getUsers(){
        return this.userList;
    }


}
