package org.basex.examples.local;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import org.basex.core.Context;
import org.basex.core.cmd.List;

public class Fetcher {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Properties properties = new Properties();

    private Fetcher() {
    }

    public Fetcher(Properties properties) {
        this.properties = properties;
        LOG.fine(properties.toString());
    }

    public void fetch() throws MalformedURLException, IOException {
        URL url = new URL(properties.getProperty("htmlURL"));
        String databaseName = properties.getProperty("databaseName");
        Context context = new Context();
        LOG.fine(new List().execute(context));
    }

}
