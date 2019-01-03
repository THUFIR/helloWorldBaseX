package org.basex.examples.local;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.logging.Logger;
import org.basex.core.BaseXException;

public final class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();
    private final String queryXML = "//note/body/text()";
    private final String queryHTML = "//text()";

    public static void main(String[] args) throws BaseXException, MalformedURLException, IOException {
        LOG.fine("starting..");
        new App().helloWorld();
    }

    private void queryOnly() throws BaseXException, MalformedURLException {
        DatabaseQuery databaseQuery = new DatabaseQuery(properties);
        databaseQuery.init();
        databaseQuery.runQuery(queryXML); //ouch
    }

    private void populate() throws MalformedURLException {
        Scraper scraper = new Scraper(properties);
        scraper.init();
        scraper.fetch();
    }

    private void helloWorld() throws BaseXException, MalformedURLException, IOException {
        properties.loadFromXML(App.class.getResourceAsStream("/basex.xml"));
        LOG.fine(properties.toString());
     //   populate();
        queryOnly();
    }

}
