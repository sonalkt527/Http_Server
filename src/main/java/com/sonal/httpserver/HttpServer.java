package com.sonal.httpserver;


import com.sonal.httpserver.config.Configuration;
import com.sonal.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        System.out.println("using webroot: "+ conf.getWebroot());

        try {
            ServerSocket serverSocket= new ServerSocket(conf.getPort());
           Socket socket= serverSocket.accept();

           InputStream inputStream =socket.getInputStream();
           OutputStream outputStream=socket.getOutputStream();

           //TODO we wourld read

            String html="<html><head><title>simple java server</title></head><body>Simple Java http server</body>";
            //after status line two special characters are sent
           final  String CRLF="\n\r";
            String response= "HTTP/1.1 200 OK"+CRLF+
                    "Content Length: "+html.getBytes().length+CRLF+ //header
                    CRLF+
                    html+
                    CRLF+
                    CRLF; //status line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE





            //TODO we would write

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            serverSocket.close();
            socket.close();





        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
