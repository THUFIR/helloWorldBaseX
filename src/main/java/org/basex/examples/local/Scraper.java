package org.basex.examples.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

public class Scraper {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Properties properties = new Properties();

    private Scraper() {
    }

    public Scraper(Properties properties) {
        this.properties = properties;
        LOG.fine(properties.toString());
    }

    private String fetch() throws MalformedURLException, IOException {
        URL url = new URL(properties.getProperty("url"));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String html = null;

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
            for (String line; (line = bufferedReader.readLine()) != null;) {
                stringBuilder.append(line);
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }
        html = stringBuilder.toString();
        LOG.fine(html);
        return html;
    }

}
