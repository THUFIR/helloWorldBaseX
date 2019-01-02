package org.basex.examples.local;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
//import  org.basex.core.Text.URL;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.List;

public final class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();
    private final Context context = new Context();
    private String stringUrl = "http://books.toscrape.com/";
    private URL url = null;

    public static void main(String[] args) throws IOException {
        LOG.fine("starting..");
        new App().helloWorld();
    }

    private void list() throws BaseXException  {
        LOG.info(new List().execute(context));
    }

    private void helloWorld() throws BaseXException, IOException   {
        url = new URL(stringUrl);
        properties.loadFromXML(App.class.getResourceAsStream("/basex.xml"));
        String databaseName = properties.getProperty("databaseName");
        String databasePath = properties.getProperty("databasePath");

        list();
        new CreateDB(databaseName, databasePath).execute(context);
        list();
        new DropDB(databaseName).execute(context);
        list();
        
        
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    }   
}
