package iteration2;

class Main {
    public static void main(String[] args) {
        JSONHandler readJS = new JSONHandler();
        readJS.readConfigFile();
        Thread[] threads; 
        threads = new Thread[readJS.getDataset().getUsers().size()];
        for(int i=0;i<readJS.getDataset().getUsers().size();i++){
            threads[i] = new Thread(){
                public void run(){
                    for(int z = 0; z < readJS.getDataset().getUsers().size();z++){
                        readJS.getDataset().getUsers().get(z).makeAssignment(readJS.getDataset());
                    }
                    
                }
            };
        }
        for(int i=0;i<readJS.getDataset().getUsers().size();i++){
            threads[i].start();
        }

        for(int i=0;i<readJS.getDataset().getUsers().size();i++){
            try{
                threads[i].join();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        readJS.writeNewAssigneeds("SampleOutput2.json", readJS.getDataset().getAssignedLabels());
        
        }

        

}