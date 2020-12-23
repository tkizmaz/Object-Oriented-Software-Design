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

    public void writeJSON(String filename, List<List<AssignedLabel>> assignedLabelList){
        //This function takes filename as an argument to give output. List<List<AssignedLabel>> HOLDS all of the assigned Labels and we need to loop through it.

        JSONObject samplObject = new JSONObject();
        samplObject.put("dataset id", this.dataset.getDatasetID()); // put, puts corresponding set of data
        samplObject.put("dataset name", this.dataset.getDatasetName());
        samplObject.put("maximum number", this.dataset.getMaximumLabels());
        JSONArray classLabels = new JSONArray(); //creating JSONarray to fill in with labels because it is an array, we need for loop
        for(int i=0;i<this.dataset.getLabels().size();i++){
            JSONObject labelDetails = new JSONObject();
            labelDetails.put("label id", this.dataset.getLabels().get(i).getLabelID());
            labelDetails.put("label text", this.dataset.getLabels().get(i).getLabelText());
            classLabels.add(labelDetails);
        }

        samplObject.put("class labels", classLabels); //putting classlabels


        JSONArray classInstances = new JSONArray(); 
        for(int i=0;i<this.dataset.getInstances().size();i++){ //creating JSONarray to fill in with instances because it is an array, we need for loop
            JSONObject instanceDetails = new JSONObject();
            instanceDetails.put("id", this.dataset.getInstances().get(i).getInstanceID());
            instanceDetails.put("instance", this.dataset.getInstances().get(i).getInstanceText());
            classInstances.add(instanceDetails);
        }

        samplObject.put("instances", classInstances); //putting instances

        JSONArray users = new JSONArray();
        for(int i=0;i<this.dataset.getUsers().size();i++){ //looping through users to put to JSON
            JSONObject userDetails = new JSONObject();
            userDetails.put("user id", this.dataset.getUsers().get(i).getUserID());
            userDetails.put("user name", this.dataset.getUsers().get(i).getUsername());
            userDetails.put("user type", this.dataset.getUsers().get(i).getuserType());
            users.add(userDetails);
        }

        samplObject.put("users",users); //putting to jsonfile

        JSONArray assignedLabel = new JSONArray();
        for(int i=0;i<assignedLabelList.size();i++){
            for(int p = 0; p < assignedLabelList.get(i).size();p++){
                JSONObject assignedLabelDetails = new JSONObject();
                assignedLabelDetails.put("instance id", assignedLabelList.get(i).get(p).getInstanceID());
                assignedLabelDetails.put("class label id",Arrays.toString(assignedLabelList.get(i).get(p).getClassLabelID()));
                assignedLabelDetails.put("user id", assignedLabelList.get(i).get(p).getUserID());
                assignedLabelDetails.put("datetime", assignedLabelList.get(i).get(p).getLocalTime());
                assignedLabel.add(assignedLabelDetails);
            }
        }
        System.out.println("done");
        samplObject.put("class label assignments",assignedLabel);
        

        try{
            Files.write(Paths.get(filename), samplObject.toJSONString().getBytes());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /*public void writeJSON2(String filename,AssignedLabel assignedLabel){
        //This function takes filename as an argument to give output. List<List<AssignedLabel>> HOLDS all of the assigned Labels and we need to loop through it.
        System.out.println("HELLOOOOOOOOOOOOOOOO");
        JSONObject samplObject = new JSONObject();

        JSONArray assignedLabels = new JSONArray();

        JSONObject assignedLabelDetails = new JSONObject();
        assignedLabelDetails.put("instance id", assignedLabel.getInstanceID());
        assignedLabelDetails.put("class label id",Arrays.toString(assignedLabel.getClassLabelID()));
        assignedLabelDetails.put("user id", assignedLabel.getUserID());
        assignedLabelDetails.put("datetime", assignedLabel.getLocalTime());
        assignedLabels.add(assignedLabelDetails);
        samplObject.put("KEY", assignedLabels);
        System.out.println("done");
        

        try{
            Files.write(Paths.get(filename), samplObject.toJSONString().getBytes());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeEachLabel(){
        JSONParser parser = new JSONParser();
        JSONObject records = null;
        try {
            records = (JSONObject) parser.parse(new FileReader("iteration2/SampleDataset2.json"));

        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JSONArray r = (JSONArray) records.get("instances");

        JSONObject NewObj = new JSONObject();
        NewObj.put("travelTime", "dasdas");
        NewObj.put("totalDistance", "dasx");
        NewObj.put("pace", "daz");
        NewObj.put("kCalBurned", "kCalBurned");
        NewObj.put("latlng", "latlng");
        r.add(NewObj);

        try (FileWriter file = new FileWriter("iteration2/SampleDataset2.json")) {
            file.write(records.toJSONString());
       } catch (IOException ex) {
            ex.printStackTrace();
       }



        }*/
    



}
