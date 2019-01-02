package org.basex.examples.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();

    public static void main(String[] args) throws IOException   {
        LOG.fine("starting..");

        new App().helloWorld();
    }

    private String fetch() throws MalformedURLException, IOException   {
        LOG.info(properties.toString());

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

    private void helloWorld() throws IOException   {
        properties.loadFromXML(App.class.getResourceAsStream("/basex.xml"));
        LOG.info(properties.toString());
        Database database = new Database(properties);
        database.CreateFromScratch("foo");
    }
}
