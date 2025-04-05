package com.sonal.httpserver.core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class
HttpConnectionThread extends Thread{


    private Socket socket;
    private final static Logger LOGGER=  LoggerFactory.getLogger(HttpConnectionThread.class);
    public HttpConnectionThread(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {
        InputStream inputStream =null;
        OutputStream outputStream=null;


        try {
          inputStream = socket.getInputStream();
           outputStream = socket.getOutputStream();
            //TODO we wourld read

            String html = "<html><head><title>simple java server</title></head><body>Simple Java http server</body>";
            //after status line two special characters are sent
            final String CRLF = "\n\r";
            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content Length: " + html.getBytes().length + CRLF + //header
                    CRLF +
                    html +
                    CRLF +
                    CRLF; //status line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE


            //TODO we would write

            outputStream.write(response.getBytes());


            LOGGER.info("Connection processing finished");
        } catch (Exception e) {
            LOGGER.info("Some issue with connection", e);
        }finally {

            if(inputStream!=null)
            {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
            if (outputStream!=null)
            {
                try {
                    outputStream.close();
                } catch (IOException e) {

                }
            }
            if(socket!=null)
            {
                try {
                    socket.close();
                } catch (IOException e) {

                }
            }





        }
    }
}
