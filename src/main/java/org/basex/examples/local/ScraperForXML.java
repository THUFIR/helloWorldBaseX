package org.basex.examples.local;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.List;
import org.basex.core.cmd.Set;

public class ScraperForXML {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Properties properties = new Properties();

    private ScraperForXML() {
    }

    public ScraperForXML(Properties properties) {
        this.properties = properties;
        LOG.fine(properties.toString());
    }

    public void fetch() throws MalformedURLException, IOException {
        URL url = new URL(properties.getProperty("xmlURL"));
        String databaseName = properties.getProperty("databaseName");

        Context ctx = new Context();

        final String name = "htmlexample";

        new Set("parser", "xml").execute(ctx);
        new CreateDB(name, url.toString()).execute(ctx);

        LOG.info(new List().execute(ctx));

        new DropDB(name).execute(ctx);
        ctx.close();
    }

}
