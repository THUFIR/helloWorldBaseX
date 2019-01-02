package org.basex.examples.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

public class RemoteXMLGrabber {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Properties properties = new Properties();

    private RemoteXMLGrabber() {
    }

    public RemoteXMLGrabber(Properties properties) {
        this.properties = properties;
        LOG.fine(properties.toString());
    }

    private void grab() throws MalformedURLException, IOException {
        URL url = new URL(properties.getProperty("htmlURL"));

    }

}
