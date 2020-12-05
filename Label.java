class Label{
    private long LabelID;
    private String LabelText;

    public void setLabelID(long id){
        this.LabelID=id;
    }
    public long getLabelID(){
        return LabelID;
    }

    public void setLabelText(String text){
        this.LabelText=text;
    }
    public String getLabelText(){
        return LabelText;
    }

}