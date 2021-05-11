package com.company.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HTTPConnectionWorkThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(HTTPConnectionWorkThread.class);
    private Socket socket;

    public HTTPConnectionWorkThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

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
                    "Content-Length: " + html.getBytes().length + CRLF +//HEADER//Content length is the size
                    CRLF +
                    html + CRLF +
                    CRLF;
            outputStream.write(response.getBytes());
            LOGGER.info("Connection Processing Finish.");
        } catch (IOException e) {
            LOGGER.error("Problem with communication", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {

                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {

                }
            }
        }
    }
}
