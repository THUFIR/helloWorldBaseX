package org.basex.examples.local;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.Databases;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.List;
import org.basex.core.cmd.Set;
import org.basex.util.list.StringList;

public class ScraperForXML implements Scraper {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Properties properties = new Properties();
    private URL url = null;
    private String databaseName = null;
    private Context context;

    private ScraperForXML() {
    }

    public ScraperForXML(Properties properties) {
        this.properties = properties;
        LOG.fine(properties.toString());
    }
    
    @Override
    public void init() throws MalformedURLException {
        url = new URL(properties.getProperty("xmlURL"));
        databaseName = properties.getProperty("databaseName");
        context = new Context();
    }

    private void list() throws BaseXException {
        LOG.info(new List().execute(context));
    }

    private void drop() throws BaseXException {
        list();
        new Set("parser", "xml").execute(context);
        new CreateDB(databaseName, url.toString()).execute(context);
        list();
    }

    private void create() throws BaseXException {
        list();
        new Set("parser", "xml").execute(context);
        new CreateDB(databaseName, url.toString()).execute(context);
        LOG.info(new List().execute(context));
        list();
    }

    private void infoOnDatabases() {

        Databases databases = context.databases();
        StringList stringListOfDatabases = databases.listDBs();
        String currentDatabaseName = null;

        Iterator<String> databaseIterator = stringListOfDatabases.iterator();

        while (databaseIterator.hasNext()) {
            currentDatabaseName = databaseIterator.next();
            LOG.info(currentDatabaseName);
            //not quite sure how to query a database...
        }
    }

    @Override
    public void fetch() throws BaseXException, MalformedURLException {
        drop();
        create();
        list();
        context.close();
    }

}
