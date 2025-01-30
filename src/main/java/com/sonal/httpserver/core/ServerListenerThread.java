package com.sonal.httpserver.core;
import com.sonal.httpserver.HttpServer;
import com.sonal.httpserver.config.Configuration;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ServerListenerThread extends Thread {
    //runnable can also be implemented


    private int port;
    private String webroot;
    private ServerSocket serverSocket;
    private final static Logger LOGGER=  LoggerFactory.getLogger(ServerListenerThread.class);

    public ServerListenerThread( String webroot, int port) throws IOException {

        this.webroot = webroot;
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
    }




    @Override
    public void run() {

        try {

            while(serverSocket.isBound() && !serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
                LOGGER.info(" connection established" + socket.getInetAddress());

                HttpConnectionThread httpConnectionThread= new HttpConnectionThread(socket);
                httpConnectionThread.start();


            }
           // serverSocket.close(); TODO






        } catch (IOException e) {
            LOGGER.info("problem with setting socket", e);
        }finally {
            if(serverSocket!=null)
            {
                try {
                    serverSocket.close();
                } catch (IOException e) {

                }
            }
        }
    }

}
