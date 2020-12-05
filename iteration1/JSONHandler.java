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

        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader(fileName)){

            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;
            
            long databaseID = (long) jsonObject.get("dataset id");
            String datasetName = (String) jsonObject.get("dataset name");
            long maxLabels = (long) jsonObject.get("maximum number of labels per instance");
            JSONArray labels = (JSONArray) jsonObject.get("class labels");
            JSONArray instances = (JSONArray) jsonObject.get("instances");

            newDataset.setDatasetID(databaseID);
            newDataset.setDatasetName(datasetName);
            newDataset.setMaximumLabels(maxLabels);


            labels.forEach(entry-> {
                JSONObject label = (JSONObject) entry;
                Label eachLabel = new Label();
                eachLabel.setLabelID((long) label.get("label id"));
                eachLabel.setLabelText((String) label.get("label text"));
                labelList.add(eachLabel);
            });

            instances.forEach(entry->{
                JSONObject instance = (JSONObject) entry;
                Instance eachInstance = new Instance();
                eachInstance.setInstanceID((long) instance.get("id"));
                eachInstance.setInstanceText((String) instance.get("instance"));
                instanceList.add(eachInstance);
            });

            newDataset.setLabels(labelList);
            newDataset.setInstances(instanceList);
            this.dataset = newDataset;
            
        }

        catch(IOException ie){
            ie.printStackTrace();
        }
        catch(ParseException ie){
            ie.printStackTrace();
        }

        
    }

    public Dataset getDataset(){

        return this.dataset;

    }

    public void readUsers(String userFile){

        this.inputUserFile = userFile;

        List<User> userList = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader(userFile)){

            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray users = (JSONArray) jsonObject.get("users");

            users.forEach(entry->{

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
        

        JSONObject samplObject = new JSONObject();
        samplObject.put("dataset id", this.dataset.getDatasetID());
        samplObject.put("dataset name", this.dataset.getDatasetName());
        samplObject.put("maximum number", this.dataset.getMaximumLabels());
        JSONArray classLabels = new JSONArray();
        for(int i=0;i<this.dataset.getLabels().size();i++){
            JSONObject labelDetails = new JSONObject();
            labelDetails.put("label id", this.dataset.getLabels().get(i).getLabelID());
            labelDetails.put("label text", this.dataset.getLabels().get(i).getLabelText());
            classLabels.add(labelDetails);
        }

        samplObject.put("class labels", classLabels);


        JSONArray classInstances = new JSONArray();
        for(int i=0;i<this.dataset.getInstances().size();i++){
            JSONObject instanceDetails = new JSONObject();
            instanceDetails.put("id", this.dataset.getInstances().get(i).getInstanceID());
            instanceDetails.put("instance", this.dataset.getInstances().get(i).getInstanceText());
            classInstances.add(instanceDetails);
        }

        samplObject.put("instances", classInstances);

        JSONArray users = new JSONArray();
        for(int i=0;i<this.dataset.getUsers().size();i++){
            JSONObject userDetails = new JSONObject();
            userDetails.put("user id", this.dataset.getUsers().get(i).getUserID());
            userDetails.put("user name", this.dataset.getUsers().get(i).getUsername());
            userDetails.put("user type", this.dataset.getUsers().get(i).getuserType());
            users.add(userDetails);
        }

        samplObject.put("users",users);

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
