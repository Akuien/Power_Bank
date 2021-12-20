package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomJsonIo {

    private Gson gson;
    private Writer writer;
    private Reader reader;

    public CustomJsonIo() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void writeObjectToJson(String fileName, Object object){
        try{
            writer = Files.newBufferedWriter(Paths.get(fileName));
            gson.toJson(object, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readObjectFromJson(String fileName, Object object){
        Object returnObject = null;
        try{
            reader = Files.newBufferedReader(Paths.get(fileName));
            returnObject = gson.fromJson(reader, Object.class);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnObject;
    }

    public void writeArrayToJson(String fileName, ArrayList<Object> list){
        try{
            //initialize writer
            writer = Files.newBufferedWriter(Paths.get(fileName));

            //convert an arraylist to json file
            gson.toJson(list, writer);

            //close the writer, when is done writing
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Object> readArrayFromJson(String fileName, Object[] objects){
        ArrayList<Object> list = new ArrayList<>();

        try{
            reader = Files.newBufferedReader(Paths.get(fileName));
            List<Object> temp = (List<Object>) Arrays.asList(gson.fromJson(reader, objects.getClass()));
            list = new ArrayList<Object>(temp);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void clearFile(String fileName){
        Writer writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get(fileName));
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
