package iteration3;
import java.time.LocalDateTime;
public class QuessingLabeling extends LabellingMechanism {
    private JSONHandler writeJS = new JSONHandler();
    private InstancePerformance iPerformance = new InstancePerformance();
    private DatasetPerformance dPerformance = new DatasetPerformance();
    private UserPerformance uPerformance = new UserPerformance();


    @Override
    public void setAssignedLabels(Dataset currentDataset) {
        iPerformance.setCurrentDataset(currentDataset);
        dPerformance.setCurrentDataset(currentDataset);
        uPerformance.setCurrentDataset(currentDataset);
        uPerformance.setCurrentUser(this.getUser());

        
        String[] positiveWords = {"teşekkürler", "güzel","memnun","basarili","artı"};
        String[] negativeWords = {"yok","degil","bit","hasar","çalışmıyor"};

        AssignedLabel assignedLabel = new AssignedLabel();
        boolean isSameInstance = false;

        int randomToInstanceChoose = rand.nextInt(currentDataset.getInstances().size());
        for(int i=0;i<this.getUser().getAssignments().size();i++){
            Instance labeledInstance = new Instance();
            labeledInstance = getUser().getAssignments().get(i).getInstance();
            if(currentDataset.getInstances().get(randomToInstanceChoose).equals(labeledInstance)){
                isSameInstance = true;
            }
        }

        if(isSameInstance){
            int randomNumber = rand.nextInt(100)+1;
            if(this.getUser().getAssignments().size()>0 && getUser().getConsistencyCheckProbability()*100 >= randomNumber){
                System.out.println("Chance occured!");
                assignedLabel.setInstance(currentDataset.getInstances().get(randomToInstanceChoose));
                for(int i=0;i<positiveWords.length;i++){
                    String positiveWord = positiveWords[i];
                    String negaviteWord = negativeWords[i];
                    if(currentDataset.getInstances().get(randomToInstanceChoose).getInstanceText().contains(positiveWord)){
                        assignedLabel.setClassLabel(currentDataset.getLabels().get(0));
                    }
                    else if(currentDataset.getInstances().get(randomToInstanceChoose).getInstanceText().contains(negaviteWord)){
                        assignedLabel.setClassLabel(currentDataset.getLabels().get(1));
                    }
                    else{
                        assignedLabel.setClassLabel(currentDataset.getLabels().get(2));
                    }
                }
                assignedLabel.setTime(LocalDateTime.now());
                assignedLabel.setUser(this.getUser());
                this.getUser().setAssigneeds(assignedLabel);
                currentDataset.setAssignedLabels(assignedLabel);
                System.out.println("User with id of: "+this.getUser().getUserID()+" with user type "+this.getUser().getUserType() +" created an assignment :"+ assignedLabel);
            }
            else{
                return;
            }
        }
        

        else{
            assignedLabel.setInstance(currentDataset.getInstances().get(randomToInstanceChoose));
            for(int i=0;i<positiveWords.length;i++){
                String positiveWord = positiveWords[i];
                String negaviteWord = negativeWords[i];
                if(currentDataset.getInstances().get(randomToInstanceChoose).getInstanceText().contains(positiveWord)){
                    assignedLabel.setClassLabel(currentDataset.getLabels().get(0));
                }
                else if(currentDataset.getInstances().get(randomToInstanceChoose).getInstanceText().contains(negaviteWord)){
                    assignedLabel.setClassLabel(currentDataset.getLabels().get(1));
                }
                else{
                    assignedLabel.setClassLabel(currentDataset.getLabels().get(2));
                }
            }
            assignedLabel.setTime(LocalDateTime.now());
            assignedLabel.setUser(this.getUser());
            this.getUser().setAssigneeds(assignedLabel);
            currentDataset.setAssignedLabels(assignedLabel);
            System.out.println("User with id of: "+this.getUser().getUserID()+" with user type "+this.getUser().getUserType() +" created an assignment :"+ assignedLabel);
        }
        
        System.out.println("End of labeling");
        iPerformance.setFrequency();
        writeJS.writeUserMetrics(uPerformance);
        writeJS.writeDatasetMetrics(dPerformance);
        writeJS.writeInstanceMetrics(iPerformance);

        try{
            Thread.sleep(500);
        }
        catch(InterruptedException exception){
            System.out.println("bir problem var");
  
        }
    }

}
