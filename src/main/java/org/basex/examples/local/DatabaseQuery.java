package org.basex.examples.local;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.List;
import org.basex.core.cmd.Open;
import org.basex.core.cmd.XQuery;

public class DatabaseQuery {
    
    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Properties properties = new Properties();
    private URL url = null;
    private String databaseName = null;
    private Context context = null;
    private String parserType = null;
    
    private DatabaseQuery() {
    }
    
    public DatabaseQuery(Properties properties) {
        this.properties = properties;
        LOG.fine(properties.toString());
    }
    
    public void init() throws MalformedURLException {
        parserType = properties.getProperty("parserType");
        url = new URL(properties.getProperty(parserType + "URL"));
        databaseName = properties.getProperty("databaseName");
        context = new Context();
    }
    
    public void runQuery(String query) throws BaseXException {
        new Open(databaseName).execute(context);
        LOG.info(new List().execute(context));
        LOG.info(new XQuery(query).execute(context));
        LOG.info("query was\t\t\t" + query);
        context.close();
    }
    
}
