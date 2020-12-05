package iteration1;
public class  Instance {
    private long InstanceID;
    private String InstanceText;

    public void setInstanceID(long id){ // instance ids sets in dataset
        this.InstanceID=id;
    }
    public long getInstanceID(){ //takes instance ids from dataset
        return InstanceID; 
    }

    public void setInstanceText(String text){ // sets instance texts in dataset
        this.InstanceText=text;
    }
    
    public String getInstanceText(){ // gets instance text from dataset
        return InstanceText;
    }
}