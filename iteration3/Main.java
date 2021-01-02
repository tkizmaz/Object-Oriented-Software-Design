package iteration3;

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
                for(int i=0;i<10;i++){
                    for(int p=0;p<readJS.getDataset().getUsers().size();p++){
                        readJS.getDataset().getUsers().get(p).makeAssignment(readJS.getDataset());
                    }
                }
                break;
            }

            else if (readJS.checkUser(userName, password)){
                for ( User user : readJS.getDataset().getUsers()){
                    if(user.getUsername().equals(userName)){
                        System.out.println("Current User "+user.getUserID()+" "+user.getUsername());
                        for(int i=0;i<10;i++){
                            for(int p=0;p<readJS.getDataset().getUsers().size();p++){
                                readJS.getDataset().getUsers().get(p).makeAssignment(readJS.getDataset());
                            }
                        }
                    }
                }                                
                
                break;
            }            
        }
        //readJS.writeNewAssigneeds("SampleOutput2.json", readJS.getDataset().getAssignedLabels());
    }

    
}