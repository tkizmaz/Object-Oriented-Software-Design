package iteration2;


import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        List<List<AssignedLabel>> assignedLabel=new ArrayList<>();
        JSONHandler readJS = new JSONHandler();
        readJS.readConfigFile();
        readJS.getDataset().getUsers().forEach(user->{
            user.makeAssignment(readJS.getDataset());
            assignedLabel.add(user.getAssignments());
        });
        readJS.writeJSON("SampleOutput.json", assignedLabel);
        }
        

}