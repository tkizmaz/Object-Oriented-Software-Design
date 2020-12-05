package iteration1;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.crypto.Data;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONHandler {

    private String inputFileName;
    private String inputUserFile;
    private Dataset dataset;

    public void readDataset(String fileName){
        this.inputFileName = fileName;
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

    public void readUsers(String userFile){

        this.inputUserFile = userFile;

        List<User> userList = new ArrayList<>(); //creating userList to append

        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader(userFile)){ 

            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray users = (JSONArray) jsonObject.get("users");

            users.forEach(entry->{ //getting all the informations in loop

                JSONObject user = (JSONObject) entry;
                User eachUser = new User();
                eachUser.setUserID((long)user.get("user id"));
                eachUser.setUsername((String)user.get("user name"));
                eachUser.setUserType((String)user.get("user type"));
                userList.add(eachUser);

            });
            this.dataset.setUsers(userList);
        }

        catch(IOException ie){
            ie.printStackTrace();
        }
        catch(ParseException ie){
            ie.printStackTrace();
        }


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
            for(int p = 0; p < assignedLabelList.get(0).size();p++){
                JSONObject assignedLabelDetails = new JSONObject();
                assignedLabelDetails.put("instance id", assignedLabelList.get(i).get(p).getInstanceID());
                assignedLabelDetails.put("class label id",Arrays.toString(assignedLabelList.get(i).get(p).getClassLabelID()));
                assignedLabelDetails.put("user id", assignedLabelList.get(i).get(p).getUserID());
                assignedLabelDetails.put("datetime", assignedLabelList.get(i).get(p).getLocalTime());
                assignedLabel.add(assignedLabelDetails);

            }
 
        }

        samplObject.put("class label assignments",assignedLabel);
        

        try{
            Files.write(Paths.get(filename), samplObject.toJSONString().getBytes());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }



}
