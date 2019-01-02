package org.basex.examples.local;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public final class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();

    public static void main(String[] args) throws IOException {
        LOG.fine("starting..");
        new App().helloWorld();
    }

    private void helloWorld() throws IOException {
        properties.loadFromXML(App.class.getResourceAsStream("/basex.xml"));
        LOG.fine(properties.toString());
        Database database = new Database(properties);
        database.CreateFromScratch();
        database.tryDrop();
        ScraperForXML sfx = new ScraperForXML(properties);
        sfx.fetch();
    }
    
}
