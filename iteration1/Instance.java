public class  Instance {
    private long InstanceID;
    private String InstanceText;

    public void setInstanceID(long id){
        this.InstanceID=id;
    }
    public long getInstanceID(){
        return InstanceID;
    }

    public void setInstanceText(String text){
        this.InstanceText=text;
    }
    
    public String getInstanceText(){
        return InstanceText;
    }
}