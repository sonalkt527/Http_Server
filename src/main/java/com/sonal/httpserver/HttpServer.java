package com.sonal.httpserver;


import com.sonal.httpserver.config.Configuration;
import com.sonal.httpserver.config.ConfigurationManager;
import com.sonal.httpserver.core.ServerListenerThread;

import java.io.IOException;


import org.slf4j.*;
/*
* Driver CLass for the server
* */
public class HttpServer {

    private final static Logger LOGGER= LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {


        System.out.println("server starting ....");
        LOGGER.info("server starting");

        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");
        Configuration conf= ConfigurationManager.getInstance().getCurrentConfiguration();



        LOGGER.info("using port: "+ conf.getPort());
        LOGGER.info("using webroot: "+ conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread= new ServerListenerThread(conf.getWebroot(),conf.getPort());
            serverListenerThread.start();
        } catch (IOException e) {
           e.printStackTrace();
        }

    }
}
