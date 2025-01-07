package com.sonal.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sonal.httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;

    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance()
    {
        if(myConfigurationManager==null)
            myConfigurationManager= new ConfigurationManager();
        return myConfigurationManager;
    }

/*
*used to load a configuration file by the path provided
*  */
    public void loadConfiguration(String filePath)  {
        FileReader fileReader= null;
        try{
            fileReader= new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new httpConfigurationException(e);
        }

        StringBuffer sb= new StringBuffer();
        int i;
        try{
            while( (i=fileReader.read())!=-1)
            {
                sb.append((char) i);
            }

        } catch (IOException e) {

            throw new httpConfigurationException(e);

        }

        JsonNode conf= null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new httpConfigurationException("error parsing the file");
        }
        try {
            myCurrentConfiguration= Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new httpConfigurationException("error parsing the configuration file, internal",e);
        }
    }

    /*
    * return the current loaded configuration
    *
    * */

    public Configuration getCurrentConfiguration()
    {
        if(myCurrentConfiguration==null)
        {
            throw  new httpConfigurationException("no current configuration");
        }
        return myCurrentConfiguration;
    }
}
