package com.company.httpserver.config;

import com.company.httpserver.util.JsonAnalysis;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.sun.net.httpserver.HttpsConfigurator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpRetryException;
import java.util.Map;

public class ConfigurationManager {
    private static ConfigurationManager myConfigManager;
    private static Configuration currentConfiguration;
    private ConfigurationManager(){

    }
    public static ConfigurationManager getInstance(){
        if (myConfigManager==null){
            myConfigManager = new ConfigurationManager();
        }
        return myConfigManager;
    }
    public void loadConfigurationFile(String path){
        //Need to be change
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
            StringBuffer sb = new StringBuffer();
            int i;
            while ((i = fileReader.read()) !=-1){
                sb.append((char) i);
            }
            System.out.println("Parsing...");
            JsonNode conf = JsonAnalysis.parse(sb.toString());
            currentConfiguration = JsonAnalysis.fromJson(conf,Configuration.class);
        } catch (FileNotFoundException e) {
            throw  new HttpsConfigurationException(e);
        } catch (IOException e) {
            throw new HttpsConfigurationException(e);
        }

    }
    public Configuration getCurrentConfiguration(){
        if (currentConfiguration == null){
            throw new HttpsConfigurationException("No Current Configuration set");
        }
        return currentConfiguration;
    }
}
