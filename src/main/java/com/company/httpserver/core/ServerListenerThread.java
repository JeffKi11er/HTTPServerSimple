package com.company.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            LOGGER.info("* Connetion accepted: " + socket.getInetAddress()); //socket get the address of the client
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
