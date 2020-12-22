package iteration1;

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
        Scanner userFileobj = new Scanner(System.in);
        System.out.println("Enter user file");
        String userFileName = userFileobj.nextLine();
        readJS.readUsers(userFileName);
        readJS.getDataset().getUsers().forEach(user->{

            user.makeAssignment(readJS.getDataset());
            assignedLabel.add(user.getAssignments());
        });
        
        readJS.writeJSON("SampleOutput.json", assignedLabel);
        
    }

}