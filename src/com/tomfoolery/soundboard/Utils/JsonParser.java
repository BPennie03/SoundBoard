package com.tomfoolery.soundboard.Utils;

import com.tomfoolery.soundboard.exceptions.NoSuchEntryException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class JsonParser {
    private final File f;
    private final HashMap<String, String> map = new HashMap<>();

    public JsonParser(File f){
        this.f = f;
        initMap();
    }

    public String toString(){
        StringBuilder contents = new StringBuilder();
        try {
            Scanner scan = new Scanner(this.f);
            while(scan.hasNext()){
                contents.append(scan.next());
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return contents.toString();
    }

    private void initMap(){
        String cleanStr = this.toString().replaceAll("[^a-zA-Z0-9#:,]", "");

        String[] splitStr = cleanStr.split(",");

        for (String s : splitStr){
            String[] pairs = s.split(":");
            this.map.put(pairs[0], pairs[1]);
        }
    }

    public String getValue(String key) throws NoSuchEntryException {

        if (this.map.get(key) == null){
            throw new NoSuchEntryException("Key could not be found");
        }

        return this.map.get(key);
    }

    public void addPair(String key, String value){
        //update file, then call initMap()
        StringBuilder start = new StringBuilder(this.toString().replace("}",""));
        start.append(",\"").append(key).append("\":\"").append(value).append("\"}");
        try {
            PrintWriter pw = new PrintWriter(this.f);
            pw.write(start.toString());
            pw.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        initMap();
    }
    public boolean valueExists(String value){
        return this.map.containsValue(value);
    }

    public boolean keyExist(String key){
        return this.map.containsKey(key);
    }
    public int getSize(){
        return this.map.size();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }

        if(!(obj instanceof JsonParser)){
            return false;
        }

        return obj.toString().equals(this.toString());
    }

    public JsonParser clone() throws CloneNotSupportedException {
        return (JsonParser) super.clone();
    }

    public Set<String> getKeySet(){
        return this.map.keySet();
    }


}
