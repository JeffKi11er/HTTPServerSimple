package com.company.httpserver;

import com.company.httpserver.config.Configuration;
import com.company.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDriver {
    public static void main(String[] args) {
        System.out.println("Hello Server");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/jasonsample.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Using port : "+configuration.getPort());
        System.out.println("Using webroot : "+configuration.getWebroot());
        try {
            ServerSocket serverSocket = new ServerSocket(configuration.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html>" +
                    "<head>" +
                    "<title>HTTP local host </title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>This pages is belong to Thiet </h1>" +
                    "</body>" +
                    "</html>";
            final String CRLF = "\n\r"; //carriage return and the line feed //13,10 ASCII
            String response = "HTTP/1.1 200 OK" +//Status line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                    CRLF +
                    "Content-Length: "+html.getBytes().length +CRLF +//HEADER//Content length is the size
                    CRLF+
                    html +CRLF+
                    CRLF;
            outputStream.write(response.getBytes());
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
