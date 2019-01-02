package org.basex.examples.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.List;

public final class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();
    private final Context context = new Context();
    private URL url = null;

    public static void main(String[] args) throws IOException   {
        LOG.fine("starting..");
        new App().helloWorld();
    }

    private void list() throws BaseXException {
        LOG.info(new List().execute(context));
    }

    private void creatDrop() throws BaseXException {
        String databaseName = properties.getProperty("databaseName");
        String databasePath = properties.getProperty("databasePath");
        list();
        new CreateDB(databaseName, databasePath).execute(context);
        list();
        new DropDB(databaseName).execute(context);
        list();
    }

    private void helloWorld() throws IOException {
        properties.loadFromXML(App.class.getResourceAsStream("/basex.xml"));
        url = new URL(properties.getProperty("url"));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
            for (String line; (line = bufferedReader.readLine()) != null;) {
                stringBuilder.append(line);
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }
        LOG.info(stringBuilder.toString());
    }
}
