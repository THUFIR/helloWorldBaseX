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

    private ScraperForXML() {
    }

    public ScraperForXML(Properties properties) {
        this.properties = properties;
        LOG.fine(properties.toString());
    }

    @Override
    public void fetch() throws BaseXException, MalformedURLException {
        URL url = new URL(properties.getProperty("xmlURL"));
        String databaseName = properties.getProperty("databaseName");

        Context context = new Context();
        LOG.info(new List().execute(context));

        new Set("parser", "xml").execute(context);
        new CreateDB(databaseName, url.toString()).execute(context);

        LOG.info(new List().execute(context));

        Databases databases = context.databases();
        StringList stringListOfDatabases = databases.listDBs();
        String currentDatabaseName = null;

        Iterator<String> databaseIterator = stringListOfDatabases.iterator();

        while (databaseIterator.hasNext()) {
            currentDatabaseName = databaseIterator.next();
            LOG.info(currentDatabaseName);
            //not quite sure how to query a database...
        }

        //  new DropDB(databaseName).execute(context);
        context.close();
    }

}
