package iteration3;

public class Label {
    
    //Label class is created
    private long LabelID;
    private String LabelText;
    //attributes are defined

    public void setLabelID(long id){
        this.LabelID=id;
        //a set method is created to set the value of a specific variable LabelID
    }
    public long getLabelID(){
        return LabelID;
        //a get method is created to retrieve the value of a specific variable LabelID within a class
    }

    public void setLabelText(String text){
        this.LabelText=text;
        //a set method is created to set the value of a specific variable LabelText

    }
    public String getLabelText(){
        return LabelText;
        //a get method is created to retrieve the value of a specific variable LabelText within a class
    }
}