package iteration2;

class Main {
    public static void main(String[] args) {
        JSONHandler readJS = new JSONHandler();
        readJS.readConfigFile();
        System.out.println(readJS.getDataset());

        readJS.getDataset().getUsers().forEach(user->{
            user.makeAssignment(readJS.getDataset());
        });
        
        readJS.writeNewAssigneeds("SampleOutput2.json", readJS.getDataset().getAssignedLabels());
        
        

        }

        

}