package com.company.httpserver;

import com.company.httpserver.config.Configuration;
import com.company.httpserver.config.ConfigurationManager;
import com.company.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDriver {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerDriver.class);

    public static void main(String[] args) {
        LOGGER.info("HTTP Server is serving.. ");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/jasonsample.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("Using port : " + configuration.getPort());
        LOGGER.info("Using webroot : " + configuration.getWebroot());
        ServerListenerThread mainThread = null;
        try {
            mainThread = new ServerListenerThread(configuration.getPort(), configuration.getWebroot());
            mainThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
