package iteration3;

import java.text.BreakIterator;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        JSONHandler readJS = new JSONHandler();
        readJS.readConfigFile();
        
        Scanner scanner = new Scanner(System.in);


        while (true){
            System.out.println("Enter user name:");
            String userName = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            if (userName.length()==0 && password.length()==0){
                readJS.getDataset().getUsers().forEach(user->{
                    user.makeAssignment(readJS.getDataset());
                });
                break;  
            }
            else if (readJS.checkUser(userName, password)){
                System.out.println("here");
                
                for ( User user : readJS.getDataset().getUsers()){
                    System.out.println(user.getUsername());
                    if(user.getUsername()==(userName)){
                        System.out.println(user.getUserID());
                        user.makeAssignment(readJS.getDataset());
                    }
                }
                
                
                break;
            }            
        }
        readJS.writeNewAssigneeds("SampleOutput2.json", readJS.getDataset().getAssignedLabels());
    }

    
}