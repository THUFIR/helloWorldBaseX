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
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.List;
import org.basex.core.cmd.Set;
import org.basex.core.cmd.XQuery;
import org.basex.util.list.StringList;

public class Scraper {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Properties properties = new Properties();
    private URL url = null;
    private String databaseName = null;
    private Context context = null;
    private String parserType = null;

    private Scraper() {
    }

    public Scraper(Properties properties) {
        this.properties = properties;
        LOG.fine(properties.toString());
    }

    public void init() throws MalformedURLException {
        parserType = properties.getProperty("parserType");
        url = new URL(properties.getProperty(parserType + "URL"));
        databaseName = properties.getProperty("databaseName");
        context = new Context();
    }

    private void list() throws BaseXException {
        LOG.info(new List().execute(context));
    }

    private void drop() throws BaseXException {
        new Set("parser", parserType).execute(context);
        new DropDB(databaseName).execute(context);
        list();
    }

    private void create() throws BaseXException {
        new Set("parser", parserType).execute(context);
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
            //xQuery here..
        }
    }

    private void query(final String query) throws BaseXException {
        LOG.info(new XQuery(query).execute(context));
    }

    public void fetch() throws BaseXException, MalformedURLException {
        drop();
        create();
        infoOnDatabases();
        list();
        query("//note/body/text()");
        context.close();
    }

}
