import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        List<List<AssignedLabel>> assignedLabel=new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter filename");
        String fileName = myObj.nextLine();
        JSONHandler readJS = new JSONHandler();
        readJS.readDataset(fileName);
        readJS.readUsers("users.json");
        readJS.getDataset().getUsers().forEach(user->{

            user.makeAssignment(readJS.getDataset().getLabels(), readJS.getDataset().getInstances(),readJS.getDataset().getMaximumLabels());
            assignedLabel.add(user.getAssignments());
        });
        
        readJS.writeJSON("sampleOutput2.json", assignedLabel);
        
    }

}