package com.sonal.httpserver;


import com.sonal.httpserver.config.Configuration;
import com.sonal.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
* Driver CLass for the server
* */
public class HttpServer {

    public static void main(String[] args) {


        System.out.println("server starting ....");

        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");
        Configuration conf= ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("using port: "+ conf.getPort());
        System.out.println("using port: "+ conf.getWebroot());

        try {
            ServerSocket serverSocket= new ServerSocket(conf.getPort());
           Socket socket= serverSocket.accept();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
