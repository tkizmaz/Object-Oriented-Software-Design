package iteration3;

class Main {
    public static void main(String[] args) {
        JSONHandler readJS = new JSONHandler();
        readJS.readConfigFile();
        System.out.println(readJS.getDataset());

        for(int i =0;i<10;i++){
            readJS.getDataset().getUsers().get(0).makeAssignment(readJS.getDataset());
        }

        for(int i=0;i<readJS.getDataset().getUsers().get(0).getAssignments().size();i++){
            System.out.println(readJS.getDataset().getUsers().get(0).getAssignments().get(i).getInstance().getInstanceID());
        }
        

        /*readJS.getDataset().getUsers().forEach(user->{
            user.makeAssignment(readJS.getDataset());
            System.out.println(user.getAssignments().get(0).getInstance().getInstanceID());
        });*/
        
        readJS.writeNewAssigneeds("SampleOutput2.json", readJS.getDataset().getAssignedLabels());
        
        

        }

    
}