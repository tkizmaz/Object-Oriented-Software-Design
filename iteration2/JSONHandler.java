package iteration2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Random;

public class JSONHandler {
    
    private Dataset dataset;
    Random rand = new Random();

    public void readConfigFile(){
        List<User> userList = new ArrayList<>(); //creating userList to append
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("D:/GitHub/CSE3063/iteration2/config.json")){
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            long currentDatasetId=(long) jsonObject.get("currentDatasetId");
            JSONArray datasets = (JSONArray) jsonObject.get("datasets");
            datasets.forEach(entry->{
                JSONObject dataset = (JSONObject) entry;
                long nooffusers = (long) dataset.get("number_of_users");
                long datasetId=(long) dataset.get("dataset_id");
                String datasetPath=(String) dataset.get("path");
                if(currentDatasetId==datasetId){
                    readDataset(datasetPath); //dataset id eklemeli miyim Dosyanın içindeki mi olacak
                    this.dataset.setNumberofUsers(nooffusers);
                }
                

            });

            
            JSONArray users = (JSONArray) jsonObject.get("users");
            long chosenRandomly[]= new long[(int)this.dataset.getNumberofUsers()];
            for(int i=0; i<this.dataset.getNumberofUsers();i++){
                int x=rand.nextInt((int)users.size())+1;
                for(long k : chosenRandomly){
                    while(k == x){
                        x = rand.nextInt((int)users.size())+1;
                    }
                }
                chosenRandomly[i]=x;
            }


            /*System.out.println(rand.nextInt((int)this.dataset.getNumberofUsers()));
            System.out.println(rand.nextInt((int)this.dataset.getNumberofUsers()));
            System.out.println(rand.nextInt((int)this.dataset.getNumberofUsers()));*/
            users.forEach(entry->{ //getting all the informations in loop

                JSONObject user = (JSONObject) entry;
                User eachUser = new User();
                long currentUserID=(long)user.get("user id");
                for(int i=0;i<chosenRandomly.length;i++){
                    if((long)currentUserID==chosenRandomly[i]){
                        eachUser.setUserID((long)user.get("user id"));
                        eachUser.setUsername((String)user.get("user name"));
                        eachUser.setUserType((String)user.get("user type"));
                        userList.add(eachUser);
                    }
                }
                

            });
            this.dataset.setUsers(userList);
        }
            catch(IOException ie){ // exception catching
                ie.printStackTrace();
            }
            catch(ParseException ie){
                ie.printStackTrace();
            }
    }

    public void readDataset(String fileName){
        List<Label> labelList = new ArrayList<Label>();
        List<Instance> instanceList = new ArrayList<Instance>();
        Dataset newDataset = new Dataset();
        JSONParser jsonParser = new JSONParser(); //create JSONparser

        try(FileReader reader = new FileReader(fileName)){ //try to read the file

            Object obj = jsonParser.parse(reader); // read that file

            JSONObject jsonObject = (JSONObject) obj; // create JSONobject to pull the data
            
            long databaseID = (long) jsonObject.get("dataset id");  //get, gets the corresponding data
            String datasetName = (String) jsonObject.get("dataset name");
            long maxLabels = (long) jsonObject.get("maximum number of labels per instance");
            JSONArray labels = (JSONArray) jsonObject.get("class labels");
            JSONArray instances = (JSONArray) jsonObject.get("instances");

            newDataset.setDatasetID(databaseID); // getting all the data and create corresponding Dataset object
            newDataset.setDatasetName(datasetName);
            newDataset.setMaximumLabels(maxLabels);


            labels.forEach(entry-> { //getting label because it is in JSONArray
                JSONObject label = (JSONObject) entry;
                Label eachLabel = new Label();
                eachLabel.setLabelID((long) label.get("label id"));
                eachLabel.setLabelText((String) label.get("label text"));
                labelList.add(eachLabel);
            });

            instances.forEach(entry->{ //getting isntances because it is in JSONArray
                JSONObject instance = (JSONObject) entry;
                Instance eachInstance = new Instance();
                eachInstance.setInstanceID((long) instance.get("id"));
                eachInstance.setInstanceText((String) instance.get("instance"));
                instanceList.add(eachInstance);
            });

            newDataset.setLabels(labelList); // setting label for that dataset
            newDataset.setInstances(instanceList); // setting instances for that dataset
            this.dataset = newDataset; 
            
        }

        catch(IOException ie){ // exception catching
            ie.printStackTrace();
        }
        catch(ParseException ie){
            ie.printStackTrace();
        }

        
    }

    public Dataset getDataset(){

        return this.dataset; //getter for Dataset

    }

    public void writeNewAssigneeds(String filename, List<AssignedLabel> assignedLabelList){
        JSONObject samplObject = new JSONObject();
        JSONArray assignments = new JSONArray();
        for(int i=0;i<assignedLabelList.size();i++){
            JSONObject assignedLabelDetails = new JSONObject();
            assignedLabelDetails.put("instance id", assignedLabelList.get(i).getInstanceID().getInstanceID());
            assignedLabelDetails.put("class label id",(assignedLabelList.get(i).getClassLabelID()[0].getLabelID()));
            assignedLabelDetails.put("user id", assignedLabelList.get(i).getUser().getUserID());
            assignedLabelDetails.put("datetime", assignedLabelList.get(i).getLocalTime());
            assignments.add(assignedLabelDetails);
        
        }
        System.out.println("done");
        samplObject.put("class label assignments", assignments);

        try{
            Files.write(Paths.get(filename), samplObject.toJSONString().getBytes());
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }



}
